package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.ActivoDTO;
import luceroraul.stacktrace.examen.entities.Operacion;
import luceroraul.stacktrace.examen.entities.Operacion.OperacionTipo;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.repositories.OperacionRepository;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.util.ActivoUtil;
import luceroraul.stacktrace.examen.util.BilleteraOperacionUtil;
import luceroraul.stacktrace.examen.util.BilleteraUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio encargado de realizar las {@link Operacion} con {@link Activo} asignados a {@link luceroraul.stacktrace.examen.entities.Billetera}
 */
@Service
public class BilleteraActivoOperacionesService {

    @Autowired
    ActivoRepository activoRepository;

    @Autowired
    OperacionRepository operacionRepository;

    @Autowired
    BilleteraOperacionUtil billeteraOperacionUtil;

    @Autowired
    BilleteraUtil billeteraUtil;

    @Autowired
    ActivoUtil activoUtil;

    /**
     * Metodo que realiza el deposito y almacena la {@link Operacion}
     * @param peticion contiene activo de destino y la cantidad a cargar
     * @return Activo con las modificaciones del deposito
     * @throws Exception
     */
    public Activo depositar(PeticionDeposito peticion) throws Exception {
        Activo resultado;
        Double cantidad = peticion.getCantidadOperable();
        Activo activoDestino = activoRepository
                .findById(peticion.getIdActivoDestino())
                .orElseThrow();
        if (peticion.getCantidadOperable() < 0){
            throw new Exception("cantidad a depositar negativa");
        }

        resultado = billeteraOperacionUtil.realizarIncrementoMismaUnidad(
                activoDestino,
                cantidad);

        almacenarDepositoYActivo(resultado, cantidad);
        return resultado;
    }

    /**
     * Metodo encargado de devolver la respuesta del deposito
     * @param peticion
     * @return
     * @throws Exception
     */
    public ResponseEntity<Body> depositarResultadoDto(PeticionDeposito peticion) {
        Respuesta respuesta;
        ActivoDTO resultado;
        try {
            resultado = activoUtil.convertirToDTO(depositar(peticion));
            respuesta = new Respuesta(resultado, "deposito realizado con exito", HttpStatus.OK);
        } catch (Exception e) {
            respuesta = new Respuesta(null, "error al realizar deposito", HttpStatus.ACCEPTED);
        }
        return respuesta.getResponseEntity();
    }

    /**
     * Metodo encargado de comenzar el intercambio
     * @param peticion
     * @return
     */
    public ResponseEntity<Body> intercambiar(PeticionIntercambio peticion){
        List<String> errores = new ArrayList<>();
        Respuesta respuesta;
        Double cantidad = peticion.getCantidadOperable();
        Activo activoOrigen = null;
        Map<String, Activo> resultado = null;
        //verifico que los activos existan
        try {
            activoOrigen = activoRepository.findById(peticion.getIdActivoOrigen()).orElseThrow();
        } catch (Exception e) {
            errores.add("Activo de Origen");
        }
        Activo activoDestino = null;
        try {
            activoDestino = activoRepository.findById(peticion.getIdActivoDestino()).orElseThrow();
        } catch (Exception e) {
            errores.add("Activo de Destino");
        }
        if (errores.size() == 0){
            try {
                //realizo el intercambio y almaceno el resultado
                resultado = realizarIntercambio(activoOrigen, activoDestino, cantidad);
                if (resultado.size() == 2){
                    almacenarIntercambioYActivos(resultado, cantidad);
                }
                respuesta = new Respuesta(
                        activoUtil.convertirMapDto(resultado),
                        "Intercambio realizado con exito",
                        HttpStatus.OK);

            } catch (Exception e) {
                respuesta = new Respuesta(
                        null,
                        "Error al intercambiar, error en cantidad",
                        HttpStatus.ACCEPTED);
            }
        } else {
            respuesta = new Respuesta(
                    null,
                    "Error al intercambiar. Parametros erroneos: "+errores.toString(),
                    HttpStatus.ACCEPTED);
        }
        return respuesta.getResponseEntity();
    }


    /**
     * Meotodo encargado de hacer el intercambio
     * @param activoOrigen no null
     * @param activoDestino no null
     * @param cantidad no null
     * @return Map con activoReducido y activoIncrementado de {@link Activo} mostrado el estado posterior al intercambio
     * @throws Exception
     */
    public Map<String, Activo> realizarIntercambio(Activo activoOrigen, Activo activoDestino, Double cantidad) throws Exception {
        Map<String, Activo> resultado = new HashMap<>();

        if (billeteraOperacionUtil.tieneMontoSuficiente(activoOrigen,cantidad)){
            resultado.put("activoReducido", billeteraOperacionUtil.realizarReduccion(activoOrigen, cantidad));
            if (billeteraOperacionUtil.sonSobreMismaMonedaCripto(activoOrigen, activoDestino)){
                resultado.put("activoIncrementado", billeteraOperacionUtil.realizarIncrementoMismaUnidad(activoDestino, cantidad));
            } else {
                resultado.put("activoIncrementado", billeteraOperacionUtil.realizarIncrementoDiferentesUnidades(
                        activoOrigen,activoDestino, cantidad));
            }
        } else {
            throw new Exception("fondo insuficiente en activo de origen");
        }
        return resultado;
    }

    /**
     * Metodo encargado de almacenar el {@link Activo} y generar la {@link Operacion} de {@link OperacionTipo#DEPOSITO}
     * @param resultado
     * @param cantidad
     */
    private void almacenarDepositoYActivo(Activo resultado, Double cantidad) {
        activoRepository.save(resultado);
        operacionRepository.save(
                Operacion.builder()
                        .momentoOperacion(LocalDateTime.now())
                        .operacionTipo(OperacionTipo.DEPOSITO)
                        .activoDestino(resultado)
                        .cantidadOperada(cantidad)
                        .build());
    }

    /**
     * Metodo encargado almacenar los {@link Activo} y generar la {@link Operacion} de {@link OperacionTipo#INTERCAMBIO}
     * @param map activos ya modificados, listos para persistir
     * @param cantidad la cantidad que fue transferida de un activo a otro
     */
    private void almacenarIntercambioYActivos(Map<String, Activo> map, Double cantidad){
        map.forEach((key,data) -> activoRepository.save(data));
        operacionRepository.save(
                Operacion.builder()
                    .momentoOperacion(LocalDateTime.now())
                    .operacionTipo(OperacionTipo.INTERCAMBIO)
                    .activoOrigen(map.get("activoReducido"))
                    .activoDestino(map.get("activoIncrementado"))
                    .cantidadOperada(cantidad)
                .build());
    }


}

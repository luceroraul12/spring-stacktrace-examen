package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.BilleteraDto.ActivoDto;
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

    public Activo depositar(PeticionDeposito peticion) throws Exception {
        Activo resultado;
        Double cantidad = peticion.getCantidadOperable();
        Activo activoDestino = activoRepository
                .findById(peticion.getIdActivoDestino())
                .orElseThrow();

        resultado = billeteraOperacionUtil.realizarIncrementoMismaUnidad(
                activoDestino,
                cantidad);

        almacenarDepositoYActivo(resultado);
        return resultado;
    }

    public ResponseEntity<Body> depositarResultadoDto(PeticionDeposito peticion) throws Exception {
        Respuesta respuesta;
        ActivoDto resultado;
        try {
            resultado = billeteraUtil.convertirActivoaDTO(depositar(peticion));
            respuesta = new Respuesta(resultado, "deposito realizado con exito", HttpStatus.OK);
        } catch (Exception e) {
            respuesta = new Respuesta(null, "error al realizar deposito", HttpStatus.ACCEPTED);
        }
        return respuesta.getResponseEntity();
    }

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
            resultado.forEach((key,data) -> activoRepository.save(data));
        } else {
            throw new Exception("fondo insuficiente en activo de origen");
        }
        return resultado;
    }

    private void almacenarDepositoYActivo(Activo resultado) {
        activoRepository.save(resultado);
        operacionRepository.save(new Operacion(
                LocalDateTime.now(),
                OperacionTipo.DEPOSITO,
                null,
                resultado
        ));
    }

    private void almacenarIntercambioYActivos(Map<String, Activo> map){
        map.forEach((key,data) -> activoRepository.save(data));
        operacionRepository.save(new Operacion(
                LocalDateTime.now(),
                OperacionTipo.INTERCAMBIO,
                map.get("activoReducido"),
                map.get("activoIncrementado")
        ));
    }


}

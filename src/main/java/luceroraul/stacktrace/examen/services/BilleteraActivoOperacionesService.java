package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.BilleteraDto;
import luceroraul.stacktrace.examen.entities.BilleteraDto.ActivoDto;
import luceroraul.stacktrace.examen.entities.Operacion;
import luceroraul.stacktrace.examen.entities.OperacionTipo;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.repositories.OperacionRepository;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.util.BilleteraOperacionUtil;
import luceroraul.stacktrace.examen.util.BilleteraUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
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

    public ResponseEntity<Respuesta<ActivoDto>> depositarResultadoDto(PeticionDeposito peticion) throws Exception {
        ActivoDto resultado = billeteraUtil.convertirActivoaDTO(depositar(peticion));
        ResponseEntity<Respuesta<ActivoDto>> retorno;
        Respuesta<ActivoDto> respuesta;
        if (resultado != null){
            respuesta = new Respuesta<>(resultado, "deposito realizado con exito");
        } else {
            respuesta = new Respuesta<>(null, "error al realizar deposito");
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }



    public Map<String, Activo> intercambiar(PeticionIntercambio peticion) throws Exception {
        Map<String, Activo> resultado = new HashMap<>();
        Double cantidad;
        Activo activoOrigen = activoRepository.findById(peticion.getIdActivoOrigen()).orElseThrow(()->{
            return new Exception("activo de origen erroneo");
        });
        Activo activoDestino = activoRepository.findById(peticion.getIdActivoDestino()).orElseThrow(() ->{
            return new Exception("activo de destino erroneo");
        });
        cantidad = peticion.getCantidadOperable();

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
                OperacionTipo.DEPOSITO,
                map.get("activoReducido"),
                map.get("activoIncrementado")
        ));
    }
}

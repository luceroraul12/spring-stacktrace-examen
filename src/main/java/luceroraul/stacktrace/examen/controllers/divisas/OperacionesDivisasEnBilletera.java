package luceroraul.stacktrace.examen.controllers.divisas;

import luceroraul.stacktrace.examen.entities.BilleteraDto.ActivoDto;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.responses.Respuesta;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OperacionesDivisasEnBilletera {

    ResponseEntity<Respuesta<ActivoDto>> depositarCantidad(PeticionDeposito peticion) throws Exception;
    ResponseEntity<Respuesta<Map<String, ActivoDto>>> intercambiarCantidad(PeticionIntercambio Peticion) throws Exception;
}

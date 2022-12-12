package luceroraul.stacktrace.examen.controllers.divisas;

import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OperacionesDivisasEnBilletera {

    ResponseEntity<Body> depositarCantidad(PeticionDeposito peticion) throws Exception;
    ResponseEntity<Body> intercambiarCantidad(PeticionIntercambio Peticion) throws Exception;
}

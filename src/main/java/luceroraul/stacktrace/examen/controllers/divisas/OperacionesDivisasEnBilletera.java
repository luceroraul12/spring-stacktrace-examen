package luceroraul.stacktrace.examen.controllers.divisas;

import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import org.springframework.http.ResponseEntity;

public interface OperacionesDivisasEnBilletera {

    ResponseEntity<Object> depositarCantidad(PeticionDeposito peticion);
    ResponseEntity<Object> intercambiarCantidad(PeticionIntercambio Peticion);
}

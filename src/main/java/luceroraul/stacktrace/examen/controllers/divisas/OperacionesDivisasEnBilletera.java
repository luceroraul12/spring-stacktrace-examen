package luceroraul.stacktrace.examen.controllers.divisas;

import luceroraul.stacktrace.examen.entities.MonedaCriptoCantidadAdquirida;
import org.springframework.http.ResponseEntity;

public interface OperacionesDivisasEnBilletera {

    ResponseEntity<Object> depositarCantidad();
    ResponseEntity<Object> intercambiarCantidad();
}

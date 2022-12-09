package luceroraul.stacktrace.examen.controllers.divisas;

import luceroraul.stacktrace.examen.entities.MonedaCriptoCantidadAdquirida;
import luceroraul.stacktrace.examen.request.MonedaCriptoOperacionPeticion;
import org.springframework.http.ResponseEntity;

public interface OperacionesDivisasEnBilletera {

    ResponseEntity<Object> depositarCantidad(MonedaCriptoOperacionPeticion peticion);
    ResponseEntity<Object> intercambiarCantidad(MonedaCriptoOperacionPeticion Peticion);
}

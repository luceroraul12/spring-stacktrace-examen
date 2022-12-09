package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.controllers.divisas.OperacionesDivisasEnBilletera;
import luceroraul.stacktrace.examen.request.MonedaCriptoOperacionPeticion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("billeteras/operaciones")
public class BilleteraOperacion implements OperacionesDivisasEnBilletera {

    @PostMapping("depositar")
    @Override
    public ResponseEntity<Object> depositarCantidad(MonedaCriptoOperacionPeticion peticion) {
        return null;
    }

    @PostMapping("intercambiar")
    @Override
    public ResponseEntity<Object> intercambiarCantidad(MonedaCriptoOperacionPeticion Peticion) {
        return null;
    }
}

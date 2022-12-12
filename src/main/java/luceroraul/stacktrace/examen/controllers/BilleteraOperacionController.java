package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.controllers.divisas.OperacionesDivisasEnBilletera;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("billeteras/operaciones")
public class BilleteraOperacionController implements OperacionesDivisasEnBilletera {

    @PostMapping("depositar")
    @Override
    public ResponseEntity<Object> depositarCantidad(PeticionDeposito peticion) {
        return null;
    }

    @PostMapping("intercambiar")
    @Override
    public ResponseEntity<Object> intercambiarCantidad(PeticionIntercambio Peticion) {
        return null;
    }
}

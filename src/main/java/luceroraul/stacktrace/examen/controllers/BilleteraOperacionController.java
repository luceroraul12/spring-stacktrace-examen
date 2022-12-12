package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.controllers.divisas.OperacionesDivisasEnBilletera;
import luceroraul.stacktrace.examen.entities.BilleteraDto.ActivoDto;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.services.BilleteraActivoOperacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("billeteras/operaciones")
public class BilleteraOperacionController implements OperacionesDivisasEnBilletera {

    @Autowired
    BilleteraActivoOperacionesService billeteraActivoOperacionesService;

    @PostMapping("depositar")
    @Override
    public ResponseEntity<Respuesta<ActivoDto>> depositarCantidad(@RequestBody PeticionDeposito peticion) throws Exception {
        return billeteraActivoOperacionesService.depositarResultadoDto(peticion);
    }

    @PostMapping("intercambiar")
    @Override
    public ResponseEntity<Respuesta<Map<String, ActivoDto>>> intercambiarCantidad(@RequestBody PeticionIntercambio peticion) throws Exception {
        return billeteraActivoOperacionesService.intercambiarDto(peticion);
    }
}

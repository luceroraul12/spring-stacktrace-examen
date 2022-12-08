package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.Billetera;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("billeteras")
public class BilleteraController extends ControllerBase<Billetera>{
    //TODO: al modificar billeteras realiza la accion correctamente pero arroja un error relacionado a JSON
}

package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.Activo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("billeteras/activos")
public class ActivoController extends ControllerBase<Activo> {
}

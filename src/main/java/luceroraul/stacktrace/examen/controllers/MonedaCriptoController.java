package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.MonedaCriptoDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("divisas")
public class MonedaCriptoController extends ControllerBase<MonedaCripto, MonedaCriptoDTO>{
}

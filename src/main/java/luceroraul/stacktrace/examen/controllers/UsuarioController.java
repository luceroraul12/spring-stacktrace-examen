package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.Usuario;
import luceroraul.stacktrace.examen.entities.UsuarioDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController extends ControllerBase<Usuario, UsuarioDTO>{
}

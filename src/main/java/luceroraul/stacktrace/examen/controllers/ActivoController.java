package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.ActivoDTO;
import luceroraul.stacktrace.examen.responses.Respuesta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("billeteras/activos")
public class ActivoController extends ControllerBase<Activo, ActivoDTO> {

    /**
     * @param elemento dto con informacion necesaria para crear. seteo cantidad 0.0
     * @return
     */
    @Override
    public ResponseEntity<Respuesta.Body> crear(@RequestBody ActivoDTO elemento) {
        elemento.setCantidadAdquirida(0.0);
        return super.crear(elemento);
    }
}

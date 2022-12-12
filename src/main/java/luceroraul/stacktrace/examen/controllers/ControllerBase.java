package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.Identificable;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.services.ServiceABM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public abstract class ControllerBase<Entidad extends Identificable> {

    @Autowired
    private ServiceABM<Entidad> serviceABM;

    @PostMapping("alta")
    public ResponseEntity<Body> crear(@RequestBody Entidad elemento){
        return serviceABM.crear(elemento);
    }

    @PatchMapping("modificar")
    public ResponseEntity<Body> modificar(@RequestBody Map<String,Object> elemento) throws Exception {
        return serviceABM.modificar(elemento);
    }

    @DeleteMapping("baja")
    public ResponseEntity<Body> eliminar(@RequestBody Entidad elemento){
        return  serviceABM.eliminar(elemento);
    }

}

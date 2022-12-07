package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.Identificable;
import luceroraul.stacktrace.examen.services.ServiceABM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class ControllerBase<Entidad extends Identificable> {

    @Autowired
    private ServiceABM<Entidad> serviceABM;

    @PostMapping("alta")
    public ResponseEntity<Object> crear(@RequestBody Entidad elemento){
        return serviceABM.crear(elemento);
    }

    @PatchMapping("modificar")
    public ResponseEntity<Object> modificar(@RequestBody Entidad elemento){
        return serviceABM.modificar(elemento);
    }

    @PostMapping("baja")
    public ResponseEntity<Object> eliminar(@RequestBody Entidad elemento){
        return  serviceABM.eliminar(elemento);
    }

}
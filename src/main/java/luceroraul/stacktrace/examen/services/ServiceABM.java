package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Identificable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ServiceABM<Entidad extends Identificable> {
    @Autowired
    protected JpaRepository<Entidad, Long> repository;

    public ResponseEntity<Object> crear(Entidad elemento){
        ResponseEntity<Object> respuesta;
        Entidad elementoCreado = repository.save(elemento);
        if(!repository.existsById(elemento.getId())){
            respuesta = new ResponseEntity<>("error al intentar crear: "+elemento.getClass().getName(),HttpStatus.ACCEPTED);
        } else {
            respuesta = new ResponseEntity<>(elementoCreado,HttpStatus.OK);
        }

        return respuesta;
    }
    public ResponseEntity<Object> eliminar(Entidad elemento){
        ResponseEntity<Object> respuesta;
        String nombre = elemento.getClass().getName();
        Long id = elemento.getId();;
        repository.deleteById(id);
        if (repository.existsById(id)){
            respuesta = new ResponseEntity<>("error al intentar borrar el "+nombre+ " con id: "+id,HttpStatus.ACCEPTED);
        } else {
            respuesta = new ResponseEntity<>(nombre+"con id: "+id+ " eliminado", HttpStatus.OK);
        }
        return respuesta;
    }

    public ResponseEntity<Object> modificar(Entidad elemento){
        ResponseEntity<Object> respuesta;
        Entidad elementoModificado = repository.save(elemento);
        if(elemento.getId() != elementoModificado.getId()){
            respuesta = new ResponseEntity<>("error al intentar modificar", HttpStatus.ACCEPTED);
        } else {
            respuesta = new ResponseEntity<>(elementoModificado, HttpStatus.OK);
        }
        return respuesta;
    }
}

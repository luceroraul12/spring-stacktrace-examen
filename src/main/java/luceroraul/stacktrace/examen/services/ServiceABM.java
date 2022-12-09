package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Identificable;
import luceroraul.stacktrace.examen.util.ConvertidorParaActualizarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public abstract class ServiceABM<Entidad extends Identificable> {
    @Autowired
    protected JpaRepository<Entidad, Long> repository;

    @Autowired
    private ConvertidorParaActualizarUtil convertidor;

    protected ServiceABM() {
    }

    public ResponseEntity<Object> crear(Entidad elemento){
        ResponseEntity<Object> respuesta;
        Entidad elementoCreado = repository.save(elemento);
        if(!repository.existsById(elemento.getId())){
            respuesta = new ResponseEntity<>(
                    "error al intentar crear: "+elemento.getClass().getName(),
                    HttpStatus.ACCEPTED);
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
            respuesta = new ResponseEntity<>(
                    "error al intentar borrar el "+nombre+ " con id: "+id,
                    HttpStatus.ACCEPTED);
        } else {
            respuesta = new ResponseEntity<>(nombre+" con id: "+id+ " eliminado", HttpStatus.OK);
        }
        return respuesta;
    }

    public ResponseEntity<Object> modificar(Map<String,Object> elementoParcial) throws Exception {
        ResponseEntity<Object> respuesta;
        Long id = Long.parseLong(String.valueOf(elementoParcial.get("id")));

        repository.existsById(id);
        Entidad elementoAlmacenado = repository.findById(id).orElseThrow(Exception::new);
        Entidad elementoModificadoParaGuardar = convertidor.modificarEntidad(
                elementoParcial,
                elementoAlmacenado,
                recuperarClaseGenerica()
                );

        repository.save(elementoModificadoParaGuardar);
        respuesta = new ResponseEntity<>(elementoModificadoParaGuardar, HttpStatus.OK);
        return respuesta;
    }

    protected abstract Class<Entidad> recuperarClaseGenerica();

}

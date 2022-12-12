package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Identificable;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.util.BaseUtil;
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
    protected BaseUtil<Entidad> baseUtil;

    @Autowired
    private ConvertidorParaActualizarUtil convertidor;

    protected ServiceABM() {
    }

    public ResponseEntity<Body> crear(Entidad elemento){
        Respuesta respuesta;
        elemento = repository.save(elemento);
        if(!repository.existsById(elemento.getId())){
            respuesta = new Respuesta(null, "el id del "+elemento.getClass().getSimpleName()+" ya existe", HttpStatus.ACCEPTED);
        } else {
            respuesta = new Respuesta(elemento, "Creacion realizada con exito", HttpStatus.OK);
        }
        return respuesta.getResponseEntity();
    }
    public ResponseEntity<Body> eliminar(Entidad elemento){
        Respuesta respuesta;
        String nombre = elemento.getClass().getSimpleName();
        Long id = elemento.getId();;
        repository.deleteById(id);
        if (repository.existsById(id)){
            respuesta = new Respuesta(
                    null,
                    "error al intentar borrar el "+nombre+ " con id: "+id,
                    HttpStatus.ACCEPTED);
        } else {
            respuesta = new Respuesta(
                    baseUtil.convertirToDTO(elemento),
                    nombre+" con id: "+id+ " eliminado",
                    HttpStatus.OK);
        }
        return respuesta.getResponseEntity();
    }

    public ResponseEntity<Body> modificar(Map<String,Object> elementoParcial) throws Exception {
        Respuesta respuesta;
        Long id = Long.parseLong(String.valueOf(elementoParcial.get("id")));

        if (repository.existsById(id)){
            Entidad elementoAlmacenado = repository.findById(id).get();
            Entidad elementoModificadoParaGuardar = convertidor.modificarEntidad(
                    elementoParcial,
                    elementoAlmacenado,
                    recuperarClaseGenerica()
            );
            elementoAlmacenado = repository.save(elementoModificadoParaGuardar);
            respuesta = new Respuesta(
                    elementoAlmacenado,
                    "Elemento creado con exito",
                    HttpStatus.OK);
        } else {
            respuesta = new Respuesta(
                    null,
                    "Error al modificar, no existe elemento con dicho id",
                    HttpStatus.ACCEPTED);
        }
        return respuesta.getResponseEntity();
    }

    protected abstract Class<Entidad> recuperarClaseGenerica();

}

package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.BaseDTO;
import luceroraul.stacktrace.examen.entities.Identificable;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ServiceABM<Entidad extends Identificable, ClaseDTO extends BaseDTO> {
    @Autowired
    protected JpaRepository<Entidad, Long> repository;

    @Autowired
    protected BaseUtil<Entidad, ClaseDTO> baseUtil;
    protected ServiceABM() {
    }

    public ResponseEntity<Body> crear(ClaseDTO elemento){
        Respuesta respuesta;
        if(!cumpleCondicionDeCreacion(elemento)){
            respuesta = new Respuesta(null, "el id del "+elemento.getClass().getSimpleName()+" ya existe", HttpStatus.ACCEPTED);
        } else {
            Long id = repository.save(baseUtil.convertirToEntidad(elemento)).getId();
            elemento.setId(id);
            respuesta = new Respuesta(id, "Creacion realizada con exito. id de elemento: "+id, HttpStatus.OK);
        }
        return respuesta.getResponseEntity();
    }


    public ResponseEntity<Body> eliminar(ClaseDTO elemento){
        Respuesta respuesta;
        Entidad resultado;
        Long id = elemento.getId();
        repository.deleteById(id);
        if (repository.existsById(id)){
            respuesta = new Respuesta(
                    null,
                    "error al intentar borrar el elemento con id: "+id,
                    HttpStatus.ACCEPTED);
        } else {
            respuesta = new Respuesta(
                    id,
                    "elemento con id: "+id+ " eliminado",
                    HttpStatus.OK);
        }
        return respuesta.getResponseEntity();
    }

    public ResponseEntity<Body> modificar(ClaseDTO elementoParcial) throws Exception {
        Respuesta respuesta;
        Long id = elementoParcial.getId();

        if (repository.existsById(id)){
            Entidad elementoAlmacenado = repository.findById(id).get();
            Entidad elementoFusionado = baseUtil.fusionarDTOyEntidad(elementoAlmacenado, elementoParcial);
            elementoAlmacenado = repository.save(elementoFusionado);

            respuesta = new Respuesta(
                    baseUtil.convertirToDTO(elementoAlmacenado),
                    "Elemento modificado con exito",
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

    protected abstract boolean cumpleCondicionDeCreacion(ClaseDTO elemento);


}

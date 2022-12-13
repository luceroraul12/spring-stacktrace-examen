package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.BaseDTO;
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

public abstract class ServiceABM<Entidad extends Identificable, ClaseDTO extends BaseDTO> {
    @Autowired
    protected JpaRepository<Entidad, Long> repository;

    @Autowired
    protected BaseUtil<Entidad, ClaseDTO> baseUtil;

    @Autowired
    private ConvertidorParaActualizarUtil convertidor;

    protected ServiceABM() {
    }

    public ResponseEntity<Body> crear(ClaseDTO elemento){
        Respuesta respuesta;
        Entidad resultado;
        if(!cumpleCondicionDeCreacion(elemento)){
            respuesta = new Respuesta(null, "el id del "+elemento.getClass().getSimpleName()+" ya existe", HttpStatus.ACCEPTED);
        } else {
            Long id = repository.save(baseUtil.convertirToEntidad(elemento)).getId();
            elemento.setId(id);
            resultado = repository.findById(id).get();
            respuesta = new Respuesta(baseUtil.convertirToDTO(resultado), "Creacion realizada con exito", HttpStatus.OK);
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
            Entidad elementoAlmacenado = repository.save(baseUtil.convertirToEntidad(elementoParcial));
//            Entidad elementoAlmacenado = repository.findById(id).get();
//            Entidad elementoModificadoParaGuardar = convertidor.modificarEntidad(
//                    elementoParcial,
//                    elementoAlmacenado,
//                    recuperarClaseGenerica()
//            );
//            elementoAlmacenado = repository.save(elementoModificadoParaGuardar);
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

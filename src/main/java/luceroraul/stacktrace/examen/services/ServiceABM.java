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

/**
 * Servicio abstracto base para realizar alta, baja, modificacion de entidades
 * @param <Entidad> Clase utilizada con JPA
 * @param <ClaseDTO> Clase que se retorna al usuario del servicio
 */
public abstract class ServiceABM<Entidad extends Identificable, ClaseDTO extends BaseDTO> {
    /**
     * Implementacion de inyeccion de dependencia por medio del uso de genericos.
     * En este caso parten de JpaRepository para poder realizar ABM.
     */
    @Autowired
    protected JpaRepository<Entidad, Long> repository;

    /**
     * Clase utilitaria generica para cada implementacion. En ella se encontraran los metodos necesarios para hacer conversiones respecto a dicha entidad.
     */
    @Autowired
    protected BaseUtil<Entidad, ClaseDTO> baseUtil;
    protected ServiceABM() {
    }

    /**
     * Metodo encargado de hacer persistir el argumento de entrada.
     * tener en cuenta {@link ServiceABM#cumpleCondicionDeCreacion(BaseDTO)}
     * @param elemento es el objecto con los datos necesarios para hacer persistirlos.
     * @return ResponseEntity del tipo {@link Body}
     */
    public ResponseEntity<Body> crear(ClaseDTO elemento){
        Respuesta respuesta;
        if(!cumpleCondicionDeCreacion(elemento)){
            respuesta = new Respuesta(null, "error al crear elemento", HttpStatus.ACCEPTED);
        } else {
            Long id = repository.save(baseUtil.convertirToEntidad(elemento)).getId();
            elemento.setId(id);
            respuesta = new Respuesta(id, "Creacion realizada con exito. id de elemento: "+id, HttpStatus.OK);
        }
        return respuesta.getResponseEntity();
    }

    /**
     * Metodo encargado de eliminar un registro de la base de dato en funcion a su identificador
     * @param id identificador
     * @return ResponseEntity del tipo {@link Body}
     */
    public ResponseEntity<Body> eliminar(Long id){
        Respuesta respuesta;
        try {
            repository.deleteById(id);
            respuesta = new Respuesta(
                    id,
                    "elemento con id: "+id+ " eliminado",
                    HttpStatus.OK);
        } catch (Exception e) {
            respuesta = new Respuesta(
                    null,
                    "error al intentar borrar el elemento con id: "+id,
                    HttpStatus.ACCEPTED);
        }
        return respuesta.getResponseEntity();
    }

    /**
     * Metodo encargado de realizar la modificacion de un registro de la base de datos.
     * El argumento puede ser con datos completos o parcial. Esto se debe a que primero va a buscar el registro y luego lo fusionara con la informacion del argumento.
     * Tener en cuenta {@link BaseUtil#fusionarDTOyEntidad(Object, Object)}
     * @param elementoParcial dato completo o parcial a guardar
     * @return ResponseEntity del tipo {@link Body}
     */
    public ResponseEntity<Body> modificar(ClaseDTO elementoParcial){
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

    /**
     * Metodo abstracto a implementar por las implementaciones donde se debe verificar si la informacion de entrada es valida para ser persistida en la base de datos.
     * @param elemento objeto de entrada
     * @return si cumple o no para ser creado
     */
    protected abstract boolean cumpleCondicionDeCreacion(ClaseDTO elemento);


}

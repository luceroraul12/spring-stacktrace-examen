package luceroraul.stacktrace.examen.util;

/**
 * Clase abstracta base para las utilidades
 * @param <Entidad> entidad JPA
 * @param <ClaseDTO> Clase que se le muestra al usuario
 */
public abstract class BaseUtil<Entidad, ClaseDTO> {

    /**
     * Convierte la entidad en su forma DTO
     * @param elemento contiene los datos extraidos de la base de datos
     * @return
     */
    public abstract ClaseDTO convertirToDTO(Entidad elemento);

    /**
     * Convierte el DTO en Entidad.
     * Solo se completa con los datos que son persistible en la base de datos
     * @param elemento informacion para persistir
     * @return
     */
    public abstract Entidad convertirToEntidad(ClaseDTO elemento);

    /**
     * Fusiona una entidad con una clase DTO
     * @param elementoAlmacenado contiene los datos de la base de datos
     * @param elementoParcial contiene todos los datos o parcial de los que existian en la base de datos
     * @return entidad que prioriza datos parciales > datos almacenados
     */
    public abstract Entidad fusionarDTOyEntidad(Entidad elementoAlmacenado, ClaseDTO elementoParcial);

    /**
     * Metodo utilizado para mantener informacion no null.
     * @param parametroAlmacenado info de la base de datos
     * @param parametroParcial info de entrada
     * @return siempre entrada > base de datos cuando exista, de lo contrario, la que se encuentra en la base de datos
     * @param <Tipo>
     */
    public <Tipo> Tipo elegirParametroNoNull(Tipo parametroAlmacenado, Tipo parametroParcial){
        return parametroParcial == null ? parametroAlmacenado : parametroParcial;
    }
}

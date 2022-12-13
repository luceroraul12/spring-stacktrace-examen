package luceroraul.stacktrace.examen.util;

public abstract class BaseUtil<Entidad, ClaseDTO> {

    public abstract ClaseDTO convertirToDTO(Entidad elemento);

    public abstract Entidad convertirToEntidad(ClaseDTO elemento);

    public abstract Entidad fusionarDTOyEntidad(Entidad elementoAlmacenado, ClaseDTO elementoParcial);

    public <Tipo> Tipo elegirParametroNoNull(Tipo parametroAlmacenado, Tipo parametroParcial){
        return parametroParcial == null ? parametroAlmacenado : parametroParcial;
    }
}

package luceroraul.stacktrace.examen.util;


import luceroraul.stacktrace.examen.entities.BaseDTO;

public abstract class BaseUtil<Entidad, ClaseDTO> {

    public abstract BaseDTO convertirToDTO(Entidad elemento);

    public abstract Entidad convertirToEntidad(ClaseDTO elemento);
}

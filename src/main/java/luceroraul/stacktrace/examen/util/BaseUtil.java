package luceroraul.stacktrace.examen.util;


import luceroraul.stacktrace.examen.entities.BaseDTO;

public abstract class BaseUtil<EntidadOriginal> {

    public abstract BaseDTO convertirToDTO(EntidadOriginal elemento);
}

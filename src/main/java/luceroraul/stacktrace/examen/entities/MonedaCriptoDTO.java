package luceroraul.stacktrace.examen.entities;

import javax.persistence.Column;

public class MonedaCriptoDTO extends BaseDTO{
    private String nombre;
    private Double relacionPeso;

    public MonedaCriptoDTO(Long id, String nombre, Double relacionPeso) {
        super(id);
        this.nombre = nombre;
        this.relacionPeso = relacionPeso;
    }
}

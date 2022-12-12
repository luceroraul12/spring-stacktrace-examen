package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Column;

public class MonedaCriptoDTO extends BaseDTO{
    private String nombre;
    private Double relacionPeso;

    public MonedaCriptoDTO(Long id, String nombre, Double relacionPeso) {
        super(id);
        this.nombre = nombre;
        this.relacionPeso = relacionPeso;
    }
}

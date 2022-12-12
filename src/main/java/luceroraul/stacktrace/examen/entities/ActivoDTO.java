package luceroraul.stacktrace.examen.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActivoDTO extends BaseDTO implements Serializable {
    private String monedaCriptoNombre;
    private Double cantidadAdquirida;

    public ActivoDTO(Long id, String monedaCriptoNombre, Double cantidadAdquirida) {
        super(id);
        this.monedaCriptoNombre = monedaCriptoNombre;
        this.cantidadAdquirida = cantidadAdquirida;
    }
}
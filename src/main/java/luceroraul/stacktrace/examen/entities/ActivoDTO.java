package luceroraul.stacktrace.examen.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActivoDTO extends BaseDTO implements Serializable {
    private Long idMonedaCripto;
    private String monedaCriptoNombre;
    private Double cantidadAdquirida;

    public ActivoDTO(Long id, Long idMonedaCripto, String monedaCriptoNombre, Double cantidadAdquirida) {
        super(id);
        this.idMonedaCripto = idMonedaCripto;
        this.monedaCriptoNombre = monedaCriptoNombre;
        this.cantidadAdquirida = cantidadAdquirida;
    }
}
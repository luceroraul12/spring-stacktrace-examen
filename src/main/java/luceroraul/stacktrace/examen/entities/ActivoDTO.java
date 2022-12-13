package luceroraul.stacktrace.examen.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActivoDTO extends BaseDTO implements Serializable {
    private Long idBilletera;
    private Long idMonedaCripto;
    private String monedaCriptoNombre;
    private Double cantidadAdquirida;

    public ActivoDTO(Long id, Long idBilletera, Long idMonedaCripto, String monedaCriptoNombre, Double cantidadAdquirida) {
        super(id);
        this.idBilletera = idBilletera;
        this.idMonedaCripto = idMonedaCripto;
        this.monedaCriptoNombre = monedaCriptoNombre;
        this.cantidadAdquirida = cantidadAdquirida;
    }
}
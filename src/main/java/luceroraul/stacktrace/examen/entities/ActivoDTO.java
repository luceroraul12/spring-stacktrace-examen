package luceroraul.stacktrace.examen.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActivoDTO extends BaseDTO implements Serializable {
    @Schema(description = "obligatorio al dar de alta y opcional al modificar")
    private Long idBilletera;
    @Schema(description = "oblicatorio al dar de alta y opcional al modificar")
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
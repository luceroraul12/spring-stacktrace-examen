package luceroraul.stacktrace.examen.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class MonedaCriptoDTO extends BaseDTO{
    @Schema(description = "obligatorio al dar de alta y opcional al modificar")
    private String nombre;
    @Schema(description = "obligatorio al dar de alta y opcional al modificar")
    private Double relacionPeso;

    public MonedaCriptoDTO(Long id, String nombre, Double relacionPeso) {
        super(id);
        this.nombre = nombre;
        this.relacionPeso = relacionPeso;
    }
}

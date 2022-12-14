package luceroraul.stacktrace.examen.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Billetera} entity
 */
@Getter
@Setter
public class BilleteraDto extends BaseDTO implements Serializable {
    @Schema(description = "debe contener id")
    private UsuarioDTO usuario;
    private List<ActivoDTO> activos;

    public BilleteraDto(Long id, UsuarioDTO usuario, List<ActivoDTO> activos) {
        super(id);
        this.usuario = usuario;
        this.activos = activos;
    }
}
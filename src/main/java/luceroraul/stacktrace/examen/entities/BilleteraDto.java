package luceroraul.stacktrace.examen.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Billetera} entity
 */
@Data
public class BilleteraDto implements Serializable {
    private final Long id;
    private final List<ActivoDto> activos;

    /**
     * A DTO for the {@link Activo} entity
     */
    @Data
    public static class ActivoDto implements Serializable {
        private final Long id;
        private final String monedaCriptoNombre;
        private final Double cantidadAdquirida;
    }
}
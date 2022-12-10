package luceroraul.stacktrace.examen.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import luceroraul.stacktrace.examen.entities.OperacionTipo;

@Getter
@Setter
@AllArgsConstructor
public class PeticionDeposito {
    private Long idActivoOrigen;
    private Double cantidadOperable;
}

package luceroraul.stacktrace.examen.request;

import lombok.Getter;
import lombok.Setter;
import luceroraul.stacktrace.examen.entities.Operacion.OperacionTipo;

@Getter
@Setter
public class PeticionIntercambio extends PeticionDeposito{
    private Long idActivoOrigen;

    public PeticionIntercambio(OperacionTipo operacionTipo, Double cantidadOperable, Long idActivoDestino, Long idActivoOrigen) {
        super(operacionTipo, cantidadOperable, idActivoDestino);
        this.idActivoOrigen = idActivoOrigen;
    }
}

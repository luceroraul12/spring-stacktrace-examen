package luceroraul.stacktrace.examen.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeticionIntercambio extends PeticionDeposito{
    private Long idActivoDestino;

    public PeticionIntercambio(Long idActivoOrigen, Long idActivoDestino, Double cantidadOperable ) {
        super(idActivoOrigen, cantidadOperable);
        this.idActivoDestino = idActivoDestino;
    }
}

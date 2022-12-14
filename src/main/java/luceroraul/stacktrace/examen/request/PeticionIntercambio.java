package luceroraul.stacktrace.examen.request;

import lombok.Getter;
import lombok.Setter;
import luceroraul.stacktrace.examen.entities.Operacion.OperacionTipo;

/**
 * Peticion necesaria para realizar un intercambio de monedas. Extiende de {@link PeticionDeposito} debido a que necesita los mismos atributos.
 */
@Getter
@Setter
public class PeticionIntercambio extends PeticionDeposito{
    /**
     * En este caso, se le agrega el identificador del {@link luceroraul.stacktrace.examen.entities.Activo} de donde debe descontarse dicha cantidad.
     */
    private Long idActivoOrigen;

    public PeticionIntercambio(OperacionTipo operacionTipo, Double cantidadOperable, Long idActivoDestino, Long idActivoOrigen) {
        super(operacionTipo, cantidadOperable, idActivoDestino);
        this.idActivoOrigen = idActivoOrigen;
    }
}

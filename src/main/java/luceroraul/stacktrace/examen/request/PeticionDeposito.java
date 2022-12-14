package luceroraul.stacktrace.examen.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import luceroraul.stacktrace.examen.entities.Operacion.OperacionTipo;

/**
 * Clase con informacion necesaria para realizar un deposito.
 */
@Getter
@Setter
@AllArgsConstructor
public class PeticionDeposito {
    /**
     * Es el tipo de operacion. Ver {@link OperacionTipo}.
     * Como la API fue diseniada para distingir DEPOSITO de INTERCAMBIO, no es un dato obligatorio cuando se realice una peticion. Solo es util para el sistema al a hora de persisitr, debido a que el mismo lo vuelve a asignar previamente de manera automatica.
     */
    private OperacionTipo operacionTipo;
    /**
     * Es la cantidad que se desea introducir
     */
    private Double cantidadOperable;
    /**
     * Es el identificador del {@link luceroraul.stacktrace.examen.entities.Activo} al que se le quiene depositar
     */
    private Long idActivoDestino;
}

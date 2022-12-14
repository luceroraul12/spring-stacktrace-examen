package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import org.springframework.stereotype.Component;

/**
 * Util enfocado actividades necesarioas para la Operacion de {@link Activo} relacionado a pesos
 */
@Component
public class BilleteraOperacionUtil {

    /**
     * Incrementa la cantidad del {@link Activo} cuando {@link luceroraul.stacktrace.examen.entities.MonedaCripto} es la misma
     * @param activo
     * @param cantidad
     * @return
     */
    public Activo realizarIncrementoMismaUnidad(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() + cantidad);
        return activo;
    }

    /**
     * Incrementa la cantidad del {@link Activo} cuando el activo de origen se encuentra con {@link luceroraul.stacktrace.examen.entities.MonedaCripto} diferente
     * @param activoOrigen
     * @param activoDestino
     * @param cantidadParaIncrementarOriginal
     * @return
     */
    public Activo realizarIncrementoDiferentesUnidades(Activo activoOrigen, Activo activoDestino, Double cantidadParaIncrementarOriginal){
        Double precioUnitarioMonedaOrigenEnPesos = activoOrigen.getMonedaCripto().getRelacionPesos();
        Double precioUnitarioMonedaDestinoEnPesos = activoDestino.getMonedaCripto().getRelacionPesos();
        Double cantidadParaIncrementarEnPesos = precioUnitarioMonedaOrigenEnPesos * cantidadParaIncrementarOriginal;
        Double cantidadParaIncrementarEnMonedaDestino = cantidadParaIncrementarEnPesos / precioUnitarioMonedaDestinoEnPesos;

        activoDestino.setCantidadAdquirida(activoDestino.getCantidadAdquirida() + cantidadParaIncrementarEnMonedaDestino);

        return activoDestino;
    }

    /**
     * Resta cierta cantidad al Activo
     * @param activo
     * @param cantidad
     * @return
     */
    public Activo realizarReduccion(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() - cantidad);
        return activo;
    }

    /**
     * Verifica de que el monto sea mayor que la cantidad a restar
     * @param activo
     * @param cantidad
     * @return
     */
    public boolean tieneMontoSuficiente(Activo activo, Double cantidad) {
        return activo.getCantidadAdquirida() >= cantidad;
    }

    /**
     * Comprueba que ambos activos tiene la misma {@link luceroraul.stacktrace.examen.entities.MonedaCripto} asignada
     * @param activoOrigen
     * @param activoDestino
     * @return
     */
    public boolean sonSobreMismaMonedaCripto(Activo activoOrigen, Activo activoDestino) {
        return activoOrigen.getMonedaCripto().equals(activoDestino.getMonedaCripto());
    }
}

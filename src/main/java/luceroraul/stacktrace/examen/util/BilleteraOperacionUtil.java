package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import org.springframework.stereotype.Component;

@Component
public class BilleteraOperacionUtil {
    public Activo realizarIncrementoMismaUnidad(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() + cantidad);
        return activo;
    }

    public Activo realizarIncrementoDiferentesUnidades(Activo activoOrigen, Activo activoDestino, Double cantidadParaIncrementarOriginal){
        Double precioUnitarioMonedaOrigenEnPesos = activoOrigen.getMonedaCripto().getRelacionDolar();
        Double precioUnitarioMonedaDestinoEnPesos = activoDestino.getMonedaCripto().getRelacionDolar();
        Double cantidadParaIncrementarEnPesos = precioUnitarioMonedaOrigenEnPesos * cantidadParaIncrementarOriginal;
        Double cantidadParaIncrementarEnMonedaDestino = cantidadParaIncrementarEnPesos / precioUnitarioMonedaDestinoEnPesos;

        activoDestino.setCantidadAdquirida(activoDestino.getCantidadAdquirida() + cantidadParaIncrementarEnMonedaDestino);

        return activoDestino;
    }

    public Activo realizarReduccion(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() - cantidad);
        return activo;
    }

    public boolean tieneMontoSuficiente(Activo activo, Double cantidad) {
        return activo.getCantidadAdquirida() >= cantidad;
    }
}

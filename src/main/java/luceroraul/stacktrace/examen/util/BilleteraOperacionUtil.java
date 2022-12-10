package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import org.springframework.stereotype.Component;

@Component
public class BilleteraOperacionUtil {
    public Activo realizarIncremento(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() + cantidad);
        return activo;
    }

    public Activo realizarReduccion(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() - cantidad);
        return activo;
    }

    public boolean tieneMontoSuficiente(Activo activo, Double cantidad) {
        return activo.getCantidadAdquirida() >= cantidad;
    }
}

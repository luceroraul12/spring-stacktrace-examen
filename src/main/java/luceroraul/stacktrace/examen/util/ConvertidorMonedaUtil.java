package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import org.springframework.stereotype.Component;

@Component
public class ConvertidorMonedaUtil {
    public Double obtenerActivoEnPesos(MonedaCripto monedaCripto, Activo activo){
        return monedaCripto.getRelacionDolar() * activo.getCantidadAdquirida();
    }
}

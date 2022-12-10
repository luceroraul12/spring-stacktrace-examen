package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertidorMonedaUtil {
    public Double obtenerActivoEnPesos(Activo activo){
        return activo.getMonedaCripto().getRelacionDolar() * activo.getCantidadAdquirida();
    }

    public Double obtenerTotalDeActivosEnPesos(List<Activo> activos){
        return activos.stream()
                .map(this::obtenerActivoEnPesos)
                .reduce(Double::sum).get();
    }

}

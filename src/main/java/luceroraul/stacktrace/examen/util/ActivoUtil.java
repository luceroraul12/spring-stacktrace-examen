package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.BilleteraDto.ActivoDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActivoUtil {

    public ActivoDto convertirDTO(Activo activo){
        return new ActivoDto(
                activo.getId(),
                activo.getMonedaCripto().getNombre(),
                activo.getCantidadAdquirida());
    }

    public Map<String, ActivoDto> convertirMapDto(Map<String, Activo> activoMap){
        Map<String, ActivoDto> resultado = new HashMap<>();
        activoMap.forEach((llave, act) -> resultado.put(llave, convertirDTO(act)));
        return resultado;
    }
}

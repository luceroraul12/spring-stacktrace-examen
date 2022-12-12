package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.ActivoDTO;
import luceroraul.stacktrace.examen.entities.BaseDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActivoUtil extends BaseUtil<Activo> {

    public ActivoDTO convertirDTO(Activo activo){
        return new ActivoDTO(
                activo.getId(),
                activo.getMonedaCripto().getNombre(),
                activo.getCantidadAdquirida());
    }

    public Map<String, ActivoDTO> convertirMapDto(Map<String, Activo> activoMap){
        Map<String, ActivoDTO> resultado = new HashMap<>();
        activoMap.forEach((llave, act) -> resultado.put(llave, convertirDTO(act)));
        return resultado;
    }

    @Override
    public BaseDTO convertirToDTO(Activo elemento) {
        return new ActivoDTO(
                elemento.getId(),
                elemento.getMonedaCripto().getNombre(),
                elemento.getCantidadAdquirida());
    }
}

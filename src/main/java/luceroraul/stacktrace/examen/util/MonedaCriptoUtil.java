package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.BaseDTO;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.MonedaCriptoDTO;
import org.springframework.stereotype.Component;

@Component
public class MonedaCriptoUtil extends BaseUtil<MonedaCripto>{
    @Override
    public BaseDTO convertirToDTO(MonedaCripto elemento) {
        return new MonedaCriptoDTO(
                elemento.getId(),
                elemento.getNombre(),
                elemento.getRelacionDolar()
        );
    }
}

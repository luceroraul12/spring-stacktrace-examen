package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.MonedaCriptoDTO;
import org.springframework.stereotype.Component;

@Component
public class MonedaCriptoUtil extends BaseUtil<MonedaCripto, MonedaCriptoDTO>{
    @Override
    public MonedaCriptoDTO convertirToDTO(MonedaCripto elemento) {
        return new MonedaCriptoDTO(
                elemento.getId(),
                elemento.getNombre(),
                elemento.getRelacionDolar()
        );
    }

    @Override
    public MonedaCripto convertirToEntidad(MonedaCriptoDTO elemento) {
        return MonedaCripto.builder()
                .nombre(elemento.getNombre())
                .relacionDolar(elemento.getRelacionPeso())
                .build();
    }

    @Override
    public MonedaCripto fusionarDTOyEntidad(MonedaCripto elementoAlmacenado, MonedaCriptoDTO elementoParcial) {
        return MonedaCripto.builder()
                .id(elementoAlmacenado.getId())
                .nombre(elegirParametroNoNull(elementoAlmacenado.getNombre(), elementoParcial.getNombre()))
                .relacionDolar(elegirParametroNoNull(elementoAlmacenado.getRelacionDolar(), elementoParcial.getRelacionPeso()))
                .build();
    }
}

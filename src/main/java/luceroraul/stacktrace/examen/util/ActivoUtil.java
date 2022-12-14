package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.ActivoDTO;
import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActivoUtil extends BaseUtil<Activo, ActivoDTO> {


    public Map<String, ActivoDTO> convertirMapDto(Map<String, Activo> activoMap){
        Map<String, ActivoDTO> resultado = new HashMap<>();
        activoMap.forEach((llave, act) -> resultado.put(llave, convertirToDTO(act)));
        return resultado;
    }

    @Override
    public ActivoDTO convertirToDTO(Activo elemento) {
        return new ActivoDTO(
                elemento.getId(),
                elemento.getBilletera().getId(),
                elemento.getMonedaCripto().getId(),
                elemento.getMonedaCripto().getNombre(),
                elemento.getCantidadAdquirida());
    }

    @Override
    public Activo convertirToEntidad(ActivoDTO elemento) {
        return Activo.builder()
                .id(elemento.getId())
                .billetera(Billetera.builder()
                        .id(elemento.getIdBilletera())
                        .build())
                .cantidadAdquirida(elemento.getCantidadAdquirida())
                .monedaCripto(
                        MonedaCripto.builder()
                                .id(elemento.getIdMonedaCripto())
                                .build())
                .build();
    }

    @Override
    public Activo fusionarDTOyEntidad(Activo elementoAlmacenado, ActivoDTO elementoParcial) {
        return Activo.builder()
                .id(elegirParametroNoNull(elementoAlmacenado.getId(),elementoParcial.getId()))
                .monedaCripto(
                        MonedaCripto.builder()
                                .id(elegirParametroNoNull(
                                        elementoAlmacenado.getMonedaCripto().getId(),
                                        elementoParcial.getIdMonedaCripto()))
                                .build())
                .billetera(
                        Billetera.builder()
                                .id(elegirParametroNoNull(
                                        elementoAlmacenado.getBilletera().getId(),
                                        elementoParcial.getIdBilletera()))
                                .build()
                )
                .cantidadAdquirida(elegirParametroNoNull(elementoAlmacenado.getCantidadAdquirida(),elementoParcial.getCantidadAdquirida()))
                .build();
    }
}

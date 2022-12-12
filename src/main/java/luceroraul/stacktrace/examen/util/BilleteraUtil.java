package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BilleteraUtil extends BaseUtil<Billetera>{

    @Autowired
    ActivoUtil activoUtil;

    public List<BilleteraDto> convertirVariasbilleterasaDTO(List<Billetera> billeteras){
        return billeteras
                .stream()
                .map(this::convertirBilleteraaDTO)
                .collect(Collectors.toList());
    }

    public BilleteraDto convertirBilleteraaDTO(Billetera billetera){
        return new BilleteraDto(
                billetera.getId(),
                billetera.getActivos()
                        .stream()
                        .map(activo -> (ActivoDTO) activoUtil.convertirToDTO(activo))
                        .collect(Collectors.toList())
        );
    }

    public ActivoDTO convertirActivoaDTO(Activo activo){
        return new ActivoDTO(
                activo.getId(),
                activo.getMonedaCripto().getNombre(),
                activo.getCantidadAdquirida()
        );
    }

    public Double obtenerEquivalenciaActivoEnPesos(Activo activo){
        return activo.getCantidadAdquirida() * activo.getMonedaCripto().getRelacionDolar();
    }

    public Double obtenerSaldoBilleteraEnPesos(List<Billetera> billeteras){
        return billeteras.stream()
                .map(Billetera::getActivos)
                .flatMap(List::stream)
                .map(this::obtenerEquivalenciaActivoEnPesos)
                .reduce(Double::sum).get();
    }

    @Override
    public BaseDTO convertirToDTO(Billetera elemento) {
        return new BilleteraDto(
                elemento.getId(),
                elemento.getActivos().stream()
                        .map(act -> (ActivoDTO) activoUtil.convertirToDTO(act))
                        .collect(Collectors.toList())
        );
    }
}

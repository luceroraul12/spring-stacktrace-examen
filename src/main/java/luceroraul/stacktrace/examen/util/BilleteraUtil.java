package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.BilleteraDto;
import luceroraul.stacktrace.examen.entities.BilleteraDto.ActivoDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BilleteraUtil {

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
                        .map(this::convertirActivoaDTO)
                        .collect(Collectors.toList())
        );
    }

    public ActivoDto convertirActivoaDTO(Activo activo){
        return new ActivoDto(
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
}

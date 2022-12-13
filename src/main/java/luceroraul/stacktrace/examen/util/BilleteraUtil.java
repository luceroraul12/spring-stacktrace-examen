package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BilleteraUtil extends BaseUtil<Billetera, BilleteraDto>{

    @Autowired
    ActivoUtil activoUtil;

    @Autowired
    UsuarioUtil usuarioUtil;

    public List<BilleteraDto> convertirVariasbilleterasaDTO(List<Billetera> billeteras){
        return billeteras
                .stream()
                .map(this::convertirBilleteraaDTO)
                .collect(Collectors.toList());
    }

    public BilleteraDto convertirBilleteraaDTO(Billetera billetera){
        return new BilleteraDto(
                billetera.getId(),
                usuarioUtil.convertirToDTO(billetera.getUsuario()),
                billetera.getActivos()
                        .stream()
                        .map(activo -> activoUtil.convertirToDTO(activo))
                        .collect(Collectors.toList())
        );
    }

    public ActivoDTO convertirActivoaDTO(Activo activo){
        return new ActivoDTO(
                activo.getId(),
                activo.getMonedaCripto().getId(),
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
    public BilleteraDto convertirToDTO(Billetera elemento) {
        return new BilleteraDto(
                elemento.getId(),
                usuarioUtil.convertirToDTO(elemento.getUsuario()),
                elemento.getActivos().stream()
                        .map(act -> (ActivoDTO) activoUtil.convertirToDTO(act))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Billetera convertirToEntidad(BilleteraDto elemento) {
        return Billetera.builder()
                .id(elemento.getId())
                .usuario(Usuario.builder()
                        .id(elemento.getUsuario().getId())
                        .build())
                .activos(
                        elemento.getActivos() != null
                                ? elemento.getActivos().stream()
                                        .map(activoDTO -> activoUtil.convertirToEntidad(activoDTO))
                                        .collect(Collectors.toList())
                                : null)
                .build();
    }

    @Override
    public Billetera fusionarDTOyEntidad(Billetera elementoAlmacenado, BilleteraDto elementoParcial) {
        return Billetera.builder()
                .id(elementoAlmacenado.getId())
                .usuario(Usuario.builder()
                        .id(elegirParametroNoNull(elementoAlmacenado.getUsuario().getId(), elementoParcial.getUsuario().getId()))
                        .build())
                .activos(elementoAlmacenado.getActivos())
                .build();
    }
}

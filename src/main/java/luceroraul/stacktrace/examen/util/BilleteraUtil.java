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

    /**
     * Convierte un conjunto de Billeteras a DTO
     * @param billeteras extraidas de la base de dato
     * @return Billeteras convertidas a DTO
     */
    public List<BilleteraDto> convertirVariasbilleterasaDTO(List<Billetera> billeteras){
        return billeteras
                .stream()
                .map(this::convertirToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Realiza la conversion de la cantidad de dicha moneda a pesos.
     * multiplica la cantidad por el precio unitario de la moneda
     * @param activo extraido de la base de datos
     * @return resultado en pesos
     */
    public Double obtenerEquivalenciaActivoEnPesos(Activo activo){
        return activo.getCantidadAdquirida() * activo.getMonedaCripto().getRelacionDolar();
    }

    /**
     * Realiza la conversion en pesos de todas las billeteras.
     * Tener en cuenta {@link BilleteraUtil#obtenerEquivalenciaActivoEnPesos(Activo)}
     * @param billeteras extraidas de la base de datos
     * @return resultado en pesos
     */
    public Double obtenerSaldoBilleteraEnPesos(List<Billetera> billeteras){
        Double resultado;
        try {
            resultado = billeteras.stream()
                    .map(Billetera::getActivos)
                    .flatMap(List::stream)
                    .map(this::obtenerEquivalenciaActivoEnPesos)
                    .reduce(Double::sum).get();
        } catch (Exception e){
            resultado = 0.0;
        }
        return resultado;
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

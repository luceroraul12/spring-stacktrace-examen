package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.ActivoDTO;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.repositories.BilleteraRepository;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion encargada de {@link Activo}
 */
@Service
public class ActivoService extends ServiceABM<Activo, ActivoDTO>{
    @Autowired
    BilleteraRepository billeteraRepository;

    @Autowired
    ActivoRepository activoRepository;

    /**
     * Para que un {@link Activo} sea modificable debe cumplir:<br>
     * -lo mismo que {@link ActivoService#cumpleCondicionDeCreacion(ActivoDTO)}<br>
     * -la cantidad almacenada no puede ser negativa
     * @param elementoParcial
     * @return
     */
    @Override
    protected boolean cumpleCondicionDeModificacion(ActivoDTO elementoParcial) {
        boolean cumpleCreacion = cumpleCondicionDeCreacion(elementoParcial);
        boolean cumpleCantidad = elementoParcial.getCantidadAdquirida() >= 0 |  elementoParcial.getCantidadAdquirida() == null;
        return cumpleCreacion & cumpleCantidad;
    }

    /**
     * Para que un activo sea creable, debe cumplir:
     * -La {@link luceroraul.stacktrace.examen.entities.Billetera} debe existir
     * -La {@link luceroraul.stacktrace.examen.entities.MonedaCripto} no debe estar asignada aun
     * @param elemento objeto de entrada
     * @return
     */
    @Override
    protected boolean cumpleCondicionDeCreacion(ActivoDTO elemento) {
        boolean esBilleteraValida;
        boolean esMonedaYaAsignada;
        boolean resultado;
        try {
            esBilleteraValida = billeteraRepository.existsById(elemento.getIdBilletera());
        } catch (Exception e) {
            esBilleteraValida = false;
        }
            esMonedaYaAsignada = activoRepository.existeMonedaParaDichabilletera(
                    elemento.getIdMonedaCripto(),
                    elemento.getIdBilletera()
            );
        resultado = !esMonedaYaAsignada & esBilleteraValida;
        return resultado;
    }


}

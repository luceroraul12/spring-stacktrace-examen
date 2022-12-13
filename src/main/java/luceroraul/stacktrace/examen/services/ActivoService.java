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
    @Override
    protected Class<Activo> recuperarClaseGenerica() {
        return Activo.class;
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

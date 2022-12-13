package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.MonedaCriptoDTO;
import org.springframework.stereotype.Service;

/**
 * Implementacion relacionada a {@link MonedaCripto}
 */
@Service
public class MonedaCriptoService extends ServiceABM<MonedaCripto, MonedaCriptoDTO>{
    @Override
    protected Class<MonedaCripto> recuperarClaseGenerica() {
        return MonedaCripto.class;
    }

    //TODO: ver el tema del nombre repetido
    /**
     * Para que sea una moneda valida para la creacion no debe contener id y puede que nombre no repetido
     * @param elemento objeto de entrada
     * @return
     */
    @Override
    protected boolean cumpleCondicionDeCreacion(MonedaCriptoDTO elemento) {
        boolean contieneId = elemento.getId() != null;
        return !contieneId;
    }
}

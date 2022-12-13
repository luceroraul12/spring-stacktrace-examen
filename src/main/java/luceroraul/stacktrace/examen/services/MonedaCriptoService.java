package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.MonedaCriptoDTO;
import org.springframework.stereotype.Service;

@Service
public class MonedaCriptoService extends ServiceABM<MonedaCripto, MonedaCriptoDTO>{
    @Override
    protected Class<MonedaCripto> recuperarClaseGenerica() {
        return MonedaCripto.class;
    }

    @Override
    protected boolean cumpleCondicionDeCreacion(MonedaCriptoDTO elemento) {
        boolean contieneId = elemento.getId() != null;
        return !contieneId;
    }
}

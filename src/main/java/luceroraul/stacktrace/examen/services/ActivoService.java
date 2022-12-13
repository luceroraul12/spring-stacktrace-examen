package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.ActivoDTO;
import org.springframework.stereotype.Service;

@Service
public class ActivoService extends ServiceABM<Activo, ActivoDTO>{
    @Override
    protected Class<Activo> recuperarClaseGenerica() {
        return Activo.class;
    }

    @Override
    protected boolean cumpleCondicionDeCreacion(ActivoDTO elemento) {
        Boolean existeEnSistema = repository.existsById(elemento.getId());
        return !existeEnSistema;
    }


}

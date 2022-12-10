package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import org.springframework.stereotype.Service;

@Service
public class ActivoService extends ServiceABM<Activo>{
    @Override
    protected Class<Activo> recuperarClaseGenerica() {
        return Activo.class;
    }


}
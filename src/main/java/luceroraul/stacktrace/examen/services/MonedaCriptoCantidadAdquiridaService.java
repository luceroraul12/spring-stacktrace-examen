package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.MonedaCriptoCantidadAdquirida;
import org.springframework.stereotype.Service;

@Service
public class MonedaCriptoCantidadAdquiridaService extends ServiceABM<MonedaCriptoCantidadAdquirida>{
    @Override
    protected Class<MonedaCriptoCantidadAdquirida> recuperarClaseGenerica() {
        return MonedaCriptoCantidadAdquirida.class;
    }


}

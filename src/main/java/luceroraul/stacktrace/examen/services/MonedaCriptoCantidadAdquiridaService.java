package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.MonedaCriptoCantidadAdquirida;

public class MonedaCriptoCantidadAdquiridaService extends ServiceABM<MonedaCriptoCantidadAdquirida>{
    @Override
    protected Class<MonedaCriptoCantidadAdquirida> recuperarClaseGenerica() {
        return MonedaCriptoCantidadAdquirida.class;
    }


}

package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.MonedaCriptoCantidadAdquirida;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilleteraService extends ServiceABM<Billetera>{
    @Autowired
    MonedaCriptoRepository monedaCriptoRepository;

    private Billetera billeteraSeleccionada;

    private MonedaCriptoCantidadAdquirida adaptarMonedaCripto(MonedaCripto moneda){
        return new MonedaCriptoCantidadAdquirida(moneda, 0.0, billeteraSeleccionada);
    }

    @Override
    protected Class<Billetera> recuperarClaseGenerica() {
        return Billetera.class;
    }
}

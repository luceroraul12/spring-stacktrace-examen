package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BilleteraService extends ServiceABM<Billetera>{
    @Autowired
    MonedaCriptoRepository monedaCriptoRepository;

    private Billetera billeteraSeleccionada;

    @Override
    protected Class<Billetera> recuperarClaseGenerica() {
        return Billetera.class;
    }

    public ResponseEntity<Billetera> consultar(Long id) {
        ResponseEntity<Billetera> respuesta;
        Billetera billeteraEncontrada = repository.findById(id).get();
        respuesta = billeteraEncontrada == null
                ? new ResponseEntity<>(new Billetera(), HttpStatus.ACCEPTED)
                : new ResponseEntity<>(billeteraEncontrada, HttpStatus.OK);
        return respuesta;
    }

    private Activo adaptarMonedaCripto(MonedaCripto moneda){
        return new Activo(moneda, 0.0, billeteraSeleccionada);
    }


}

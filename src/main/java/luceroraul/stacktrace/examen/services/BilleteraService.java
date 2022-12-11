package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.BilleteraDto;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.repositories.BilleteraRepository;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import luceroraul.stacktrace.examen.util.BilleteraUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BilleteraService extends ServiceABM<Billetera>{
    @Autowired
    BilleteraUtil billeteraUtil;
    @Autowired
    BilleteraRepository billeteraRepository;

    private Billetera billeteraSeleccionada;

    @Override
    protected Class<Billetera> recuperarClaseGenerica() {
        return Billetera.class;
    }

    public ResponseEntity<BilleteraDto> consultar(Long id) {
        BilleteraDto billeteraDto = billeteraUtil.convertirBilleteraaDTO(billeteraRepository.findById(1L).get());

        ResponseEntity<BilleteraDto> respuesta;
        respuesta = new ResponseEntity<>(billeteraDto, HttpStatus.OK);
        return respuesta;
    }

    private Activo adaptarMonedaCripto(MonedaCripto moneda){
        return new Activo(moneda, 0.0, billeteraSeleccionada);
    }


}

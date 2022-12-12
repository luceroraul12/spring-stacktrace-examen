package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.BilleteraDto;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.repositories.BilleteraRepository;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.util.BilleteraUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public ResponseEntity<BilleteraDto> consultarBilleteraUnica(Long id) {
        BilleteraDto billeteraDto = billeteraUtil.convertirBilleteraaDTO(billeteraRepository.findById(id).get());

        ResponseEntity<BilleteraDto> respuesta;
        respuesta = new ResponseEntity<>(billeteraDto, HttpStatus.OK);
        return respuesta;
    }

    public ResponseEntity<List<BilleteraDto>> consultarBilleterasPorUsuario(Long id){
        ResponseEntity<List<BilleteraDto>> respuesta;
        List<BilleteraDto> billeteras = billeteraUtil
                .convertirVariasbilleterasaDTO(
                        billeteraRepository.obtenerTodasLasBilleterasDeUsuario(id));
        respuesta = new ResponseEntity<>(billeteras, HttpStatus.OK);
        return respuesta;
    }

    private Activo adaptarMonedaCripto(MonedaCripto moneda){
        return new Activo(moneda, 0.0, billeteraSeleccionada);
    }


    public ResponseEntity<Respuesta<Double>> consultarSaldoPorBilletera(Long id) {
        Billetera billetera = billeteraRepository.findById(id).orElseThrow();
        Respuesta<Double> resultado = new Respuesta<>(
                billeteraUtil.obtenerSaldoBilleteraEnPesos(Collections.singletonList(billetera)),
                "Consulta de saldo por billetera exitosa"
        );

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    public ResponseEntity<Respuesta<Double>> consultarSaldoPorUsuario(Long id){
        List<Billetera> billeteras = billeteraRepository.obtenerTodasLasBilleterasDeUsuario(id);
        Respuesta<Double> resultado;
        if (billeteras.size() != 0){
            resultado = new Respuesta<>(
                    billeteraUtil.obtenerSaldoBilleteraEnPesos(billeteras),
                    "Consulta de saldo por usuario exitosa"
            );
        } else {
            resultado = new Respuesta<>(null, "no existe billetera con id: "+id);
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}

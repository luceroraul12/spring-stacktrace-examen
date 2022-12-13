package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.BilleteraDto;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.repositories.BilleteraRepository;
import luceroraul.stacktrace.examen.repositories.UsuarioRepository;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.util.BilleteraUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BilleteraService extends ServiceABM<Billetera, BilleteraDto>{
    @Autowired
    BilleteraUtil billeteraUtil;
    @Autowired
    BilleteraRepository billeteraRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected Class<Billetera> recuperarClaseGenerica() {
        return Billetera.class;
    }

    @Override
    protected boolean cumpleCondicionDeCreacion(BilleteraDto elemento) {
        boolean esUsuarioValido = usuarioRepository.existsById(elemento.getUsuario().getId());
        return esUsuarioValido;
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

    public ResponseEntity<Body> consultarSaldoPorBilletera(Long id) {
        Respuesta resultado;
        Billetera billetera;
        try {
            billetera = billeteraRepository.findById(id).get();
            resultado = new Respuesta(
                    billeteraUtil.obtenerSaldoBilleteraEnPesos(Collections.singletonList(billetera)),
                    "Consulta de saldo por billetera exitosa",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            resultado = new Respuesta(
                    null,
                    "No existe billetera con id: "+id,
                    HttpStatus.ACCEPTED
            );
        }
        return resultado.getResponseEntity();
    }

    public ResponseEntity<Body> consultarSaldoPorUsuario(Long id){
        Respuesta resultado;
        List<Billetera> billeteras;
        try {
            if (!usuarioRepository.existsById(id)){
                throw new Exception();
            }
            billeteras = billeteraRepository.obtenerTodasLasBilleterasDeUsuario(id);
            resultado = new Respuesta(
                    billeteraUtil.obtenerSaldoBilleteraEnPesos(billeteras),
                    "Consulta de saldo por usuario exitosa",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            resultado = new Respuesta(null, "Error al consultar saldo. No existe usuario con id: "+id,HttpStatus.ACCEPTED);
        }
        return resultado.getResponseEntity();
    }

}

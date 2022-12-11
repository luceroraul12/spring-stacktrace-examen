package luceroraul.stacktrace.examen.controllers;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.BilleteraDto;
import luceroraul.stacktrace.examen.services.BilleteraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("billeteras")
public class BilleteraController extends ControllerBase<Billetera>{
    //TODO: al modificar billeteras realiza la accion correctamente pero arroja un error relacionado a JSON

    @Autowired
    BilleteraService billeteraService;

    @GetMapping("consulta")
    public ResponseEntity<BilleteraDto> consultar(@RequestBody Map<String,Object> pedido){
        return billeteraService.consultarBilleteraUnica(Long.valueOf(String.valueOf(pedido.get("id"))));
    }

    @GetMapping("consulta-usuario")
    public ResponseEntity<List<BilleteraDto>> consultarPorIdUsuario(@RequestBody Map<String, Object> pedido){
        return billeteraService.consultarBilleterasPorUsuario(Long.parseLong(String.valueOf(pedido.get("id"))));
    }
}

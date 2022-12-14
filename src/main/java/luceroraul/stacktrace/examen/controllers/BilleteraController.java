package luceroraul.stacktrace.examen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.BilleteraDto;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.services.BilleteraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("billeteras")
public class BilleteraController extends ControllerBase<Billetera, BilleteraDto>{

    @Autowired
    BilleteraService billeteraService;

    /**
     * Endpoint para consultar todos los {@link luceroraul.stacktrace.examen.entities.Activo} de una {@link Billetera}
     * @param id de billetera
     * @return arreglo de activos
     */
    @Operation(summary = "Para consultar el resumen de una billetera en pesos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "consulta realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Body.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay errores con ese usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @GetMapping("consulta-billetera")
    public ResponseEntity<BilleteraDto> consultar(@RequestParam(value = "id-billetera") Long id ){
        return billeteraService.consultarBilleteraUnica(id);
    }

    /**
     * Endpoint para consultar todas las  {@link Billetera} de un {@link luceroraul.stacktrace.examen.entities.Usuario}
     * @param id del usuario
     * @return arreglo de billeteras
     */
    @Operation(summary = "Para consultar las billeteras de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "consulta realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation =  BilleteraDto.class))) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay errores con ese usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @GetMapping("consulta-usuario")
    public ResponseEntity<List<BilleteraDto>> consultarPorIdUsuario(@RequestParam(value = "id-usuario") Long id ){
        return billeteraService.consultarBilleterasPorUsuario(id);
    }

    /**
     * Endpoint para obtener el equivalente de una billetera
     * @param idBilletera
     * @return equivalencia activos en pesos
     */
    @Operation(summary = "Para consultar los activos de una billetera")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "consulta realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BilleteraDto.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay errores con esa billetera",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @GetMapping("consulta-saldo-billetera")
    public ResponseEntity<Body> consultarSaldoPorBilletera(@RequestParam(value = "id-billetera") Long idBilletera){
        return billeteraService.consultarSaldoPorBilletera(idBilletera);
    }

    /**
     * Endpoint para obtener el equivalente de un usuario
     * @param idUsuario
     * @return equivalencia de billeteras en pesos
     */
    @Operation(summary = "Para consultar el resumen de todas las billeteras de un usuario en pesos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "consulta realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Body.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay errores con ese usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @GetMapping("consulta-saldo-usuario")
    public ResponseEntity<Body> consultarSaldoPorUsuario(@RequestParam(value = "id-usuario") Long idUsuario){
        return billeteraService.consultarSaldoPorUsuario(idUsuario);
    }
}

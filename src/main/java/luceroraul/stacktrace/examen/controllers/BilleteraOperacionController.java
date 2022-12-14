package luceroraul.stacktrace.examen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luceroraul.stacktrace.examen.controllers.divisas.OperacionesDivisasEnBilletera;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.services.BilleteraActivoOperacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("billeteras/operaciones")
public class BilleteraOperacionController implements OperacionesDivisasEnBilletera {

    @Autowired
    BilleteraActivoOperacionesService billeteraActivoOperacionesService;


    @Operation(summary = "Permite realizar un deposito a un activo. La cantidad a depositar se encuentra en la misma moneda que contenga el Activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposito realizado con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Body.class)) }),
            @ApiResponse(responseCode = "202", description = "Se acepto la peticion pero hay problemas con el Activo o la cantidad",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @PostMapping("depositar")
    @Override
    public ResponseEntity<Body> depositarCantidad(@RequestBody PeticionDeposito peticion) throws Exception {
        return billeteraActivoOperacionesService.depositarResultadoDto(peticion);
    }

    @Operation(summary = "Para hacer intercambio de monedas entre Activos, la cantidad a intercambiar se encuentra en la misma moneda que el Activo de origen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Intercambio realizado con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Body.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay errores con la cantidad o los activos de origen - destino",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @PostMapping("intercambiar")
    @Override
    public ResponseEntity<Body> intercambiarCantidad(@RequestBody PeticionIntercambio peticion) throws Exception {
        return billeteraActivoOperacionesService.intercambiar(peticion);
    }
}

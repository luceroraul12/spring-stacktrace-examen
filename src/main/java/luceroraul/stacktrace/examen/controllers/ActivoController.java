package luceroraul.stacktrace.examen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.ActivoDTO;
import luceroraul.stacktrace.examen.responses.Respuesta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("billeteras/activos")
public class ActivoController extends ControllerBase<Activo, ActivoDTO> {

    /**
     * @param elemento dto con informacion necesaria para crear. seteo cantidad 0.0
     * @return
     */
    @Operation(summary = "Da de alta un activo. Solo requiere idbilletera y idMonedaCripto<br>" +
            "idMonedaCripto=1 corresponde a BTC y idMonedaCripto=2 ETH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creacion realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Respuesta.Body.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay errores con la billetera o la moneda cripto elegida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Respuesta.Body.class)))})
    @Override
    public ResponseEntity<Respuesta.Body> crear(@RequestBody ActivoDTO elemento) {
        elemento.setCantidadAdquirida(0.0);
        return super.crear(elemento);
    }
}

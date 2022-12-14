package luceroraul.stacktrace.examen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import luceroraul.stacktrace.examen.entities.BaseDTO;
import luceroraul.stacktrace.examen.entities.Identificable;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import luceroraul.stacktrace.examen.services.ServiceABM;
import luceroraul.stacktrace.examen.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador base generico abstracto relacionado al {@link ServiceABM} para realizar como minimo alta, baja, modificaion de entidades
 * @param <Entidad> Entidad de JPA
 * @param <ClaseDTO> Clase dto que se le entrega al cliente
 */
public abstract class ControllerBase<Entidad extends Identificable, ClaseDTO extends BaseDTO> {
    /**
     * Hace referencia a la inyeccion de depedencia por medio de genericos.
     * Ver {@link ServiceABM}.
     */
    @Autowired
    private ServiceABM<Entidad, ClaseDTO> serviceABM;

    /**
     * Endpoint para realizar la creacion de un elemento. No requiere id
     * @param elemento dto con informacion necesaria para crear
     * @return Body
     */
    @Operation(summary = "Da de alta un elemento de dicho controllerno. No requiere id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "creacion realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Body.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay parametros erroneos y por lo tanto se cancela la peticion",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @PostMapping("alta")
    public ResponseEntity<Body> crear(@RequestBody ClaseDTO elemento){
        elemento.setId(null);
        return serviceABM.crear(elemento);
    }

    /**
     * Endpoint para realizar la modificacion de un elemento.
     * @param elemento dto completo o parcial para realizar una modificacion
     * @return Body
     */
    @Operation(summary = "permite modificar un elemento de dicho controller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "modificacion realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Body.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay parametros erroneos y por lo tanto se cancela la peticion",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @PatchMapping("modificar")
    public ResponseEntity<Body> modificar(@RequestBody ClaseDTO elemento){
        return serviceABM.modificar(elemento);
    }

    /**
     * Endpoint para realizar la eliminacion de un elemento
     * @param id es el identificador enviado por url
     * @return reafirma la eliminacion retornando el identificador
     */
    @Operation(summary = "Permite eliminar un elemento de dicho controller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "eliminacion realizada con exito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Body.class)) }),
            @ApiResponse(responseCode = "202", description = "fue recibido por el sistema pero hay parametros erroneos y por lo tanto se cancela la peticion",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Body.class)))})
    @DeleteMapping("baja")
    public ResponseEntity<Body> eliminar(@RequestParam(value = "id-elemento") Long id){
        return  serviceABM.eliminar(id);
    }

}

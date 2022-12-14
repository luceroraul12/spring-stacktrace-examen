package luceroraul.stacktrace.examen.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Es una respuesta modelo para todos los tipos de respuesta
 */
@Getter
@Setter
@AllArgsConstructor
public class Respuesta{
    /**
     * Es la respuesta objeto de la API
     */
    private Body body;
    /**
     * Codigo de mensaje HTTP
     */
    private HttpStatus estadoHttp;

    public Respuesta(Object body, String mensaje, HttpStatus estadoHttp) {
        this.body = new Body(body, mensaje);
        this.estadoHttp = estadoHttp;
    }
    public ResponseEntity<Body> getResponseEntity(){
        return new ResponseEntity<>(body, estadoHttp);
    }

    /**
     * Respuesta util de la API
     */
    @Getter
    @Setter
    @AllArgsConstructor
    public class Body {
        /**
         * Al poder llegar a devolver diferentes tipos de informacion, esta tipado como Object pero normalmente puede ser:
         * - clases DTO
         * - null
         */
        private Object body;
        /**
         * Mensaje que indica que paso al recuperar la informacion, puede ser afirmativo o negativo
         */
        private String mensaje;

    }
}

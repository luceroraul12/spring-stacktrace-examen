package luceroraul.stacktrace.examen.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
public class Respuesta{
    private Body body;
    private HttpStatus estadoHttp;

    public Respuesta(Object body, String mensaje, HttpStatus estadoHttp) {
        this.body = new Body(body, mensaje);
        this.estadoHttp = estadoHttp;
    }
    public ResponseEntity<Body> getResponseEntity(){
        return new ResponseEntity<>(body, estadoHttp);
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public class Body {
        private Object body;
        private String mensaje;

    }
}

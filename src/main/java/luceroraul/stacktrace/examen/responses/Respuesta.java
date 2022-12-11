package luceroraul.stacktrace.examen.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Respuesta<Entidad>{
    private Entidad entidad;
    private String mensaje;
}

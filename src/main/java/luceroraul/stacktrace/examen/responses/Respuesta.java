package luceroraul.stacktrace.examen.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Respuesta<Entidad>{
    private Entidad body;
    private String mensaje;
}

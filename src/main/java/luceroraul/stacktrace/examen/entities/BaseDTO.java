package luceroraul.stacktrace.examen.entities;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseDTO {
    @Schema(description = "obligatorio al modifcar, dependendiendo del DTO puede ser obligatorio al crear")
    protected Long id;

    public BaseDTO(Long id) {
        this.id = id;
    }
}

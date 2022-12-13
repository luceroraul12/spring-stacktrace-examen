package luceroraul.stacktrace.examen.entities;


import lombok.Data;

@Data
public class BaseDTO {
    protected Long id;

    public BaseDTO(Long id) {
        this.id = id;
    }
}

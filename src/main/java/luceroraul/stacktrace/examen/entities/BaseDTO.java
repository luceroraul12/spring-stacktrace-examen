package luceroraul.stacktrace.examen.entities;


import lombok.Data;

@Data
public class BaseDTO {
    protected final Long id;

    public BaseDTO(Long id) {
        this.id = id;
    }
}

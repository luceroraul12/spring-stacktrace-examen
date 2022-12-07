package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OperacionTipo extends Identificable{
    @Column(name = "tipo")
    private String tipo;
}

package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OperacionTipo extends Identificable{
    @Column(name = "tipo", unique = true)
    private String tipo;
}

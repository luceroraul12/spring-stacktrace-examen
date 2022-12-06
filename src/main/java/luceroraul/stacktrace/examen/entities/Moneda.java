package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Moneda extends Identificable{
    private String nombre;
    private Double equivalenciaDolar;
}

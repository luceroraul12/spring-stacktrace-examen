package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Moneda extends Identificable{
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "relacion_dolar")
    private Double relacionDolar;
}

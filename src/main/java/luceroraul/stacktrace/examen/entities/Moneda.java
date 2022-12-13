package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Moneda extends Identificable{
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "relacion_dolar")
    private Double relacionDolar;

    public Moneda(Long id, String nombre, Double relacionDolar) {
        super(id);
        this.nombre = nombre;
        this.relacionDolar = relacionDolar;
    }
}

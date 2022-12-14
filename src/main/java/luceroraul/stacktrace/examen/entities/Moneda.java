package luceroraul.stacktrace.examen.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Moneda extends Identificable{
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column(name = "relacion_pesos")
    private Double relacionPesos;

    public Moneda(Long id, String nombre, Double relacionPesos) {
        super(id);
        this.nombre = nombre;
        this.relacionPesos = relacionPesos;
    }
}

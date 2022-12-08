package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
public class MonedaCripto extends Moneda{
    public MonedaCripto(String nombre, Double relacionDolar) {
        super(nombre, relacionDolar);
    }
}

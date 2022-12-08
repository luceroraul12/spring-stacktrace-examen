package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MonedaTradicional extends Moneda {
    public MonedaTradicional(String nombre, Double relacionDolar) {
        super(nombre, relacionDolar);
    }
}

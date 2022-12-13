package luceroraul.stacktrace.examen.entities;

import javax.persistence.Entity;
import lombok.*;

@Entity
public class MonedaCripto extends Moneda{


    public MonedaCripto() {
        super(null,null,null);
    }

    @Builder
    public MonedaCripto(Long id, String nombre, Double relacionDolar) {
        super(id, nombre, relacionDolar);
    }

}

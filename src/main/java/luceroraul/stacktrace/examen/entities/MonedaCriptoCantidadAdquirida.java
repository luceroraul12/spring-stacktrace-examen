package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MonedaCriptoCantidadAdquirida extends Identificable{
    @OneToOne
    @JoinColumn(name = "moneda_cripto_id")
    private MonedaCripto monedaCripto;
    @Column(name = "cantidad_adquirida")
    private Double cantidadAdquirida = 0.0;

    @ManyToOne
    @JoinColumn(name = "billetera_id")
    private Billetera billetera;

}

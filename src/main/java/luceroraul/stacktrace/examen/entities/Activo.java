package luceroraul.stacktrace.examen.entities;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "billetera_activos")
public class Activo extends Identificable{
    @OneToOne
    @JoinColumn(name = "moneda_cripto_id")
    private MonedaCripto monedaCripto;
    @Column(name = "cantidad_adquirida")
    private Double cantidadAdquirida ;

    @ManyToOne
    @JoinColumn(name = "billetera_id")
    private Billetera billetera;

    public Activo() {
        super(null);
    }

    @Builder
    public Activo(Long id, MonedaCripto monedaCripto, Double cantidadAdquirida, Billetera billetera) {
        super(id);
        this.monedaCripto = monedaCripto;
        this.cantidadAdquirida = cantidadAdquirida;
        this.billetera = billetera;
    }

}

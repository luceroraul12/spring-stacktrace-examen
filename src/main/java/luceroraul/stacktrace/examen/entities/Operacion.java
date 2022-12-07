package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import luceroraul.stacktrace.examen.enums.TipoOperacion;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Operacion extends Identificable {
    @Column(name = "momento_operacion")
    private LocalDateTime momentoOperacion;
    @Column(name = "tipo_operacion")
    private TipoOperacion tipoOperacion;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "billetera_id")
    private Billetera billetera;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "moneda_cripto_origen_id")
    private MonedaCripto monedaCriptoOrigen;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "moneda_cripto_destino_id")
    private MonedaCripto monedaCriptoDestino;

}

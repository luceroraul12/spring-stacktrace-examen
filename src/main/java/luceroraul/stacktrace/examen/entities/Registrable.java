package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import luceroraul.stacktrace.examen.enums.TipoOperacion;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Registrable extends Identificable {
    @Column(name = "momento_operacion")
    private LocalDateTime momentoOperacion;
    @Column(name = "tipo_operacion")
    private TipoOperacion tipoOperacion;
    @Column(name = "billetera_origen")
    private Billetera billetera;
    @Column(name = "tipo_moneda_origen")
    private MonedaCripto tipoMonedaOrigen;
    @Column(name = "tipo_moneda_destino")
    private MonedaCripto tipoMonedaDestino;

}

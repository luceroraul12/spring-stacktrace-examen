package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operacion extends Identificable {
    @Column(name = "momento_operacion")
    private LocalDateTime momentoOperacion;

    @Column(name = "tipo")
    private OperacionTipo operacionTipo;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "activo_origen_id")
    private Activo activoOrigen;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "activo_destino_id")
    private Activo activoDestino;

}

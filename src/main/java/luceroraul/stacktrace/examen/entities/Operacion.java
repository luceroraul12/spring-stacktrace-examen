package luceroraul.stacktrace.examen.entities;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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

    @Column(name = "cantidad_operada")
    private Double cantidadOperada;

    public Operacion() {
        super(null);
    }

    public enum OperacionTipo{
        DEPOSITO, INTERCAMBIO
    }

    @Builder
    public Operacion(Long id, LocalDateTime momentoOperacion, OperacionTipo operacionTipo, Activo activoOrigen, Activo activoDestino, Double cantidadOperada) {
        super(id);
        this.momentoOperacion = momentoOperacion;
        this.operacionTipo = operacionTipo;
        this.activoOrigen = activoOrigen;
        this.activoDestino = activoDestino;
        this.cantidadOperada = cantidadOperada;
    }
    //    public Operacion(Long id, LocalDateTime momentoOperacion, OperacionTipo operacionTipo, Activo activoOrigen, Activo activoDestino) {
//        super(id);
//        this.momentoOperacion = momentoOperacion;
//        this.operacionTipo = operacionTipo;
//        this.activoOrigen = activoOrigen;
//        this.activoDestino = activoDestino;
//    }
}

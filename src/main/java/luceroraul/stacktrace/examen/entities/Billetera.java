package luceroraul.stacktrace.examen.entities;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Billetera extends Identificable{
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    @OneToMany(mappedBy = "billetera", orphanRemoval = true)
    private List<Activo> activos = new ArrayList<>();

    public Billetera() {
        super(null);
    }

    @Builder
    public Billetera(Long id, Usuario usuario, List<Activo> activos) {
        super(id);
        this.usuario = usuario;
        this.activos = activos;
    }

}

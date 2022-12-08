package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Billetera extends Identificable{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "billetera", orphanRemoval = true)
    private List<MonedaCriptoCantidadAdquirida> monedaCriptoCantidadAdquiridas = new ArrayList<>();

}

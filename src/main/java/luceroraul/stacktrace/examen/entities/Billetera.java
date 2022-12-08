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
    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario  usuario;

    @OneToMany(mappedBy = "billetera", orphanRemoval = true)
    private List<MonedaCriptoCantidadAdquirida> monedaCriptoCantidadAdquiridas = new ArrayList<>();

}

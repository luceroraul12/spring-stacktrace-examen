package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Usuario extends Identificable {
    @Column(name = "dni")
    private Integer DNI;


    private List<String> nombres;
    private List<String> apellidos;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "sexo_id", nullable = false)
    private Sexo sexo;

}

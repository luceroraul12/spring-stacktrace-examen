package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario extends Identificable {
    @Column(name = "dni")
    private Integer DNI;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "sexo_id", nullable = false)
    private Sexo sexo;

    @ElementCollection
    @Column(name = "nombre", length = 50)
    @CollectionTable(name = "usuario_nombres", joinColumns = @JoinColumn(name = "owner_id"))
    private List<String> nombres = new ArrayList<>();
    @ElementCollection
    @Column(name = "apellido", length = 50)
    @CollectionTable(name = "Usuario_apellidos", joinColumns = @JoinColumn(name = "owner_id"))
    private List<String> apellidos = new ArrayList<>();

    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
}

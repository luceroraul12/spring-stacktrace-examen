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
    @Column(name = "dni", unique = true)
    private Integer DNI;
    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "nombre", length = 50)
    private String nombre;
    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    private List<Billetera> billeteras = new ArrayList<>();

    public enum Sexo  {
        HOMBRE, MUJER, OTRO
    }

}

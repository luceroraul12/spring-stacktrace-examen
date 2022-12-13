package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.*;
import lombok.*;

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

    public Usuario() {
        super(null);
    }

    @Builder
    public Usuario(Long id, Integer DNI, Sexo sexo, String nombre, String apellido, String email, String telefono, List<Billetera> billeteras) {
        super(id);
        this.DNI = DNI;
        this.sexo = sexo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.billeteras = billeteras;
    }

    public enum Sexo  {
        HOMBRE, MUJER, OTRO
    }

}

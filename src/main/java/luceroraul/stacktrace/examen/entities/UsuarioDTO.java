package luceroraul.stacktrace.examen.entities;

import lombok.Data;
import luceroraul.stacktrace.examen.entities.BaseDTO;
import luceroraul.stacktrace.examen.entities.Usuario;

@Data
public class UsuarioDTO extends BaseDTO {
    private Integer DNI;
    private Usuario.Sexo sexo;

    private String nombre;
    private String apellido;

    private String email;
    private String telefono;

    public UsuarioDTO(Long id, Integer DNI, Usuario.Sexo sexo, String nombres, String apellidos, String email, String telefono) {
        super(id);
        this.DNI = DNI;
        this.sexo = sexo;
        this.nombre = nombres;
        this.apellido = apellidos;
        this.email = email;
        this.telefono = telefono;
    }
}

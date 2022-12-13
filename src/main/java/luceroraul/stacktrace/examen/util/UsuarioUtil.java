package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.BaseDTO;
import luceroraul.stacktrace.examen.entities.Usuario;
import luceroraul.stacktrace.examen.entities.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioUtil extends BaseUtil<Usuario, UsuarioDTO>{
    @Override
    public BaseDTO convertirToDTO(Usuario elemento) {
        return new UsuarioDTO(
                elemento.getId(),
                elemento.getDNI(),
                elemento.getSexo(),
                elemento.getNombre(),
                elemento.getApellido(),
                elemento.getEmail(),
                elemento.getTelefono()
        );
    }

    @Override
    public Usuario convertirToEntidad(UsuarioDTO elemento) {
        return Usuario.builder()
                .id(elemento.getId())
                .DNI(elemento.getDNI())
                .sexo(elemento.getSexo())
                .nombre(elemento.getNombre())
                .apellido(elemento.getApellido())
                .email(elemento.getEmail())
                .telefono(elemento.getTelefono())
                .build();
    }
}

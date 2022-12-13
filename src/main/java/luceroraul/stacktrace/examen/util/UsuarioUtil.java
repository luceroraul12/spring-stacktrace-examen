package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Usuario;
import luceroraul.stacktrace.examen.entities.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioUtil extends BaseUtil<Usuario, UsuarioDTO>{
    @Override
    public UsuarioDTO convertirToDTO(Usuario elemento) {
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

    @Override
    public Usuario fusionarDTOyEntidad(Usuario elementoAlmacenado, UsuarioDTO elementoParcial) {
        return Usuario.builder()
                .id(elegirParametroNoNull(elementoAlmacenado.getId(), elementoParcial.getId()))
                .DNI(elegirParametroNoNull(elementoAlmacenado.getDNI(), elementoParcial.getDNI()))
                .sexo(elegirParametroNoNull(elementoAlmacenado.getSexo(), elementoParcial.getSexo()))
                .nombre(elegirParametroNoNull(elementoAlmacenado.getNombre(), elementoParcial.getNombre()))
                .apellido(elegirParametroNoNull(elementoAlmacenado.getApellido(), elementoParcial.getApellido()))
                .email(elegirParametroNoNull(elementoAlmacenado.getEmail(), elementoParcial.getEmail()))
                .telefono(elegirParametroNoNull(elementoAlmacenado.getTelefono(), elementoParcial.getTelefono()))
                .billeteras(elementoAlmacenado.getBilleteras())
                .build();

    }
}

package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceABM<Usuario>{
    @Override
    protected Class<Usuario> recuperarClaseGenerica() {
        return Usuario.class;
    }
}

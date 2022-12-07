package luceroraul.stacktrace.examen.repositories;

import luceroraul.stacktrace.examen.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

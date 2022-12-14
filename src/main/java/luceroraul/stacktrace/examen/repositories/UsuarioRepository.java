package luceroraul.stacktrace.examen.repositories;

import luceroraul.stacktrace.examen.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select (count(u) > 0) from Usuario u where u.DNI = :DNI")
    boolean existeUsuarioConMismoDni(@Param("DNI") Integer DNI);

}

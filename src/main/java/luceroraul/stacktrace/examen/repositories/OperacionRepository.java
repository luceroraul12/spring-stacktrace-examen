package luceroraul.stacktrace.examen.repositories;

import luceroraul.stacktrace.examen.entities.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacionRepository extends JpaRepository<Operacion, Long> {
}

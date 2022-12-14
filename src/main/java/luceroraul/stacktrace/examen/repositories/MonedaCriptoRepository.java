package luceroraul.stacktrace.examen.repositories;

import luceroraul.stacktrace.examen.entities.MonedaCripto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonedaCriptoRepository extends JpaRepository<MonedaCripto, Long> {
    @Query("select (count(m) > 0) from MonedaCripto m where upper(m.nombre) = upper(:nombre)")
    boolean yaExistePorNombre(@Param("nombre") String nombre);

}

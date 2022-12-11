package luceroraul.stacktrace.examen.repositories;

import luceroraul.stacktrace.examen.entities.Billetera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BilleteraRepository extends JpaRepository<Billetera, Long> {
    @Query("select b from Billetera b where b.usuario.id = :id")
    List<Billetera> obtenerTodasLasBilleterasDeUsuario(@Param("id") Long id);




}

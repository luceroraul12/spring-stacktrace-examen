package luceroraul.stacktrace.examen.repositories;

import luceroraul.stacktrace.examen.entities.Activo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivoRepository extends JpaRepository<Activo, Long> {
    @Query("select (count(a) > 0) from Activo a where a.monedaCripto.id = :id and a.billetera.id = :id1")
    boolean existeMonedaParaDichabilletera(@Param("id") Long idMonedaCripto, @Param("id1") Long idBilletera);


}

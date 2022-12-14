package luceroraul.stacktrace.examen.init;

import javax.annotation.PostConstruct;
import luceroraul.stacktrace.examen.entities.*;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuarcion para la base de datos.
 * Lo unico que hace es cargar las tablas con datos iniciales. Por el momento, solo se cargan 2 tipos de {@link MonedaCripto}
 */
@Configuration
public class BaseDeDatosIncial {

    @Autowired
    MonedaCriptoRepository monedaCriptoRepository;


    /**
     * Verifica que no existan datos con dicha tabla, en caso de estar vacia, carga valores por defecto.
     */
    @PostConstruct
    public void cargarBaseDeDatos(){
        if (monedaCriptoRepository.count() == 0){
            cargarTablaMonedaCripto();
        }
    }

    /**
     * Carga la tabla de {@link MonedaCripto} con dos tipos, BTC y ETH con valores de ejemplo
     */
    private void cargarTablaMonedaCripto() {
        monedaCriptoRepository.save(MonedaCripto.builder()
                .nombre("BTC")
                .relacionDolar(100.0)
                .build());
        monedaCriptoRepository.save(MonedaCripto.builder()
                .nombre("ETH")
                .relacionDolar(20.0)
                .build());
    }
}

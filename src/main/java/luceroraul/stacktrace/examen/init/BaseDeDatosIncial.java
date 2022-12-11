package luceroraul.stacktrace.examen.init;

import jakarta.annotation.PostConstruct;
import luceroraul.stacktrace.examen.entities.*;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseDeDatosIncial {

    @Autowired
    MonedaCriptoRepository monedaCriptoRepository;


    @PostConstruct
    public void cargarBaseDeDatos(){
        if (monedaCriptoRepository.count() == 0){
            cargarTablaMonedaCripto();
        }
    }

    private void cargarTablaMonedaCripto() {
        monedaCriptoRepository.save(new MonedaCripto("BTC", 100.0));
        monedaCriptoRepository.save(new MonedaCripto("ETH", 20.0));
    }
}

package luceroraul.stacktrace.examen.init;

import javax.annotation.PostConstruct;
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

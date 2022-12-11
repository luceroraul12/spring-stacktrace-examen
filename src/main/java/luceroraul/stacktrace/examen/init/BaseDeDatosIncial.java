package luceroraul.stacktrace.examen.init;

import jakarta.annotation.PostConstruct;
import luceroraul.stacktrace.examen.entities.*;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import luceroraul.stacktrace.examen.repositories.MonedaTradicionalRepository;
import luceroraul.stacktrace.examen.repositories.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseDeDatosIncial {

    @Autowired
    SexoRepository sexoRepository;

    @Autowired
    MonedaTradicionalRepository monedaTradicionalRepository;

    @Autowired
    MonedaCriptoRepository monedaCriptoRepository;


    @PostConstruct
    public void cargarBaseDeDatos(){
        if(sexoRepository.count() == 0){
            cargarTablaSexo();
        }
        if (monedaTradicionalRepository.count() == 0){
            cargarTablaMonedaTradicional();
        }
        if (monedaCriptoRepository.count() == 0){
            cargarTablaMonedaCripto();
        }
    }

    private void cargarTablaMonedaCripto() {
        monedaCriptoRepository.save(new MonedaCripto("BTC", 100.0));
        monedaCriptoRepository.save(new MonedaCripto("ETH", 20.0));
    }

    private void cargarTablaMonedaTradicional() {
        monedaTradicionalRepository.save(new MonedaTradicional("USD", 1.0));
        monedaTradicionalRepository.save(new MonedaTradicional("ARGS", 169.81));
    }


    private void cargarTablaSexo() {

        sexoRepository.save(new Sexo("masculino"));
        sexoRepository.save(new Sexo("femenino"));
        sexoRepository.save(new Sexo("otro"));
        sexoRepository.save(new Sexo("no incluir"));
    }
}

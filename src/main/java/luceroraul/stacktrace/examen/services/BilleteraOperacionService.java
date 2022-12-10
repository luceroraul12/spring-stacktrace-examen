package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.request.MonedaCriptoOperacionPeticion;
import org.springframework.beans.factory.annotation.Autowired;

public class BilleteraOperacionService {

    @Autowired
    ActivoRepository activoRepository;

    public Activo depositar(MonedaCriptoOperacionPeticion peticion){
        Activo resultado;
        Activo divisaAlmacenada = activoRepository.findById(peticion.getIdActivoDestino()).get();

        resultado = realizarDeposito(divisaAlmacenada,
                peticion.getCantidadOperable());
        return resultado;
    }

    public Activo realizarDeposito(
            Activo divisaDestino,
            Double cantidadOperable) {
            divisaDestino.setCantidadAdquirida(
                    divisaDestino.getCantidadAdquirida() + cantidadOperable
            );
            return divisaDestino;
    }

}

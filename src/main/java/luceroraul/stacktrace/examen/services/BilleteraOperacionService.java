package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.MonedaCriptoCantidadAdquirida;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoCantidadAdquiridaRepository;
import luceroraul.stacktrace.examen.request.MonedaCriptoOperacionPeticion;
import org.springframework.beans.factory.annotation.Autowired;

public class BilleteraOperacionService {

    @Autowired
    MonedaCriptoCantidadAdquiridaRepository monedaCriptoCantidadAdquiridaRepository;

    public MonedaCriptoCantidadAdquirida depositar(MonedaCriptoOperacionPeticion peticion){
        MonedaCriptoCantidadAdquirida resultado;
        MonedaCriptoCantidadAdquirida divisaAlmacenada = monedaCriptoCantidadAdquiridaRepository.findById(peticion.getIdMonedaCriptoCantidadAdquiridaDestino()).get();

        resultado = realizarDeposito(divisaAlmacenada,
                peticion.getCantidadOperable());
        return resultado;
    }

    public MonedaCriptoCantidadAdquirida realizarDeposito(
            MonedaCriptoCantidadAdquirida divisaDestino,
            Double cantidadOperable) {
            divisaDestino.setCantidadAdquirida(
                    divisaDestino.getCantidadAdquirida() + cantidadOperable
            );
            return divisaDestino;
    }

}

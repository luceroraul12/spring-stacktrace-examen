package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BilleteraOperacionService {

    @Autowired
    ActivoRepository activoRepository;

    public Activo depositar(PeticionDeposito peticion) throws Exception {
        Activo resultado;
        Long idActivoOrigen;
        Double cantidad;
        idActivoOrigen = peticion.getIdActivoOrigen();
        cantidad = peticion.getCantidadOperable();
        resultado = realizarIncremento(
                activoRepository.findById(idActivoOrigen).orElseThrow(),
                cantidad);
        return resultado;
    }

    public Map<String, Activo> intercambiar(PeticionIntercambio peticion){
        Activo resultado;
        Long idActivoOrigen;
        Double cantidad;
        idActivoOrigen = peticion.getIdActivoOrigen();
        cantidad = peticion.getCantidadOperable();
        resultado = realizarIncremento(
                activoRepository.findById(idActivoOrigen).orElseThrow(),
                cantidad);
        return resultado;
    }

    public Activo realizarIncremento(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() + cantidad);
        return activo;
    }

    public Activo realizarDecremento(Activo activo, Double cantidad) {
        activo.setCantidadAdquirida(activo.getCantidadAdquirida() - cantidad);
        return activo;
    }

    public boolean tieneMontoSuficiente(Long idActivoOrigen, Double cantidad) {
        Activo activo = activoRepository.findById(idActivoOrigen).orElseThrow();
        return activo.getCantidadAdquirida() >= cantidad;
    }


}

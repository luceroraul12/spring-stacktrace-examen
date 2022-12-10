package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.request.PeticionDeposito;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.util.BilleteraOperacionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class BilleteraOperacionService {

    @Autowired
    ActivoRepository activoRepository;

    @Autowired
    BilleteraOperacionUtil util;

    public Activo depositar(PeticionDeposito peticion) throws Exception {
        Activo resultado;
        Long idActivoOrigen;
        Double cantidad;
        idActivoOrigen = peticion.getIdActivoOrigen();
        cantidad = peticion.getCantidadOperable();
        resultado = util.realizarIncremento(
                activoRepository.findById(idActivoOrigen).orElseThrow(),
                cantidad);
        return resultado;
    }

    public Map<String, Activo> intercambiar(PeticionIntercambio peticion) throws Exception {
        Map<String, Activo> resultado = new HashMap<>();
        Double cantidad;
        Activo activoOrigen = activoRepository.findById(peticion.getIdActivoOrigen()).orElseThrow();
        Activo activoDestino = activoRepository.findById(peticion.getIdActivoDestino()).orElseThrow();
        cantidad = peticion.getCantidadOperable();

        if (util.tieneMontoSuficiente(activoOrigen,cantidad)){
            resultado.put("activoReducido", util.realizarReduccion(activoOrigen, cantidad));
            resultado.put("activoIncrementado", util.realizarIncremento(activoDestino, cantidad));

            activoRepository.saveAll(Arrays.asList(activoOrigen, activoDestino));
        } else {
            throw new Exception("fondo insuficiente en activo de origen");
        }
        return resultado;
    }




}

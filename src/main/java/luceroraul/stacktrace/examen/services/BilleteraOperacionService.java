package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.Operacion;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.repositories.OperacionRepository;
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
    OperacionRepository operacionRepository;

    @Autowired
    BilleteraOperacionUtil util;

    public Activo depositar(PeticionDeposito peticion) throws Exception {
        Activo resultado;
        Long idActivoOrigen;
        Double cantidad;
        Operacion operacion;
        idActivoOrigen = peticion.getIdActivoDestino();
        cantidad = peticion.getCantidadOperable();
        resultado = util.realizarIncrementoMismaUnidad(
                activoRepository.findById(idActivoOrigen).orElseThrow(),
                cantidad);

        operacion = new Operacion(

        );
        activoRepository.save(resultado);

        return resultado;
    }

    public Map<String, Activo> intercambiar(PeticionIntercambio peticion) throws Exception {
        Map<String, Activo> resultado = new HashMap<>();
        Double cantidad;
        Activo activoOrigen = activoRepository.findById(peticion.getIdActivoOrigen()).orElseThrow(()->{
            return new Exception("activo de origen erroneo");
        });
        Activo activoDestino = activoRepository.findById(peticion.getIdActivoDestino()).orElseThrow(() ->{
            return new Exception("activo de destino erroneo");
        });
        cantidad = peticion.getCantidadOperable();

        if (util.tieneMontoSuficiente(activoOrigen,cantidad)){
            resultado.put("activoReducido", util.realizarReduccion(activoOrigen, cantidad));
            if (util.sonSobreMismaMonedaCripto(activoOrigen, activoDestino)){
                resultado.put("activoIncrementado", util.realizarIncrementoMismaUnidad(activoDestino, cantidad));
            } else {
                resultado.put("activoIncrementado", util.realizarIncrementoDiferentesUnidades(
                        activoOrigen,activoDestino, cantidad));
            }
            resultado.forEach((key,data) -> activoRepository.save(data));
        } else {
            throw new Exception("fondo insuficiente en activo de origen");
        }
        return resultado;
    }
}

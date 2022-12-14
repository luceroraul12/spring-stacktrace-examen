package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.entities.MonedaCriptoDTO;
import luceroraul.stacktrace.examen.repositories.MonedaCriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion relacionada a {@link MonedaCripto}
 */
@Service
public class MonedaCriptoService extends ServiceABM<MonedaCripto, MonedaCriptoDTO>{

    @Autowired
    MonedaCriptoRepository monedaCriptoRepository;

    //TODO: ver el tema del nombre repetido

    /**
     * En este caso, al ser una tabla que no depende de nadie y que los parametros restantos se pueden obtener de la base de datos, no contiene condicion de modificacion
     * @param elementoParcial informacion completa o parcial a para modificar
     * @return
     */
    @Override
    protected boolean cumpleCondicionDeModificacion(MonedaCriptoDTO elementoParcial) {
        return true;
    }

    /**
     * Para que sea una moneda valida para la creacion debe cumplir:<br>
     * -{@link MonedaCripto#getNombre()} no existente en tabla
     * -{@link MonedaCripto#getRelacionPesos()} nunca negativa ni nula
     * @param elemento objeto de entrada
     * @return
     */
    @Override
    protected boolean cumpleCondicionDeCreacion(MonedaCriptoDTO elemento) {
        boolean yaExisteNombre = monedaCriptoRepository.yaExistePorNombre(elemento.getNombre());
        boolean esRelacionPesoValida = elemento.getRelacionPeso() >= 0;
        return esRelacionPesoValida & !yaExisteNombre;
    }
}

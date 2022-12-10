package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertidorMonedaUtilTest {

    private ConvertidorMonedaUtil convertidorMonedaUtil = new ConvertidorMonedaUtil();

    @Test
    void convertir(){
        MonedaCripto monedaCripto = new MonedaCripto("prueba", 20.0);
        Activo activo = new Activo(null, 3.5, null);
        Double resultado = convertidorMonedaUtil.obtenerActivoEnPesos(monedaCripto, activo);
        assertEquals(70, resultado);
        assertNotEquals(30, resultado);
        assertNotEquals(120, resultado);
    }

}
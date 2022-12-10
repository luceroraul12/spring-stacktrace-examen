package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConvertidorMonedaUtilTest {

    private ConvertidorMonedaUtil convertidorMonedaUtil = new ConvertidorMonedaUtil();

    @Test
    void convertir(){
        MonedaCripto monedaCripto = new MonedaCripto("prueba", 20.0);
        Activo activo = new Activo(monedaCripto, 3.5, null);
        Double resultado = convertidorMonedaUtil.obtenerActivoEnPesos(activo);
        assertEquals(70, resultado);
        assertNotEquals(30, resultado);
        assertNotEquals(120, resultado);
    }

    @Test
    void convertirVarios(){
        MonedaCripto m1 = new MonedaCripto("m1", 10.0);
        MonedaCripto m2 = new MonedaCripto("m2", 20.0);
        MonedaCripto m3 = new MonedaCripto("m3", 30.0);
        List<Activo> activos = Arrays.asList(
                new Activo(m1,5.0,null),
                new Activo(m2,10.0,null),
                new Activo(m3,3.2,null)
        );

        Double resultado = convertidorMonedaUtil.obtenerTotalDeActivosEnPesos(activos);
        assertEquals(346, resultado);
        assertNotEquals(200, resultado);
        assertNotEquals(500, resultado);

    }

}
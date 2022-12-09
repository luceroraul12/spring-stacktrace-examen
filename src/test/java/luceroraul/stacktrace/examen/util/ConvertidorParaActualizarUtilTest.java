package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.MonedaCripto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConvertidorParaActualizarUtilTest {

    @Autowired
    ConvertidorParaActualizarUtil convertidor = new ConvertidorParaActualizarUtil();

    @Test
    void modificarEntidad() {
        MonedaCripto m = new MonedaCripto("ARGS", 2546.234);
        Map<String, Object> parcial = new HashMap<>();
        MonedaCripto resultado;

        parcial.put("nombre", "ARGENTINA");
        resultado = convertidor.modificarEntidad(parcial, m, MonedaCripto.class);

        assertEquals("ARGENTINA",resultado.getNombre());
        assertEquals(2546.234,resultado.getRelacionDolar());
    }

    @Test
    void mapear() {
        MonedaCripto m = new MonedaCripto("ARGS", 2546.234);
        Map<String, Object> mMap = convertidor.convertirEntidadAMap(m);
        assertEquals("ARGS", mMap.get("nombre"));
        assertEquals(2546.234, mMap.get("relacionDolar"));
        assertNotEquals(2546242.234, mMap.get("relacionDolar"));
    }
}
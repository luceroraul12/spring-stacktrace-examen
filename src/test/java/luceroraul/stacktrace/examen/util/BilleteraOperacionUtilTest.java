package luceroraul.stacktrace.examen.util;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BilleteraOperacionUtilTest {

    BilleteraOperacionUtil util = new BilleteraOperacionUtil();

    @Test
    void realizarIncremento() throws Exception {
//        PeticionDeposito peticion = new PeticionDeposito(
//                20L,
//                10L,
//                null,
//                10.0);
        Activo original = new Activo(null, 20.5, null);
        Activo resultado = util.realizarIncrementoMismaUnidad(original, 25.3);

        assertEquals(45.8, resultado.getCantidadAdquirida());
        assertNotEquals(30, resultado.getCantidadAdquirida());
        assertNotEquals(3000, resultado.getCantidadAdquirida());

    }

    @Test
    void tieneMontoSuficiente() {
        Activo original = new Activo(null, 20.5, null);

        boolean respuestaBuena = util.tieneMontoSuficiente(original,15.2);
        boolean respuestaMala = util.tieneMontoSuficiente(original,300.2);
        boolean respuestaLimite = util.tieneMontoSuficiente(original,20.5);
        assertTrue(respuestaBuena);
        assertTrue(respuestaLimite);
        assertFalse(respuestaMala);
    }

    @Test
    void conversionDiferentesMonedaseIncrementarDestino(){
        MonedaCripto monedaOrigen = new MonedaCripto("mOrigen", 20.0);
        MonedaCripto monedaDestino = new MonedaCripto("mDestino", 30.0);
        Activo activoOrigen = new Activo(monedaOrigen, 10.0, null);
        Activo activoDestino = new Activo(monedaDestino, 10.0, null);

        Activo resultado = util.realizarIncrementoDiferentesUnidades(
                activoOrigen,
                activoDestino,
                1.0);
        assertEquals(10.66, resultado.getCantidadAdquirida(), 0.01);

    }
}
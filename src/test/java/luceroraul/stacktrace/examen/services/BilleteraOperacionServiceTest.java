package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BilleteraOperacionServiceTest {

    @MockBean
    ActivoRepository repository;

    @Autowired
    BilleteraOperacionService service;

    @BeforeEach
    void before(){
        MockitoAnnotations.openMocks(this);
        Mockito.when(repository.findById(20L)).thenReturn(Optional.of(new Activo(null, 25.3, null)));
        Mockito.when(repository.findById(10L)).thenReturn(Optional.of(new Activo(null, 15.0, null)));
    }

    @Test
    void intercambiar() throws Exception {
        Map<String, Activo> resultado = service.intercambiar(new PeticionIntercambio(
                20L,
                10L,
                10.0));
        Activo origen = resultado.get("activoReducido");
        Activo destino = resultado.get("activoIncrementado");
        assertEquals(15.3, origen.getCantidadAdquirida());
        assertEquals(25.0, destino.getCantidadAdquirida());
//        cuando se introduce un activo de origen con fondos insuficientes para realizar un intercambio
        Exception exception = assertThrows(Exception.class, () -> service.intercambiar(new PeticionIntercambio(
                20L,
                10L,
                10.0)));

        assertEquals(exception.getMessage(), "fondo insuficiente en activo de origen");
    }

    @Test
    void realizarIncremento() throws Exception {
//        PeticionDeposito peticion = new PeticionDeposito(
//                20L,
//                10L,
//                null,
//                10.0);
        Activo original = new Activo(null, 20.5, null);
        Activo resultado = service.realizarIncremento(original, 25.3);

        assertEquals(45.8, resultado.getCantidadAdquirida());
        assertNotEquals(30, resultado.getCantidadAdquirida());
        assertNotEquals(3000, resultado.getCantidadAdquirida());

    }

    @Test
    void tieneMontoSuficiente() {
        Activo original = new Activo(null, 20.5, null);

        boolean respuestaBuena = service.tieneMontoSuficiente(original,15.2);
        boolean respuestaMala = service.tieneMontoSuficiente(original,300.2);
        boolean respuestaLimite = service.tieneMontoSuficiente(original,20.5);
        assertTrue(respuestaBuena);
        assertTrue(respuestaLimite);
        assertFalse(respuestaMala);
    }
}
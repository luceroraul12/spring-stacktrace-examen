package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Activo;
import luceroraul.stacktrace.examen.entities.MonedaCripto;
import luceroraul.stacktrace.examen.repositories.ActivoRepository;
import luceroraul.stacktrace.examen.request.PeticionIntercambio;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BilleteraActivoOperacionesServiceTest {

    @MockBean
    ActivoRepository repository;

    @Autowired
    BilleteraActivoOperacionesService service;

    @BeforeEach
    void before(){
        MockitoAnnotations.openMocks(this);
        MonedaCripto monedaOrigen = new MonedaCripto("origen", 20.0);
        MonedaCripto monedaDestino = new MonedaCripto("destino", 30.0);

        Mockito.when(repository.findById(20L)).thenReturn(Optional.of(new Activo(monedaOrigen, 25.3, null)));
        Mockito.when(repository.findById(10L)).thenReturn(Optional.of(new Activo(monedaDestino, 15.0, null)));
        Mockito.when(repository.findById(5L)).thenReturn(Optional.of(new Activo(monedaOrigen, 15.0, null)));
    }

    @Test
    void intercambiarMismaMonedaOrgenDestino() throws Exception {
        Map<String, ActivoDto>resultado = (Map<String, ActivoDto>) service.intercambiar(new PeticionIntercambio(
                null,
                20.0,
                5L,
                20L
                )).getBody().getBody();
        ActivoDto origen = resultado.get("activoReducido");
        ActivoDto destino = resultado.get("activoIncrementado");
        assertEquals(5.3, origen.getCantidadAdquirida(),0.001);
        assertEquals(35.0, destino.getCantidadAdquirida(), 0.001);

    }

    @Test
    void intercambiarDiferentesMonedas() throws Exception {
        Map<String, ActivoDto> resultado = (Map<String, ActivoDto>) service.intercambiar(new PeticionIntercambio(
                null,
                20.0,
                10L,
                20L));
        ActivoDto origen = resultado.get("activoReducido");
        ActivoDto destino = resultado.get("activoIncrementado");
        assertEquals(5.3, origen.getCantidadAdquirida(),0.001);
        assertEquals(28.33, destino.getCantidadAdquirida(), 0.01);
    }

    @Test
    void intercambiarErroneo(){
        ResponseEntity<Body> resultado = service.intercambiar(
                new PeticionIntercambio(
                        null,
                        20.0,
                        20L,
                        5L));

        assertEquals("Error al intercambiar, error en cantidad", resultado.getBody().getMensaje());
    }


}
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
                20.0));
        Activo origen = resultado.get("activoReducido");
        Activo destino = resultado.get("activoIncrementado");
        assertEquals(5.3, origen.getCantidadAdquirida(),0.001);
        assertEquals(35.0, destino.getCantidadAdquirida(), 0.001);

    }

    @Test
    void intercambiarThrow(){
        Exception exception = assertThrows(Exception.class, () -> service.intercambiar(new PeticionIntercambio(
                10L,
                20L,
                20.0)));

        assertEquals(exception.getMessage(), "fondo insuficiente en activo de origen");
    }


}
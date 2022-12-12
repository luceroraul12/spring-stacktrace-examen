package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.Usuario;
import luceroraul.stacktrace.examen.entities.UsuarioDTO;
import luceroraul.stacktrace.examen.repositories.BilleteraRepository;
import luceroraul.stacktrace.examen.responses.Respuesta;
import luceroraul.stacktrace.examen.responses.Respuesta.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceABM<Usuario, UsuarioDTO>{
    @Autowired
    private BilleteraRepository billeteraRepository;
    @Override
    protected Class<Usuario> recuperarClaseGenerica() {
        return Usuario.class;
    }

    @Override
    protected boolean cumpleCondicionDeCreacion(UsuarioDTO elemento) {
        return false;
    }

    @Override
    public ResponseEntity<Body> crear(UsuarioDTO elemento) {
        ResponseEntity<Body> respuesta = super.crear(elemento);
        billeteraRepository.save(
                new Billetera(baseUtil.convertirToEntidad(elemento),
                        null));
        return respuesta;
    }
}

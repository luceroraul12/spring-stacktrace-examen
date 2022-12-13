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

/**
 * Implementacion relacionada a {@link Usuario}
 */
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
        boolean contieneId = elemento.getId() != null;
        boolean contieneNombre = elemento.getNombre() != null;
        boolean contieneApellido = elemento.getApellido() != null;
        return !contieneId & contieneApellido & contieneNombre;
    }


    /**
     * Cuando se crea un {@link Usuario} se le debe crear una billetera.
     * por lo tanto, luego de la creacion del usuario, uso el repositorio de {@link Billetera} y genero el propio a partir del Id de usuario obtenido de la creacion previa.
     * @param elemento es el objecto con los datos necesarios para hacer persistirlos. Ademas de eso, contiene el id posterior a la creacion del mismo.
     * @return
     */
    @Override
    public ResponseEntity<Body> crear(UsuarioDTO elemento) {
        ResponseEntity<Body> respuesta = super.crear(elemento);
        billeteraRepository.save(
                new Billetera(null,baseUtil.convertirToEntidad(elemento),null));
        return respuesta;
    }
}

package luceroraul.stacktrace.examen.services;

import luceroraul.stacktrace.examen.entities.Billetera;
import luceroraul.stacktrace.examen.entities.Usuario;
import luceroraul.stacktrace.examen.entities.UsuarioDTO;
import luceroraul.stacktrace.examen.repositories.BilleteraRepository;
import luceroraul.stacktrace.examen.repositories.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Al no depender de otras tablas y que la informacion se extrae de la base de datos, no contiene condiciones para la modificacion
     * @param elementoParcial informacion completa o parcial a para modificar
     * @return
     */
    @Override
    protected boolean cumpleCondicionDeModificacion(UsuarioDTO elementoParcial) {
        boolean esDniRepetido = false;
        if (elementoParcial.getDNI() != null){
            esDniRepetido = usuarioRepository.existeUsuarioConMismoDni(elementoParcial.getDNI());
        }
        return !esDniRepetido;
    }
    @Override
    protected boolean cumpleCondicionDeCreacion(UsuarioDTO elemento) {
        boolean contieneId = elemento.getId() != null;
        boolean contieneNombre = elemento.getNombre() != null;
        boolean contieneApellido = elemento.getApellido() != null;
        boolean esDniRepetido = usuarioRepository.existeUsuarioConMismoDni(elemento.getDNI());
        return !contieneId & contieneApellido & contieneNombre & !esDniRepetido;
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
        if (elemento.getId() != null){
            billeteraRepository.save(
                    new Billetera(null,baseUtil.convertirToEntidad(elemento),null));
        }
        return respuesta;
    }


}

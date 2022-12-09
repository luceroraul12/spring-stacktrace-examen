package luceroraul.stacktrace.examen.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConvertidorParaActualizarUtil {
    ObjectMapper mapper = new ObjectMapper();


    public <Entidad> Entidad modificarEntidad(
            Map<String, Object> elementoParcial,
            Entidad elementoAlmacenado,
            Class<Entidad> clase){
        Entidad resultado;
        Map<String, Object> elementoAlmacenadoMap = convertirEntidadAMap(elementoAlmacenado);
        elementoAlmacenadoMap.putAll(elementoParcial);
        resultado = mapper.convertValue(elementoAlmacenadoMap, clase);
        return resultado;
    }

    public <Entidad> Map<String, Object> convertirEntidadAMap(Entidad elemento){
        return mapper.convertValue(
                elemento,
                new TypeReference<Map<String, Object>>() {
                });
    }

}

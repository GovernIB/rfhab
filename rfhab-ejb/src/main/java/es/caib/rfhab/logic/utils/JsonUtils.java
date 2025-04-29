package es.caib.rfhab.logic.utils;

import org.fundaciobit.genapp.common.i18n.I18NException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author jpou
 *
 */
public class JsonUtils {

    protected static Logger log = Logger.getLogger(LogicUtils.class);

    public static <T> List<T> listFromJson(String json, Class<T[]> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        if (json == null || json.isEmpty()) {
            return null; // Retorna null si el JSON és buit o nul
        }

        ObjectMapper mapper = new ObjectMapper();
        // mapper.setDefaultLeniency(true); // Permet JSON incomplet o amb camps
        // desconeguts

        // Converteix el JSON a un array d'objectes T
        T[] array = mapper.readValue(json, clazz);

        // Converteix l'array a una llista
        return new ArrayList<>(List.of(array));
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws I18NException {

        if (json == null || json.isEmpty())
            return null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new I18NException(e.getMessage());
        }
    }
}

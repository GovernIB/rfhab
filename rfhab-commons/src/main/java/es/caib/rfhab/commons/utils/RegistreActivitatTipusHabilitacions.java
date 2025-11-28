package es.caib.rfhab.commons.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Diccionari d'habilitacions per cada valor de {@link RegistreActivitatTipus}.
 * Les llistes es poden ampliar segons les habilitacions necessàries.
 */
public final class RegistreActivitatTipusHabilitacions {

    public static final String COPIA_AUTENTICA = "CEA";
    public static final String IDENTIFICACIO_I_SIGNATURA = "IDSIG";
    
    private static final Map<RegistreActivitatTipus, List<String>> HABILITACIONS;

    static {
        Map<RegistreActivitatTipus, List<String>> m = new EnumMap<>(RegistreActivitatTipus.class);
        m.put(RegistreActivitatTipus.COPIA, Arrays.asList(COPIA_AUTENTICA));
        m.put(RegistreActivitatTipus.COMPAREIX, Arrays.asList(IDENTIFICACIO_I_SIGNATURA));
        m.put(RegistreActivitatTipus.TRAMIT, Arrays.asList(IDENTIFICACIO_I_SIGNATURA));
        HABILITACIONS = Collections.unmodifiableMap(m);
    }

    private RegistreActivitatTipusHabilitacions() {
        // Utilitat: no instanciable
    }

    /**
     * Retorna el mapa complet (immutable).
     */
    public static Map<RegistreActivitatTipus, List<String>> getHabilitacions() {
        return HABILITACIONS;
    }

    /**
     * Retorna la llista d'habilitacions per al tipus indicat o una llista buida si no hi ha
     * definició.
     */
    public static List<String> getHabilitacions(RegistreActivitatTipus tipus) {
        if (tipus == null) {
            return Collections.emptyList();
        }
        return HABILITACIONS.getOrDefault(tipus, Collections.emptyList());
    }
}

package es.caib.rfhab.commons.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Diccionari de rols per cada valor de {@link RegistreActivitatTipus}.
 * Les llistes es poden ampliar segons els rols necessaris.
 */
public final class RegistreActivitatTipusRols {

    public static final String COPIA_AUTENTICA = "CEA";
    public static final String IDENTIFICACIO_I_SIGNATURA = "IDSIG";
    
    private static final Map<RegistreActivitatTipus, List<String>> ROLS;

    static {
        Map<RegistreActivitatTipus, List<String>> m = new EnumMap<>(RegistreActivitatTipus.class);
        m.put(RegistreActivitatTipus.COPIA, Arrays.asList(COPIA_AUTENTICA));
        m.put(RegistreActivitatTipus.COMPAREIX, Arrays.asList(IDENTIFICACIO_I_SIGNATURA));
        m.put(RegistreActivitatTipus.TRAMIT, Arrays.asList(IDENTIFICACIO_I_SIGNATURA));
        ROLS = Collections.unmodifiableMap(m);
    }

    private RegistreActivitatTipusRols() {
        // Utilitat: no instanciable
    }

    /**
     * Retorna el mapa complet (immutable).
     */
    public static Map<RegistreActivitatTipus, List<String>> getRols() {
        return ROLS;
    }

    /**
     * Retorna la llista de rols per al tipus indicat o una llista buida si no hi ha
     * definició.
     */
    public static List<String> getRols(RegistreActivitatTipus tipus) {
        if (tipus == null) {
            return Collections.emptyList();
        }
        return ROLS.getOrDefault(tipus, Collections.emptyList());
    }
}

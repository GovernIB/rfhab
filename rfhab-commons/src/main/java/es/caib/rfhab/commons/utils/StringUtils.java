package es.caib.rfhab.commons.utils;

/**
 * 
 * @author jpou
 * 
 */
public class StringUtils {

    /**
     * Comprova si una cadena és buida ("") o és null.
     * 
     * @param cadena
     * @return
     */
    public static boolean isEmpty(final String cadena) {
        return cadena == null || cadena.length() == 0
                || cadena.equals("null"); // esta condicion es para controlar los null de oracle
    }

    /**
     * Comprova si una cadena no és buida ("") o null.
     * 
     * @param cadena
     * @return
     */
    public static boolean isNotEmpty(final String cadena) {
        return !isEmpty(cadena);
    }
}

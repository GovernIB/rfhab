package es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits;

import java.util.HashMap;
import java.util.Map;

public class PlantillaOdtModelConsentiment {

    /**
     * Construeix el context per Freemarker. TODO: transformar els noms de les keys
     * a annotacions de l'objecte DAO i que aquest mètode sigui genèric
     * com a paràmetres
     */
    public static Map<String, Object> buildFreemarkerContext(TramitConsentimentDAO tramitInfo) {
        Map<String, Object> dades = new HashMap<>();
        dades.put("interessatNom", tramitInfo.getInteressatNom());
        dades.put("interessatLlinatge1", tramitInfo.getInteressatLlinatge1());
        dades.put("interessatLlinatge2", tramitInfo.getInteressatLlinatge2());
        dades.put("interessatNif", tramitInfo.getInteressatNif());
        dades.put("representantNom", tramitInfo.getRepresentantNom());
        dades.put("representantLlinatge1", tramitInfo.getRepresentantLlinatge1());
        dades.put("representantLlinatge2", tramitInfo.getRepresentantLlinatge2());
        dades.put("representantNif", tramitInfo.getRepresentantNif());
        dades.put("funcionariNom", tramitInfo.getFuncionariNom());
        dades.put("funcionariLlinatge1", tramitInfo.getFuncionariLlinatge1());
        dades.put("funcionariLlinatge2", tramitInfo.getFuncionariLlinatge2());
        dades.put("funcionariCodi", tramitInfo.getFuncionariCodi());
        dades.put("procedimentNom", tramitInfo.getProcedimentNom());
        dades.put("procedimentCodi", tramitInfo.getProcedimentCodi());
        dades.put("tramitNom", tramitInfo.getTramitNom());
        dades.put("tramitCodi", tramitInfo.getTramitCodi());
        dades.put("dataTramit", tramitInfo.getDataTramit());
        return dades;
    }
}
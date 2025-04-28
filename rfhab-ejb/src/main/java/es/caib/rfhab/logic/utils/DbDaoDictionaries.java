package es.caib.rfhab.logic.utils;

import java.util.Map;

import es.caib.rfhab.model.fields.HistoricLlocFields;
import es.caib.rfhab.model.fields.LlocFields;

public final class DbDaoDictionaries {

    // HistoricLloc - HistoricLlocDAO
    public static final Map<String, String> HistoricLloc = Map.ofEntries(
            Map.entry("llocID", HistoricLlocFields.LLOCID.javaName),
            Map.entry("codiLloc", LlocFields.CODILLOC.javaName),
            Map.entry("nom", LlocFields.NOM.javaName),
            Map.entry("unitatID", LlocFields.UNITATID.javaName),
            Map.entry("personalOamr", LlocFields.PERSONALOAMR.javaName),
            Map.entry("entitatID", LlocFields.ENTITATID.javaName),
            Map.entry("observacions", LlocFields.OBSERVACIONS.javaName),
            Map.entry("numeroCai", HistoricLlocFields.NUMEROCAI.javaName),
            Map.entry("dataCreacio", LlocFields.DATACREACIO.javaName),
            Map.entry("dataBaixa", LlocFields.DATABAIXA.javaName));
}
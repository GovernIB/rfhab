package es.caib.rfhab.logic.utils;

import java.util.Map;

import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.model.fields.FuncionariFields;
import es.caib.rfhab.model.fields.HistoricFields;
import es.caib.rfhab.model.fields.HistoricLlocFields;
import es.caib.rfhab.model.fields.LlocFields;

public final class DbDaoDictionaries {

    // TODO: que les keys siguin els noms de les propietats dels DAOs, extrets amb
    // reflexió
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

    // Historic - HistoricFuncionariDAO
    public static final Map<String, String> HistoricFuncionari = Map.ofEntries(
            Map.entry("numero", FuncionariFields.NUMERO.javaName),
            Map.entry("nom", FuncionariFields.NOM.javaName),
            Map.entry("llinatge1", FuncionariFields.LLINATGE1.javaName),
            Map.entry("llinatge2", FuncionariFields.LLINATGE2.javaName),
            Map.entry("tipusIdentificador", FuncionariFields.TIPUSIDENTIFICADOR.javaName),
            Map.entry("identificador", FuncionariFields.IDENTIFICADOR.javaName),
            Map.entry("usuari", FuncionariFields.USUARI.javaName),
            Map.entry("correu", FuncionariFields.CORREU.javaName),
            Map.entry("observacions", FuncionariFields.OBSERVACIONS.javaName));
}
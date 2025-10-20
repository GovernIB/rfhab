package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import javax.ejb.Local;

@Local
public interface CarregadorMassiuFhIllocsLogicaService {
        public static final String JNDI_NAME = "java:app/rfhab-ejb/CarregadorMassiuFhIllocsLogicaEJB!es.caib.rfhab.logic.CarregadorMassiuFhIllocsLogicaService";

        /**
         * Dona d'alta un nou funcionari habilitat via API REST.
         */
        public String nouFuncionariHabilitat(NouFuncionariHabilitatDTO dto) throws Exception;

        /**
         * Dona d'alta un funcionari via API REST.
         */
        public String donarAltaFh(String lang, String usuariId, String identificadorFh, String numCai) throws Exception;

        /**
         * Dona de baixa un funcionari via API REST.
         */
        public String donarBaixaFh(String lang, String usuariId, String identificadorFh, String numCai)
                        throws Exception;

        /**
         * Dona d'alta un nou lloc via API REST.
         */
        public String nouLloc(NouLlocDTO dto)
                        throws Exception;

        /**
         * Dona d'alta un lloc via API REST.
         */
        public String donarAltaLloc(String lang, String usuariId, String codiLloc, String expansio, String numCai)
                        throws Exception;

        /**
         * Dona de baixa un lloc via API REST.
         */
        public String donarBaixaLloc(String lang, String usuariId, String codiLloc, String expansio, String numCai)
                        throws Exception;

        /**
         * Assigna un funcionari a un lloc via API REST.
         */
        public String assignarFuncionari(String lang, String usuariId, String identificadorFh, String codiLloc,
                        String expansio, String numeroCai,
                        String observacions) throws Exception;

        /**
         * Treu un funcionari d'un lloc via API REST.
         */
        public String treureFuncionari(String identificadorFh, String codiLloc, String expansio, String numeroCai,
                        String observacions) throws Exception;

        /**
         * Treu tots els funcionaris d'un lloc via API REST.
         */
        public String treureTotsFuncionari(String codiLloc, String expansio, String numeroCai, String observacions)
                        throws Exception;

        /**
         * Llegeix el fitxer ODS i processa cada línia com a FuncionariOdsDTO, aplicant
         * la lògica de càrrega.
         * La lògica de crida a l'API REST s'ha d'implementar més endavant.
         * 
         * @throws Exception Si hi ha problemes de lectura o processament
         */
        public void carregaFh() throws Exception;

        public String getOdsFilePath();

        public String getApiUrl();
}

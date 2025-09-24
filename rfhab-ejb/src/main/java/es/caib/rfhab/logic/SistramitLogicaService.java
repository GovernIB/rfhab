package es.caib.rfhab.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.logic.utils.TicketAccesDto.RpersonaInfo;
import es.caib.rfhab.model.entity.Funcionari;

@Local
public interface SistramitLogicaService {
    public static final String JNDI_NAME = "java:app/rfhab-ejb/SistramitLogicaEJB!es.caib.rfhab.logic.SistramitLogicaService";

    public String getTicketAccesoFh(Funcionari funcionari, String codiDir3, RpersonaInfo interessat,
            RpersonaInfo representant, String idTramiteCatalogo, String ticketLanguage, String ticketParametros,
            boolean servicioCatalogo, String tramite, Integer tramiteVersion, String idActuacionFH)
            throws I18NException;
}

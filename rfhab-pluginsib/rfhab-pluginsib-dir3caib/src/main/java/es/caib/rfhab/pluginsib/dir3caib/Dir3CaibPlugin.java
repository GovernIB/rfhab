package es.caib.rfhab.pluginsib.dir3caib;

import es.caib.rfhab.commons.utils.Configuracio;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;

/**
 * 
 * @author jagarcia
 */

public class Dir3CaibPlugin implements IDir3CaibPlugin {

	protected Logger log = Logger.getLogger(this.getClass());

	// TODO: codigo?
	// TODO: que gravi a base de dades
	public void sincronitzar() throws I18NException {

		log.info("INICI Dir3CaibPlugin: sincronitzar");

		try {

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException(e, "error.sincronitzardir3caib");
		}

		log.info(" FI Dir3CaibPlugin: sincronitar");
	}
}
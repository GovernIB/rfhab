package es.caib.rfhab.pluginsib.dir3caib;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.pluginsib.dir3caib.client.v1.api.DefaultApi;
import es.caib.rfhab.pluginsib.dir3caib.client.v1.services.ApiClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author jagarcia
 */

public class Dir3CaibPlugin implements IDir3CaibPlugin {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private DefaultApi publicApi = null;
	private DefaultApi protectedApi = null;

	//TODO: codigo?
	//TODO: que gravi a base de dades
	public void sincronitzar() throws I18NException {

		log.info("INICI Dir3CaibPlugin: sincronitzar");

		try {

			protectedApi = getProtectedApi();

			if (protectedApi != null) {
								
				/*
				 * 
				 * String codigo: codi de la unitat arrel 
				 * String fechaActualizacion: data de la darrera actualització. Format: yyyy-MM-dd 
				 * String fechaSincronizacion: data en la que es va fer la primera sincronització. Format: yyyy-MM-dd 
				 * Boolean denominacionCooficial: retorni la denominació cooficial si existeix
				 * Boolean historicos: retorni el històric de les unitats
				 * Boolean contactos: indica si volem que es retornin els contactes de la unitat 
				 * 
				 */
				
				final String codigo = "A04003003";
				
				List<Object> unidades = protectedApi.obtenerArbolUnidades(codigo, null, null, true, false, false);
				
				if (unidades != null) {
					unidades.forEach(provincia -> {
	                    log.info("Unidad: " + provincia);
					});
				}else {
					log.info("No hi ha unitats");
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new I18NException("error.sincronitzardir3caib", e);
		}

		log.info(" FI Dir3CaibPlugin: sincronitar");
	}

	private DefaultApi getProtectedApi() throws Exception {

		log.info("Obtenint l'API de Dir3Caib");

		if (protectedApi != null) {
			return protectedApi;

		} else {

			final String host = Configuracio.getDir3CaibEndpoint();
			final String username = Configuracio.getDir3CaibUsername();
			final String password = Configuracio.getDir3CaibPassword();

			ApiClient client = new ApiClient();
			client.setBasePath(host);
			client.setUsername(username);
			client.setPassword(password);
			
			return new DefaultApi(client);
		}
	}
	
	private DefaultApi getPublicApi() throws Exception {

		log.info("Obtenint l'API de Dir3Caib");

		if (publicApi != null) {
			return publicApi;

		} else {

			final String host = Configuracio.getDir3CaibEndpoint();

			ApiClient client = new ApiClient();
			client.setBasePath(host);
			
			return new DefaultApi(client);
		}
	}

}
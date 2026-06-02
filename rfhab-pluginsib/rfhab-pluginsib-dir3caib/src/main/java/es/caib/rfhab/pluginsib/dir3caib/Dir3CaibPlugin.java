package es.caib.rfhab.pluginsib.dir3caib;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.dir3caib.ws.api.unidad.Dir3CaibObtenerUnidadesWs;
import es.caib.dir3caib.ws.api.unidad.Dir3CaibObtenerUnidadesWsService;
import es.caib.dir3caib.ws.api.unidad.UnidadWs;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;

import javax.xml.ws.BindingProvider;

/**
 * 
 * @author jagarcia
 * @author jpou
 */

public class Dir3CaibPlugin implements IDir3CaibPlugin {

	protected Logger log = Logger.getLogger(this.getClass());

	public void sincronitzar(String codiArrel, Timestamp fechaActualizacion, Timestamp fechaSincronizacion)
			throws I18NException {

		log.info("INICI Dir3CaibPlugin: sincronitzar");

		try {
			List<UnidadWs> unitats = obtenirArbreUnidadesV2(codiArrel, fechaActualizacion, fechaSincronizacion);

			if (unitats == null || unitats.isEmpty()) {
				log.info("No hi ha unitats DIR3 per sincronitzar");
			} else {
				log.info("Unitats DIR3 obtingudes: " + unitats.size());
			}

		} catch (Exception e) {
			log.error("Error sincronitzant amb DIR3CAIB", e);
			throw new I18NException(e, "error.sincronitzardir3caib");
		}

		log.info("FI Dir3CaibPlugin: sincronitzar");
	}

	protected List<UnidadWs> obtenirArbreUnidadesV2(String codiArrel, Timestamp fechaActualizacion,
			Timestamp fechaSincronizacion) throws Exception {
		Dir3CaibObtenerUnidadesWs client = crearClientDir3Caib();
		configurarClient(client, obtenirEndpointDir3Caib(), obtenirUsuariDir3Caib(), obtenirContrasenyaDir3Caib());

		return client.obtenerArbolUnidadesV2(codiArrel, fechaActualizacion, fechaSincronizacion);
	}

	protected Dir3CaibObtenerUnidadesWs crearClientDir3Caib() throws Exception {
		Dir3CaibObtenerUnidadesWsService service = crearServeiDir3Caib();
		return obtenirPort(service);
	}

	protected Dir3CaibObtenerUnidadesWsService crearServeiDir3Caib() throws Exception {
		String endpoint = obtenirEndpointDir3Caib();
		URL wsdlUrl = construirWsdlUrl(endpoint);

		if (wsdlUrl != null) {
			return new Dir3CaibObtenerUnidadesWsService(wsdlUrl);
		}

		return new Dir3CaibObtenerUnidadesWsService();
	}

	protected Dir3CaibObtenerUnidadesWs obtenirPort(Dir3CaibObtenerUnidadesWsService service) {
		return service.getDir3CaibObtenerUnidadesWs();
	}

	protected void configurarClient(Dir3CaibObtenerUnidadesWs client, String endpoint, String username,
			String password) {
		if (!(client instanceof BindingProvider)) {
			return;
		}

		BindingProvider bindingProvider = (BindingProvider) client;
		Map<String, Object> requestContext = bindingProvider.getRequestContext();

		String endpointNormalitzat = normalitzarEndpoint(endpoint);
		if (teText(endpointNormalitzat)) {
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointNormalitzat);
		}

		if (teText(username)) {
			requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
			requestContext.put("javax.xml.ws.security.auth.username", username);
		}

		if (teText(password)) {
			requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
			requestContext.put("javax.xml.ws.security.auth.password", password);
		}
	}

	protected URL construirWsdlUrl(String endpoint) throws Exception {
		if (!teText(endpoint)) {
			return null;
		}

		String endpointTrim = endpoint.trim();
		if (endpointTrim.toLowerCase().contains("?wsdl")) {
			return new URL(endpointTrim);
		}

		if (endpointTrim.contains("?")) {
			return new URL(endpointTrim + "&wsdl");
		}

		return new URL(endpointTrim + "?wsdl");
	}

	protected String normalitzarEndpoint(String endpoint) {
		if (!teText(endpoint)) {
			return endpoint;
		}

		String endpointTrim = endpoint.trim();
		int wsdlIndex = endpointTrim.toLowerCase().indexOf("?wsdl");
		if (wsdlIndex >= 0) {
			return endpointTrim.substring(0, wsdlIndex);
		}

		return endpointTrim;
	}

	private boolean teText(String value) {
		return value != null && !value.trim().isEmpty();
	}

	protected String obtenirEndpointDir3Caib() {
		return Configuracio.getDir3CaibEndpoint();
	}

	protected String obtenirUsuariDir3Caib() {
		return Configuracio.getDir3CaibUsername();
	}

	protected String obtenirContrasenyaDir3Caib() {
		return Configuracio.getDir3CaibPassword();
	}

}
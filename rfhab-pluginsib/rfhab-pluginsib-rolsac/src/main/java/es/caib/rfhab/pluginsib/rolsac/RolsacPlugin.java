package es.caib.rfhab.pluginsib.rolsac;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.pluginsib.rolsac.client.v1.api.ProcedimientosApi;
import es.caib.rfhab.pluginsib.rolsac.client.v1.api.TramitesApi;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.LlistarRequest14;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.LlistarRequest18;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.Procedimientos;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.RespuestaProcedimientos;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.RespuestaTramites;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.Tramites;
import es.caib.rfhab.pluginsib.rolsac.client.v1.services.ApiClient;
import es.caib.rfhab.pluginsib.rolsac.client.v1.services.auth.HttpBasicAuth;

/**
 * 
 * @author jagarcia
 */

public class RolsacPlugin implements IRolsacPlugin {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private static final String FILTRE_PROCEDIMENTS = "{\"activo\":\"1\",\"telematico\":\"1\",\"disponibleFuncionarioHabilitado\":\"1\"}";

	private static final String FILTRE_PAGINACIO = "{\"page\":\"1\",\"size\":\"500\"}";

	private ProcedimientosApi procedimientosApiClient = null;

	private TramitesApi tramitesApiClient = null;

	private Properties propietats;

	public Properties getPropietats() {
		return propietats;
	}

	public void setPropietats(Properties propietats) {
		this.propietats = propietats;
	}

	public RolsacPlugin() {
		super();
	}

	public HashMap<String, String> obtenirProcediments() throws Exception {

		/*
		 * if (procedimientosApiClient == null) { procedimientosApiClient =
		 * getProcedimientosApi(); }
		 * 
		 * // Parametres de la cerca de procediments LlistarRequest14 parametres = new
		 * LlistarRequest14(); parametres.setFiltro(FILTRE_PROCEDIMENTS);
		 * parametres.setFiltroPaginacion(FILTRE_PAGINACIO);
		 * 
		 * // Obtenim la llista de procediments RespuestaProcedimientos
		 * respostaApiProcediments = procedimientosApiClient.llistar(parametres);
		 * 
		 * // Processament List<Procedimientos> llistaProcediments =
		 * respostaApiProcediments.getResultado(); LOG.info("S'han obtingut " +
		 * llistaProcediments.size() + " procediments");
		 * 
		 * for (Procedimientos procediment : llistaProcediments) {
		 * LOG.info(procediment.getCodigo() + " " + procediment.getNombre()); }
		 */

		final String endpoint = Configuracio.getRolsacEndpoint();
		final String entitat = "procedimientos";
		final String usuari = Configuracio.getRolsacUsername();
		final String pass = Configuracio.getRolsacPassword();
		
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idioma", "es");
		map.add("filtro", FILTRE_PROCEDIMENTS);
		map.add("filtroOrdenacion", FILTRE_PAGINACIO);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		final ResponseEntity<RespuestaProcedimientos> responseProcedimientos = restTemplate
				.postForEntity(endpoint + entitat, request, RespuestaProcedimientos.class);

		if (responseProcedimientos != null && responseProcedimientos.getBody() != null) {
			if (!responseProcedimientos.getBody().getStatus().equals("200")
					|| (responseProcedimientos.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {

				final List<Procedimientos> respostaProcediments = responseProcedimientos.getBody().getResultado();
				if (respostaProcediments != null)
					LOG.info("S'han trobat " + respostaProcediments.size() + " procediments");
				else
					LOG.info("No s'han trobat procediments");

				HashMap<String, String> resultats = new HashMap<String, String>();

				if (respostaProcediments != null) 
					for (Procedimientos procediment : respostaProcediments) {
						LOG.info(procediment.getCodigo() + " " + procediment.getNombre());
						resultats.put(String.valueOf(procediment.getCodigo()), procediment.getNombre().replace("'", "`"));
					}

				return resultats;
			}
		}
		return null;

	}

	public HashMap<String, String> obtenirTramits(String procedimentId) throws Exception {

		/*
		 * if (tramitesApiClient == null) { tramitesApiClient = getTramitesApi(); }
		 * 
		 * final String filtreTramits = "{\"codigoProcedimiento\":\"" + procedimentId +
		 * "\"}";
		 * 
		 * LlistarRequest18 parametres = new LlistarRequest18();
		 * parametres.setFiltro(filtreTramits);
		 * parametres.setFiltroPaginacion(FILTRE_PAGINACIO);
		 * 
		 * RespuestaTramites respostaApiTramits = tramitesApiClient.llistar(new
		 * LlistarRequest18());
		 * 
		 * List<Tramites> llistaTramits = respostaApiTramits.getResultado();
		 * LOG.info("S'han obtingut " + llistaTramits.size() + " tramits");
		 * 
		 * for (Tramites tramit : llistaTramits) { LOG.info(tramit.getCodigo() + " " +
		 * tramit.getNombre()); }
		 */

		final String endpoint = Configuracio.getRolsacEndpoint();
		final String entitat = "tramites";
		final String usuari = Configuracio.getRolsacUsername();
		final String pass = Configuracio.getRolsacPassword();

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(usuari, pass));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idioma", "es");
		map.add("filtro", "{\"codigoProcedimiento\":\"" + procedimentId + "\"}");
		map.add("filtroOrdenacion", FILTRE_PAGINACIO);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		final ResponseEntity<RespuestaTramites> responseProcedimientos = restTemplate.postForEntity(endpoint + entitat,
				request, RespuestaTramites.class);

		if (responseProcedimientos != null && responseProcedimientos.getBody() != null) {
			if (!responseProcedimientos.getBody().getStatus().equals("200")
					|| (responseProcedimientos.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {

				final List<Tramites> respostaProcediments = responseProcedimientos.getBody().getResultado();
				LOG.info("S'han trobat " + respostaProcediments.size() + " procediments");

				HashMap<String, String> resultats = new HashMap<String, String>();

				for (Tramites procediment : respostaProcediments) {
					LOG.info(procediment.getCodigo() + " " + procediment.getNombre());
					resultats.put(String.valueOf(procediment.getCodigo()), procediment.getNombre().replace("'", "`"));
				}

				return resultats;
			}
		}

		return null;

	}

	private ProcedimientosApi getProcedimientosApi() throws Exception {

		LOG.info("Obtenint Procediments API de Rolsac");

		if (procedimientosApiClient == null) {

			final String endpoint = Configuracio.getRolsacEndpoint();
			final String usuari = Configuracio.getRolsacUsername();
			final String pass = Configuracio.getRolsacPassword();

			ApiClient apiClient = new ApiClient();
			apiClient.setBasePath(endpoint + "procedimientos");
			HttpBasicAuth basicAuth = (HttpBasicAuth) apiClient.getAuthentication("basic");
			basicAuth.setUsername(usuari);
			basicAuth.setPassword(pass);

			return new ProcedimientosApi(apiClient);
		}

		return procedimientosApiClient;

	}

	private TramitesApi getTramitesApi() throws Exception {

		LOG.info("Obtenint Tramites API de Rolsac");

		if (tramitesApiClient == null) {

			final String endpoint = Configuracio.getRolsacEndpoint();
			final String usuari = Configuracio.getRolsacUsername();
			final String pass = Configuracio.getRolsacPassword();

			ApiClient apiClient = new ApiClient();
			apiClient.setBasePath(endpoint + "tramites");
			HttpBasicAuth basicAuth = (HttpBasicAuth) apiClient.getAuthentication("basic");
			basicAuth.setUsername(usuari);
			basicAuth.setPassword(pass);

			return new TramitesApi(apiClient);

		}

		return tramitesApiClient;

	}

}
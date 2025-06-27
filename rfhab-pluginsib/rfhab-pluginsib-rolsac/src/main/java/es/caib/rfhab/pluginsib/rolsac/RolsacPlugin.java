package es.caib.rfhab.pluginsib.rolsac;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.jboss.logging.Logger;
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
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.Procedimientos;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.RespuestaProcedimientos;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.RespuestaTramites;
import es.caib.rfhab.pluginsib.rolsac.client.v1.model.Tramites;
import es.caib.rfhab.pluginsib.rolsac.client.v1.services.ApiClient;
import es.caib.rfhab.pluginsib.rolsac.client.v1.services.auth.HttpBasicAuth;

/**
 * 
 * @author jagarcia
 * @author jpou
 * 
 */
public class RolsacPlugin implements IRolsacPlugin {

	protected final Logger LOG = Logger.getLogger(this.getClass());

	private static final String FILTRE_PROCEDIMENTS = "{\"activo\":\"1\",\"telematico\":\"1\",\"disponibleFuncionarioHabilitado\":\"1\"}";

	private static final String FILTRE_PAGINACIO = "{\"page\":\"1\",\"size\":\"500\"}";

	private ProcedimientosApi procedimientosApiClient = null;

	private TramitesApi tramitesApiClient = null;

	private static final long TEMPS_CATXE_PROCEDIMENTS_I_TRAMITS = 1000 * 60 * 60 * 4; // 4 hores
	private Entry<Long, HashMap<String, String[]>> procedimentsAllCatxe = null;
	private Entry<Long, HashMap<String, String[]>> tramitsFhAllCatxe = null;

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

	public HashMap<String, String> obtenirProcedimentsByDir3(String codiDir3) throws Exception {

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
		map.add("codigoua", codiDir3);

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
						resultats.put(String.valueOf(procediment.getCodigo()),
								procediment.getNombre().replace("'", "`"));
					}

				return resultats;
			}
		}
		return null;

	}

	public HashMap<String, String[]> obtenirProcedimentsAll(String llengua, Boolean empraCatxe) throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}

		if (empraCatxe == null || empraCatxe) {
			long now = System.currentTimeMillis();
			if (procedimentsAllCatxe != null && procedimentsAllCatxe.getKey() != null
					&& (now - procedimentsAllCatxe.getKey()) < TEMPS_CATXE_PROCEDIMENTS_I_TRAMITS) {
				HashMap<String, String[]> itemsCatxe = procedimentsAllCatxe.getValue();
				Map.Entry<String, String[]> primerItem = itemsCatxe.entrySet().iterator().next();
				if (primerItem != null && primerItem.getValue()[1] == llengua) {
					LOG.info("Retornant procediments del catxe");
					return itemsCatxe;
				}
			}
			LOG.info("Procediments no trobats al catxe, s'ha de tornar a cridar a Rolsac");
		}
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
		// map.add("idioma", "es");
		// map.add("filtro", FILTRE_PROCEDIMENTS);
		// map.add("filtroOrdenacion", FILTRE_PAGINACIO);
		map.add("lang", llengua);
		map.add("filtro", FILTRE_PROCEDIMENTS);
		map.add("filtroPaginacion", FILTRE_PAGINACIO);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		LOG.info("Cridant a Rolsac: " + endpoint + entitat);
		LOG.info("Amb filtre: " + FILTRE_PROCEDIMENTS);
		LOG.info("Amb paginacio: " + FILTRE_PAGINACIO);
		LOG.info("Amb usuari: " + usuari);
		LOG.info("Amb password: " + pass);
		LOG.info("Amb request: " + request);
		LOG.info("Amb headers: " + headers);
		LOG.info("Amb map: " + map);
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

				HashMap<String, String[]> resultats = new HashMap<String, String[]>();

				if (respostaProcediments != null)
					for (Procedimientos procediment : respostaProcediments) {
						LOG.info(procediment.getCodigo() + " " + procediment.getNombre());
						resultats.put(String.valueOf(procediment.getCodigo()),
								new String[] { procediment.getNombre().replace("'", "`"),
										llengua });
					}

				procedimentsAllCatxe = Map.entry(System.currentTimeMillis(), resultats);
				return resultats;
			}
		}
		procedimentsAllCatxe = null;
		return null;
	}

	public HashMap<String, String[]> obtenirTramits(String procedimentId, String llengua)
			throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}
		if(procedimentId == null){
			procedimentId = "";
		}
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
		map.add("idioma", llengua);
		final String filtreTramits = "{\"codigoProcedimiento\":\"" + procedimentId + "\"}";
		map.add("filtro", filtreTramits);
		map.add("filtroOrdenacion", FILTRE_PAGINACIO);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		LOG.info("Cridant a Rolsac: " + endpoint + entitat);
		LOG.info("Amb filtre: " + filtreTramits);
		LOG.info("Amb paginacio: " + FILTRE_PAGINACIO);
		LOG.info("Amb usuari: " + usuari);
		LOG.info("Amb password: " + pass);
		LOG.info("Amb request: " + request);
		LOG.info("Amb headers: " + headers);
		LOG.info("Amb map: " + map);
		final ResponseEntity<RespuestaTramites> responseTramites = restTemplate.postForEntity(endpoint + entitat,
				request, RespuestaTramites.class);

		if (responseTramites != null && responseTramites.getBody() != null) {
			if (!responseTramites.getBody().getStatus().equals("200")
					|| (responseTramites.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {

				final List<Tramites> respostaTramits = responseTramites.getBody().getResultado();
				LOG.info("S'han trobat " + respostaTramits.size() + " procediments");

				HashMap<String, String[]> resultats = new HashMap<String, String[]>();

				for (Tramites tramit : respostaTramits) {
					LOG.info(tramit.getCodigo() + " " + tramit.getNombre() + " "
							+ tramit.getLinkProcedimiento().getCodigo());
					resultats.put(String.valueOf(tramit.getCodigo()),
							new String[] { tramit.getNombre().replace("'", "`"),
									tramit.getLinkProcedimiento().getCodigo(), llengua });
				}

				return resultats;
			}
		}

		return null;
	}

	public HashMap<String, String[]> obtenirTramitsAll(String llengua, Boolean empraCatxe)
			throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}

		if (empraCatxe == null || empraCatxe) {
			long now = System.currentTimeMillis();
			if (tramitsFhAllCatxe != null && tramitsFhAllCatxe.getKey() != null
					&& (now - tramitsFhAllCatxe.getKey()) < TEMPS_CATXE_PROCEDIMENTS_I_TRAMITS) {
				HashMap<String, String[]> itemsCatxe = tramitsFhAllCatxe.getValue();
				Map.Entry<String, String[]> primerItem = itemsCatxe.entrySet().iterator().next();
				if (primerItem != null && primerItem.getValue()[1] == llengua) {
					LOG.info("Retornant tramits del catxe");
					return itemsCatxe;
				}
			}
			LOG.info("Tramits no trobats al catxe, s'ha de tornar a cridar a Rolsac");
		}

		HashMap<String, String[]> resultats = obtenirTramits(null, llengua);
		if (resultats == null) {
			tramitsFhAllCatxe = null;
		} else {
			tramitsFhAllCatxe = Map.entry(System.currentTimeMillis(), resultats);
		}
		return resultats;
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
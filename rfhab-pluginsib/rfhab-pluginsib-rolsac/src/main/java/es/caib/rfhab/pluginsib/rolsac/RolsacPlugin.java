package es.caib.rfhab.pluginsib.rolsac;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.jboss.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import es.caib.rfhab.commons.utils.Configuracio;
import es.caib.rfhab.pluginsib.rolsac.model.Plantillas;
import es.caib.rfhab.pluginsib.rolsac.model.Procediments;
import es.caib.rfhab.pluginsib.rolsac.model.respostes.RespuestaProcediments;
import es.caib.rfhab.pluginsib.rolsac.model.respostes.RespuestaSimple;
import es.caib.rfhab.pluginsib.rolsac.model.respostes.RespuestaTramits;
import es.caib.rfhab.pluginsib.rolsac.model.Tramits;

/**
 * 
 * @author jagarcia
 * @author jpou
 * 
 */
// TODO:convertir això a EJB o a Singleton per a poder emprar correctament la
// catxé
public class RolsacPlugin implements IRolsacPlugin {

	protected final Logger LOG = Logger.getLogger(this.getClass());

	private static final String FILTRE_PROCEDIMENTS = "{\"activo\":\"1\",\"telematico\":\"1\",\"disponibleFuncionarioHabilitado\":\"1\"}";

	private static final String FILTRE_PAGINACIO = "{\"page\":\"1\",\"size\":\"500\"}";

	private static final long TEMPS_CATXE_PROCEDIMENTS_I_TRAMITS = 1000 * 60 * 60 * 24; // 24 hores
	final String ENDPOINT = Configuracio.getRolsacEndpoint();
	final String USUARI = Configuracio.getRolsacUsername();
	final String PASS = Configuracio.getRolsacPassword();

	AsyncLoadingCache<String, HashMap<String, String[]>> procedimentsAllCatxe = Caffeine.newBuilder()
			// .maximumSize(100)// TODO???
			.expireAfterWrite(TEMPS_CATXE_PROCEDIMENTS_I_TRAMITS, TimeUnit.MILLISECONDS)
			.buildAsync(k -> getProcedimentsPerLlenguaFromService(k));
	AsyncLoadingCache<String, HashMap<String, String[]>> tramitsFhAllCatxe = Caffeine.newBuilder()
			// .maximumSize(100)// TODO???
			.expireAfterWrite(TEMPS_CATXE_PROCEDIMENTS_I_TRAMITS, TimeUnit.MILLISECONDS)
			.buildAsync(k -> getTramitsPerLlengua(k));
	AsyncLoadingCache<String, String> codiDir3UnitatsAdministrativesCatxe = Caffeine.newBuilder()
			// .maximumSize(100)// TODO???
			.expireAfterWrite(TEMPS_CATXE_PROCEDIMENTS_I_TRAMITS, TimeUnit.MILLISECONDS)
			.buildAsync(k -> getCodiDir3PerUnitatAdministrativaFromService(k));

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

		final String entitat = "procedimientos";

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(USUARI, PASS));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idioma", "es");
		map.add("filtro", FILTRE_PROCEDIMENTS);
		map.add("filtroOrdenacion", FILTRE_PAGINACIO);
		map.add("codigoua", codiDir3);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		final ResponseEntity<RespuestaProcediments> responseProcedimientos = restTemplate
				.postForEntity(ENDPOINT + entitat, request, RespuestaProcediments.class);

		if (responseProcedimientos != null && responseProcedimientos.getBody() != null) {
			if (!responseProcedimientos.getBody().getStatus().equals("200")
					|| (responseProcedimientos.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {

				final List<Procediments> respostaProcediments = responseProcedimientos.getBody().getResultado();
				if (respostaProcediments != null)
					LOG.info("S'han trobat " + respostaProcediments.size() + " procediments");
				else
					LOG.info("No s'han trobat procediments");

				HashMap<String, String> resultats = new HashMap<String, String>();

				if (respostaProcediments != null)
					for (Procediments procediment : respostaProcediments) {
						LOG.info(procediment.getCodigo() + " " + procediment.getNombre());
						resultats.put(String.valueOf(procediment.getCodigo()),
								procediment.getNombre().replace("'", "`"));
					}

				return resultats;
			}
		}
		return null;

	}

	public HashMap<String, String[]> obtenirProcedimentsAll(String llengua) throws Exception {
		return procedimentsAllCatxe.get(llengua).thenApply(resultats -> {
			if (resultats == null) {
				LOG.info("No s'han trobat procediments per a la llengua: " + llengua);
				return new HashMap<String, String[]>();
			} else {
				LOG.info("S'han trobat " + resultats.size() + " procediments per a la llengua: " + llengua);
				return resultats;
			}
		}).get();
	}

	private HashMap<String, String[]> getProcedimentsPerLlenguaFromService(String llengua) throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}

		final String entitat = "procedimientos";

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

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(USUARI, PASS));
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

		LOG.info("Cridant a Rolsac: " + ENDPOINT + entitat);
		LOG.info("Amb filtre: " + FILTRE_PROCEDIMENTS);
		LOG.info("Amb paginacio: " + FILTRE_PAGINACIO);
		LOG.info("Amb usuari: " + USUARI);
		LOG.info("Amb password: " + PASS);
		LOG.info("Amb request: " + request);
		LOG.info("Amb headers: " + headers);
		LOG.info("Amb map: " + map);
		final ResponseEntity<RespuestaProcediments> responseProcedimientos = restTemplate
				.postForEntity(ENDPOINT + entitat, request, RespuestaProcediments.class);

		if (responseProcedimientos != null && responseProcedimientos.getBody() != null) {
			if (!responseProcedimientos.getBody().getStatus().equals("200")
					|| (responseProcedimientos.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {

				final List<Procediments> respostaProcediments = responseProcedimientos.getBody().getResultado();
				if (respostaProcediments != null)
					LOG.info("S'han trobat " + respostaProcediments.size() + " procediments");
				else
					LOG.info("No s'han trobat procediments");

				HashMap<String, String[]> resultats = new HashMap<String, String[]>();

				if (respostaProcediments != null)
					for (Procediments procediment : respostaProcediments) {
						LOG.info(procediment.getCodigo() + " - " + procediment.getCodigoSIA() + " - "
								+ procediment.getNombre());
						resultats.put(String.valueOf(procediment.getCodigo()),
								new String[] { procediment.getNombre().replace("'", "`"),
										llengua, procediment.getCodigoSIA() });
					}

				return resultats;
			}
		}
		return null;
	}

	public HashMap<String, String[]> obtenirTramits(String procedimentId, String llengua) throws Exception {
		HashMap<String, String[]> tramitsPerLlengua = null;
		HashMap<String, String[]> tramitsTrobats = new HashMap<String, String[]>();
		java.util.concurrent.CompletableFuture<HashMap<String, String[]>> future = tramitsFhAllCatxe
				.getIfPresent(llengua);
		if (future != null) {
			tramitsPerLlengua = future.thenApply(resultats -> {
				if (resultats == null) {
					LOG.info("No s'han trobat tràmits per a la llengua: " + llengua);
					return new HashMap<String, String[]>();
				} else {
					LOG.info("S'han trobat " + resultats.size() + " tràmits per a la llengua: " + llengua);
					return resultats;
				}
			}).get();
		} else {
			tramitsPerLlengua = new HashMap<String, String[]>();
		}

		for (Map.Entry<String, String[]> entry : tramitsPerLlengua.entrySet()) {
			String[] values = entry.getValue();
			if (values != null && values.length > 1 && procedimentId.equals(values[1])) {
				tramitsTrobats.put(entry.getKey(), values);
			}
		}
		if (tramitsTrobats.isEmpty()) {
			HashMap<String, String[]> resultats = getTramitsPerLlengua(procedimentId, llengua);
			if (resultats != null) {
				tramitsTrobats.putAll(resultats);
				LOG.info("S'han trobat " + resultats.size() + " tràmits per al procediment: " + procedimentId);
				// Actualitzem el catxe amb els resultats trobats
				tramitsPerLlengua.putAll(resultats);
				tramitsFhAllCatxe.put(llengua,
						java.util.concurrent.CompletableFuture.completedFuture(tramitsPerLlengua));
			}
		}
		return tramitsTrobats;
	}

	public Tramits obtenirTramitPerId(String tramitId, String llengua) throws Exception {
		if (tramitId == null || tramitId.isEmpty()) {
			LOG.warn("El codi del tràmit no pot ser null o buit");
			return null;
		}

		final String entitat = "tramites/" + tramitId;

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(USUARI, PASS));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("lang", llengua);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		LOG.info("Cridant a Rolsac: " + ENDPOINT + entitat);
		LOG.debug("Amb usuari: " + USUARI);
		LOG.debug("Amb password: " + PASS);
		LOG.info("Amb request: " + request);

		final ResponseEntity<RespuestaTramits> responseTramits = restTemplate
				.postForEntity(ENDPOINT + entitat, request, RespuestaTramits.class);

		if (responseTramits != null && responseTramits.getBody() != null) {
			if (!responseTramits.getBody().getStatus().equals("200")) {
				LOG.warn("Error en la resposta de Rolsac: status=" + responseTramits.getBody().getStatus());
				return null;
			} else {
				final List<Tramits> tramit = responseTramits.getBody().getResultado();
				if (tramit != null && !tramit.isEmpty()) {
					LOG.info("S'ha trobat el tramit: " + tramit + " per al codi: " + tramitId);
					return tramit.get(0);
				} else {
					LOG.info("No s'ha trobat el tramit: " + tramitId);
					return null;
				}
			}
		}

		LOG.warn("Resposta nul·la de Rolsac per al tràmit: " + tramitId);
		return null;
	}

	private HashMap<String, String[]> getTramitsPerLlengua(String procedimentId, String llengua)
			throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}
		if (procedimentId == null) {
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

		final String entitat = "tramites";

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(USUARI, PASS));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idioma", llengua);
		final String filtreTramits = "{\"codigoProcedimiento\":\"" + procedimentId + "\"}";
		map.add("filtro", filtreTramits);
		map.add("filtroOrdenacion", FILTRE_PAGINACIO);

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		LOG.info("Cridant a Rolsac: " + ENDPOINT + entitat);
		LOG.debug("Amb usuari: " + USUARI);
		LOG.debug("Amb password: " + PASS);
		LOG.info("Amb request: " + request);
		final ResponseEntity<RespuestaTramits> responseTramites = restTemplate.postForEntity(ENDPOINT + entitat,
				request, RespuestaTramits.class);

		if (responseTramites != null && responseTramites.getBody() != null) {
			if (!responseTramites.getBody().getStatus().equals("200")
					|| (responseTramites.getBody().getNumeroElementos().equals("0"))) {
				return null;
			} else {

				final List<Tramits> respostaTramits = responseTramites.getBody().getResultado();
				LOG.info("S'han trobat " + respostaTramits.size() + " tramits");

				HashMap<String, String[]> resultats = new HashMap<String, String[]>();

				for (Tramits tramit : respostaTramits) {
					LOG.info("Tràmit trobat - Codi: " + tramit.getCodigo() +
							", Nom: " + tramit.getNombre() +
							", Codi Procediment: " + tramit.getLink_procedimiento().getCodigo() +
							", Llengua: " + llengua +
							", Versió: " + tramit.getVersio() +
							", Paràmetres: " + tramit.getParametros() +
							", IdTraTel: " + tramit.getIdTraTel() +
							", Codi Organ Competent: " + tramit.getLink_organCompetent().getCodigo());
					Plantillas plantillaTramit = tramit.getPlantilla();
					resultats.put(String.valueOf(tramit.getCodigo()),
							new String[] {
									(plantillaTramit != null && plantillaTramit.getNombre() != null)
											? plantillaTramit.getNombre().replace("'", "`")
											: tramit.getNombre().replace("'", "`"),
									tramit.getLink_procedimiento().getCodigo(),
									llengua,
									String.valueOf(tramit.getCodigo()),
									(plantillaTramit != null && plantillaTramit.getVersion() != null)
											? String.valueOf(plantillaTramit.getVersion())
											: String.valueOf(tramit.getVersio()),
									(plantillaTramit != null && plantillaTramit.getParametros() != null)
											? String.valueOf(plantillaTramit.getParametros())
											: String.valueOf(tramit.getParametros()),
									(plantillaTramit != null && plantillaTramit.getIdentificador() != null)
											? String.valueOf(plantillaTramit.getIdentificador().trim())
											: String.valueOf(tramit.getIdTraTel().trim()),
									tramit.getLink_organCompetent().getCodigo()
							});
				}

				return resultats;
			}
		}

		return null;
	}

	public HashMap<String, String[]> obtenirTramitsAll(String llengua)
			throws Exception {
		return tramitsFhAllCatxe.get(llengua).thenApply(resultats -> {
			if (resultats == null) {
				LOG.info("No s'han trobat tramits per a la llengua: " + llengua);
				return new HashMap<String, String[]>();
			} else {
				LOG.info("S'han trobat " + resultats.size() + " tramits per a la llengua: " + llengua);
				return resultats;
			}
		}).get();
	}

	private HashMap<String, String[]> getTramitsPerLlengua(String llengua)
			throws Exception {
		if (llengua == null || llengua.isEmpty()) {
			llengua = "ca";
		}

		return getTramitsPerLlengua(null, llengua);
	}

	public String getCodiDir3UnitatAdministrativa(String codi) throws InterruptedException, ExecutionException {
		if (codi == null || codi.isEmpty()) {
			LOG.warn("El codi de la unitat administrativa no pot ser null o buit");
			return null;
		}

		return codiDir3UnitatsAdministrativesCatxe.get(codi).thenApply(resultat -> {
			if (resultat == null || resultat.isEmpty()) {
				LOG.info("No s'ha trobat el codiDir3 per a la unitat administrativa: " + codi);
				return null;
			} else {
				LOG.info("S'ha trobat el codiDir3: " + resultat + " per a la unitat administrativa: " + codi);
				return resultat;
			}
		}).get();
	}

	private String getCodiDir3PerUnitatAdministrativaFromService(String codi) throws Exception {
		if (codi == null || codi.isEmpty()) {
			LOG.warn("El codi de la unitat administrativa no pot ser null o buit");
			return null;
		}

		final String entitat = "unidades_administrativas/codigoDir3/" + codi;

		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(USUARI, PASS));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		LOG.info("Cridant a Rolsac: " + ENDPOINT + entitat);
		LOG.debug("Amb usuari: " + USUARI);
		LOG.debug("Amb password: " + PASS);
		LOG.info("Amb request: " + request);

		final ResponseEntity<RespuestaSimple> responseUnitatAdministrativa = restTemplate
				.postForEntity(ENDPOINT + entitat, request, RespuestaSimple.class);

		if (responseUnitatAdministrativa != null && responseUnitatAdministrativa.getBody() != null) {
			if (!responseUnitatAdministrativa.getBody().getStatus().equals("200")) {
				LOG.warn(
						"Error en la resposta de Rolsac: status=" + responseUnitatAdministrativa.getBody().getStatus());
				return null;
			} else {
				final String codiDir3 = responseUnitatAdministrativa.getBody().getResultado();
				if (codiDir3 != null && !codiDir3.isEmpty()) {
					LOG.info("S'ha trobat el codiDir3: " + codiDir3 + " per a la unitat administrativa: " + codi);
					return codiDir3;
				} else {
					LOG.info("No s'ha trobat el codiDir3 per a la unitat administrativa: " + codi);
					return null;
				}
			}
		}

		LOG.warn("Resposta nul·la de Rolsac per a la unitat administrativa: " + codi);
		return null;
	}
}
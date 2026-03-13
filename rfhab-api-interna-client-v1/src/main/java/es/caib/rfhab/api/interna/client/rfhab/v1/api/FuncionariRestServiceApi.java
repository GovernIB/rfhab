package es.caib.rfhab.api.interna.client.rfhab.v1.api;

import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiException;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiClient;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Configuration;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.rfhab.api.interna.client.rfhab.v1.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class FuncionariRestServiceApi {
  private ApiClient apiClient;

  public FuncionariRestServiceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public FuncionariRestServiceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Comprobar si un funcionari té permís per realitzar un trámit
   * 
   * @param usuari Usuari del funcionari habilitat (required)
   * @param codisia Codi SIA (required)
   * @param entitat Codi de la entitat (required)
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String comprobarAutoritzaci(List<String> usuari, List<String> codisia, List<String> entitat, String language) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'usuari' is set
    if (usuari == null) {
      throw new ApiException(400, "Missing the required parameter 'usuari' when calling comprobarAutoritzaci");
    }
    
    // verify the required parameter 'codisia' is set
    if (codisia == null) {
      throw new ApiException(400, "Missing the required parameter 'codisia' when calling comprobarAutoritzaci");
    }
    
    // verify the required parameter 'entitat' is set
    if (entitat == null) {
      throw new ApiException(400, "Missing the required parameter 'entitat' when calling comprobarAutoritzaci");
    }
    
    // create path and map variables
    String localVarPath = "/secure/funcionari/autoritzat".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "usuari", usuari));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "codisia", codisia));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "entitat", entitat));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Consulta les habilitacions d&#39;un funcionari habilitat
   * 
   * @param usuari Nom d&#39;usuari del funcionari (required)
   * @param entitat Codi de la entitat (required)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String habilitacions(List<String> usuari, List<String> entitat) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'usuari' is set
    if (usuari == null) {
      throw new ApiException(400, "Missing the required parameter 'usuari' when calling habilitacions");
    }
    
    // verify the required parameter 'entitat' is set
    if (entitat == null) {
      throw new ApiException(400, "Missing the required parameter 'entitat' when calling habilitacions");
    }
    
    // create path and map variables
    String localVarPath = "/secure/funcionari/habilitacions".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "usuari", usuari));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "entitat", entitat));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Obtenir les habilitacions associades a un/a funcionari/ària
   * 
   * @param usuari Usuari del funcionari (required)
   * @param habilitacio Habilitació (required)
   * @param entitat Codi de la entitat (required)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String obtenirHabilitacionsFuncionari(List<String> usuari, List<String> habilitacio, List<String> entitat) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'usuari' is set
    if (usuari == null) {
      throw new ApiException(400, "Missing the required parameter 'usuari' when calling obtenirHabilitacionsFuncionari");
    }
    
    // verify the required parameter 'habilitacio' is set
    if (habilitacio == null) {
      throw new ApiException(400, "Missing the required parameter 'habilitacio' when calling obtenirHabilitacionsFuncionari");
    }
    
    // verify the required parameter 'entitat' is set
    if (entitat == null) {
      throw new ApiException(400, "Missing the required parameter 'entitat' when calling obtenirHabilitacionsFuncionari");
    }
    
    // create path and map variables
    String localVarPath = "/secure/funcionari/habilitat".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "usuari", usuari));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "habilitacio", habilitacio));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "entitat", entitat));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}

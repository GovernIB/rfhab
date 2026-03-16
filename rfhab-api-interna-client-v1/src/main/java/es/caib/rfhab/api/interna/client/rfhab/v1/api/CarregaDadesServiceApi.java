package es.caib.rfhab.api.interna.client.rfhab.v1.api;

import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiException;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiClient;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Configuration;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.rfhab.api.interna.client.rfhab.v1.model.FitxersCarrega;
import es.caib.rfhab.api.interna.client.rfhab.v1.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class CarregaDadesServiceApi {
  private ApiClient apiClient;

  public CarregaDadesServiceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public CarregaDadesServiceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Carrega inicial de dades
   * 
   * @param fitxersCarrega Fitxers (required)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String carregaInicialDades(FitxersCarrega fitxersCarrega) throws ApiException {
    Object localVarPostBody = fitxersCarrega;
    
    // verify the required parameter 'fitxersCarrega' is set
    if (fitxersCarrega == null) {
      throw new ApiException(400, "Missing the required parameter 'fitxersCarrega' when calling carregaInicialDades");
    }
    
    // create path and map variables
    String localVarPath = "/secure/carregadades/carregainicial".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}

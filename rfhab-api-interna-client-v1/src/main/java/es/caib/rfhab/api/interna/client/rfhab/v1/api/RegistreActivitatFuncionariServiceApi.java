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
public class RegistreActivitatFuncionariServiceApi {
  private ApiClient apiClient;

  public RegistreActivitatFuncionariServiceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public RegistreActivitatFuncionariServiceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Registra una activiat d&#39;un funcionari
   * 
   * @param funcionari NIF del/de la funcionari/ària que realitza la activitat (required)
   * @param tipus Tipus de l&#39;activitat:&lt;br /&gt;&amp;emsp;&lt;i&gt;1 - Còpia autèntica&lt;br /&gt;&amp;emsp;2 - Final tràmit&lt;/i&gt; (required)
   * @param data Data de registre de l&#39;activitat (required)
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @param identificadorcopiaautentica Identificador de còpia autèntica, podria ser un identificador intern de digitalib o bé el csv de la còpia autèntica. Obligatori pel tipus d&#39;activitat 1 (optional)
   * @param registre Número de registre associat a l&#39;activitat. Obligatori pel tipus d&#39;activitat 2 (optional)
   * @param idactuaciotramitfh Identificador associat a l&#39;activitat de tràmit iniciada que es preten marcar com a finalitzada. Obligatori pel tipus d&#39;activitat 2 (optional)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String registreActivitat(String funcionari, String tipus, String data, String language, String identificadorcopiaautentica, String registre, String idactuaciotramitfh) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'funcionari' is set
    if (funcionari == null) {
      throw new ApiException(400, "Missing the required parameter 'funcionari' when calling registreActivitat");
    }
    
    // verify the required parameter 'tipus' is set
    if (tipus == null) {
      throw new ApiException(400, "Missing the required parameter 'tipus' when calling registreActivitat");
    }
    
    // verify the required parameter 'data' is set
    if (data == null) {
      throw new ApiException(400, "Missing the required parameter 'data' when calling registreActivitat");
    }
    
    // create path and map variables
    String localVarPath = "/secure/activitat/registre".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "funcionari", funcionari));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "tipus", tipus));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "data", data));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "identificadorcopiaautentica", identificadorcopiaautentica));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "registre", registre));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "idactuaciotramitfh", idactuaciotramitfh));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}

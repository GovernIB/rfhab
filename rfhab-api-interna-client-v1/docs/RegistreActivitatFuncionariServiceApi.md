# RegistreActivitatFuncionariServiceApi

All URIs are relative to */rfhabapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**registreActivitat**](RegistreActivitatFuncionariServiceApi.md#registreActivitat) | **POST** /secure/activitat/registre | Registra una activiat d&#39;un funcionari |



## registreActivitat

> String registreActivitat(funcionari, tipus, data, language, identificadorcopiaautentica, registre, idactuaciotramitfh)

Registra una activiat d&#39;un funcionari

### Example

```java
// Import classes:
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiClient;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiException;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Configuration;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.auth.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.models.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.api.RegistreActivitatFuncionariServiceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/rfhabapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        RegistreActivitatFuncionariServiceApi apiInstance = new RegistreActivitatFuncionariServiceApi(defaultClient);
        String funcionari = "44444444A"; // String | NIF del/de la funcionari/ària que realitza la activitat
        String tipus = "1"; // String | Tipus de l'activitat:<br />&emsp;<i>1 - Còpia autèntica<br />&emsp;2 - Final tràmit</i>
        String data = "2025-08-31T06:15:00+00:00"; // String | Data de registre de l'activitat
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        String identificadorcopiaautentica = "klñjjskadfjklsdkl/asdlfjsd"; // String | Identificador de còpia autèntica, podria ser un identificador intern de digitalib o bé el csv de la còpia autèntica. Obligatori pel tipus d'activitat 1
        String registre = "registre_example"; // String | Número de registre associat a l'activitat. Obligatori pel tipus d'activitat 2
        String idactuaciotramitfh = "idactuaciotramitfh_example"; // String | Identificador associat a l'activitat de tràmit iniciada que es preten marcar com a finalitzada. Obligatori pel tipus d'activitat 2
        try {
            String result = apiInstance.registreActivitat(funcionari, tipus, data, language, identificadorcopiaautentica, registre, idactuaciotramitfh);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling RegistreActivitatFuncionariServiceApi#registreActivitat");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **funcionari** | **String**| NIF del/de la funcionari/ària que realitza la activitat | |
| **tipus** | **String**| Tipus de l&#39;activitat:&lt;br /&gt;&amp;emsp;&lt;i&gt;1 - Còpia autèntica&lt;br /&gt;&amp;emsp;2 - Final tràmit&lt;/i&gt; | [enum: 1, 2] |
| **data** | **String**| Data de registre de l&#39;activitat | |
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |
| **identificadorcopiaautentica** | **String**| Identificador de còpia autèntica, podria ser un identificador intern de digitalib o bé el csv de la còpia autèntica. Obligatori pel tipus d&#39;activitat 1 | [optional] |
| **registre** | **String**| Número de registre associat a l&#39;activitat. Obligatori pel tipus d&#39;activitat 2 | [optional] |
| **idactuaciotramitfh** | **String**| Identificador associat a l&#39;activitat de tràmit iniciada que es preten marcar com a finalitzada. Obligatori pel tipus d&#39;activitat 2 | [optional] |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament. |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |


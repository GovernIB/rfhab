# FuncionariRestServiceApi

All URIs are relative to */rfhabapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**comprobarAutoritzaci**](FuncionariRestServiceApi.md#comprobarAutoritzaci) | **GET** /secure/funcionari/autoritzat | Comprobar si un funcionari té permís per realitzar un trámit |
| [**habilitacions**](FuncionariRestServiceApi.md#habilitacions) | **GET** /secure/funcionari/habilitacions | Consulta les habilitacions d&#39;un funcionari habilitat |
| [**obtenirHabilitacionsFuncionari**](FuncionariRestServiceApi.md#obtenirHabilitacionsFuncionari) | **GET** /secure/funcionari/habilitat | Obtenir les habilitacions associades a un/a funcionari/ària |



## comprobarAutoritzaci

> String comprobarAutoritzaci(usuari, codisia, entitat, language)

Comprobar si un funcionari té permís per realitzar un trámit

### Example

```java
// Import classes:
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiClient;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiException;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Configuration;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.auth.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.models.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.api.FuncionariRestServiceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/rfhabapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FuncionariRestServiceApi apiInstance = new FuncionariRestServiceApi(defaultClient);
        List<String> usuari = Arrays.asList(); // List<String> | Usuari del funcionari habilitat
        List<String> codisia = Arrays.asList(); // List<String> | Codi SIA
        List<String> entitat = Arrays.asList(); // List<String> | Codi de la entitat
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            String result = apiInstance.comprobarAutoritzaci(usuari, codisia, entitat, language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FuncionariRestServiceApi#comprobarAutoritzaci");
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
| **usuari** | [**List&lt;String&gt;**](String.md)| Usuari del funcionari habilitat | |
| **codisia** | [**List&lt;String&gt;**](String.md)| Codi SIA | |
| **entitat** | [**List&lt;String&gt;**](String.md)| Codi de la entitat | |
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

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
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |


## habilitacions

> String habilitacions(usuari, entitat)

Consulta les habilitacions d&#39;un funcionari habilitat

### Example

```java
// Import classes:
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiClient;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiException;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Configuration;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.auth.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.models.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.api.FuncionariRestServiceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/rfhabapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FuncionariRestServiceApi apiInstance = new FuncionariRestServiceApi(defaultClient);
        List<String> usuari = Arrays.asList(); // List<String> | Nom d'usuari del funcionari
        List<String> entitat = Arrays.asList(); // List<String> | Codi de la entitat
        try {
            String result = apiInstance.habilitacions(usuari, entitat);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FuncionariRestServiceApi#habilitacions");
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
| **usuari** | [**List&lt;String&gt;**](String.md)| Nom d&#39;usuari del funcionari | |
| **entitat** | [**List&lt;String&gt;**](String.md)| Codi de la entitat | |

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
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |


## obtenirHabilitacionsFuncionari

> String obtenirHabilitacionsFuncionari(usuari, habilitacio, entitat)

Obtenir les habilitacions associades a un/a funcionari/ària

### Example

```java
// Import classes:
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiClient;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiException;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Configuration;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.auth.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.models.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.api.FuncionariRestServiceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/rfhabapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        FuncionariRestServiceApi apiInstance = new FuncionariRestServiceApi(defaultClient);
        List<String> usuari = Arrays.asList(); // List<String> | Usuari del funcionari
        List<String> habilitacio = Arrays.asList(); // List<String> | Habilitació
        List<String> entitat = Arrays.asList(); // List<String> | Codi de la entitat
        try {
            String result = apiInstance.obtenirHabilitacionsFuncionari(usuari, habilitacio, entitat);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FuncionariRestServiceApi#obtenirHabilitacionsFuncionari");
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
| **usuari** | [**List&lt;String&gt;**](String.md)| Usuari del funcionari | |
| **habilitacio** | [**List&lt;String&gt;**](String.md)| Habilitació | |
| **entitat** | [**List&lt;String&gt;**](String.md)| Codi de la entitat | |

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
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |


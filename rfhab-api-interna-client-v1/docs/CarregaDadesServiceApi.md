# CarregaDadesServiceApi

All URIs are relative to */rfhabapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**carregaInicialDades**](CarregaDadesServiceApi.md#carregaInicialDades) | **POST** /secure/carregadades/carregainicial | Carrega inicial de dades |



## carregaInicialDades

> String carregaInicialDades(fitxersCarrega)

Carrega inicial de dades

### Example

```java
// Import classes:
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiClient;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.ApiException;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.Configuration;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.auth.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.services.models.*;
import es.caib.rfhab.api.interna.client.rfhab.v1.api.CarregaDadesServiceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/rfhabapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        CarregaDadesServiceApi apiInstance = new CarregaDadesServiceApi(defaultClient);
        FitxersCarrega fitxersCarrega = new FitxersCarrega(); // FitxersCarrega | Fitxers
        try {
            String result = apiInstance.carregaInicialDades(fitxersCarrega);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CarregaDadesServiceApi#carregaInicialDades");
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
| **fitxersCarrega** | [**FitxersCarrega**](FitxersCarrega.md)| Fitxers | |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Operació realitzada correctament |  -  |
| **400** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **500** | Error no controlat |  -  |


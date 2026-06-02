package es.caib.rfhab.pluginsib.dir3caib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.junit.Test;

import es.caib.dir3caib.ws.api.unidad.Dir3CaibObtenerUnidadesWs;
import es.caib.dir3caib.ws.api.unidad.UnidadWs;

public class Dir3CaibPluginTest {

    private static final String TEST_PROPERTIES_FILE = "di3caibplugintest.properties";
    private static final String KEY_ENDPOINT = "dir3caib.endpoint";
    private static final String KEY_USUARI = "dir3caib.usuari";
    private static final String KEY_CONTRASENYA = "dir3caib.contrasenya";

    private static final Properties TEST_PROPERTIES = carregarPropertiesTest();
    private static final String ENDPOINT = propietatObligatoria(KEY_ENDPOINT);
    private static final String USUARI = propietatObligatoria(KEY_USUARI);
    private static final String CONTRASENYA = propietatObligatoria(KEY_CONTRASENYA);

    @Test
    public void sincronitzarHaDeCridarObtenirArbreV2AmbParametresRebuts() throws Exception {
        ClientDouble client = new ClientDouble();
        client.setResposta(Collections.singletonList(new UnidadWs()));

        Dir3CaibPlugin plugin = new TestableDir3CaibPlugin(client.getClient(),
                ENDPOINT, USUARI, CONTRASENYA);

        plugin.sincronitzar("A04003003", null, null);

        assertTrue(((TestableDir3CaibPlugin) plugin).isCrearClientCridat());
        assertTrue(client.isObtenirArbreInvocat());
        assertEquals("A04003003", client.getCodiRebut());
        assertNull(client.getDataActualitzacioRebuda());
        assertNull(client.getDataSincronitzacioRebuda());

        assertEquals(normalitzarEndpoint(ENDPOINT),
                client.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
        assertEquals(USUARI, client.getRequestContext().get(BindingProvider.USERNAME_PROPERTY));
        assertEquals(CONTRASENYA, client.getRequestContext().get(BindingProvider.PASSWORD_PROPERTY));
        assertEquals(USUARI, client.getRequestContext().get("javax.xml.ws.security.auth.username"));
        assertEquals(CONTRASENYA, client.getRequestContext().get("javax.xml.ws.security.auth.password"));
    }

    @Test
    public void sincronitzarHaDePassarDatesQuanSInformen() throws Exception {
        ClientDouble client = new ClientDouble();
        client.setResposta(Collections.<UnidadWs>emptyList());
        Timestamp fechaActualizacion = Timestamp.valueOf("2026-06-02 10:15:30");
        Timestamp fechaSincronizacion = Timestamp.valueOf("2026-06-01 08:00:00");

        Dir3CaibPlugin plugin = new TestableDir3CaibPlugin(client.getClient(),
            normalitzarEndpoint(ENDPOINT), USUARI, CONTRASENYA);

        plugin.sincronitzar("U0001", fechaActualizacion, fechaSincronizacion);

        assertEquals("U0001", client.getCodiRebut());
        assertEquals(fechaActualizacion, client.getDataActualitzacioRebuda());
        assertEquals(fechaSincronizacion, client.getDataSincronitzacioRebuda());
    }

    @Test
    public void sincronitzarHaDeRellancarI18NExceptionQuanFallaElClient() throws Exception {
        ClientDouble client = new ClientDouble();
        client.setExcepcio(new RuntimeException("boom"));

        Dir3CaibPlugin plugin = new TestableDir3CaibPlugin(client.getClient(),
            normalitzarEndpoint(ENDPOINT), USUARI, CONTRASENYA);

        try {
            plugin.sincronitzar("U0001", null, null);
            fail("S'esperava una I18NException");
        } catch (I18NException e) {
            assertTrue(e.getCause() instanceof RuntimeException);
            assertEquals("boom", e.getCause().getMessage());
        }
    }

    private static final class TestableDir3CaibPlugin extends Dir3CaibPlugin {
        private final Dir3CaibObtenerUnidadesWs client;
        private final String endpoint;
        private final String username;
        private final String password;
        private boolean crearClientCridat;

        private TestableDir3CaibPlugin(Dir3CaibObtenerUnidadesWs client, String endpoint, String username,
                String password) {
            this.client = client;
            this.endpoint = endpoint;
            this.username = username;
            this.password = password;
        }

        @Override
        protected Dir3CaibObtenerUnidadesWs crearClientDir3Caib() {
            crearClientCridat = true;
            return client;
        }

        @Override
        protected String obtenirEndpointDir3Caib() {
            return endpoint;
        }

        @Override
        protected String obtenirUsuariDir3Caib() {
            return username;
        }

        @Override
        protected String obtenirContrasenyaDir3Caib() {
            return password;
        }

        private boolean isCrearClientCridat() {
            return crearClientCridat;
        }
    }

    private static final class ClientDouble implements InvocationHandler {
        private final Dir3CaibObtenerUnidadesWs client;
        private final Map<String, Object> requestContext = new HashMap<String, Object>();
        private boolean obtenirArbreInvocat;
        private String codiRebut;
        private Timestamp dataActualitzacioRebuda;
        private Timestamp dataSincronitzacioRebuda;
        private java.util.List<UnidadWs> resposta = Collections.emptyList();
        private RuntimeException excepcio;

        private ClientDouble() {
            this.client = (Dir3CaibObtenerUnidadesWs) Proxy.newProxyInstance(
                    Dir3CaibObtenerUnidadesWs.class.getClassLoader(),
                    new Class<?>[] { Dir3CaibObtenerUnidadesWs.class, BindingProvider.class }, this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            String methodName = method.getName();

            if ("obtenerArbolUnidadesV2".equals(methodName)) {
                obtenirArbreInvocat = true;
                codiRebut = (String) args[0];
                dataActualitzacioRebuda = (Timestamp) args[1];
                dataSincronitzacioRebuda = (Timestamp) args[2];
                if (excepcio != null) {
                    throw excepcio;
                }
                return resposta;
            }

            if ("getRequestContext".equals(methodName)) {
                return requestContext;
            }

            if ("getResponseContext".equals(methodName)) {
                return new HashMap<String, Object>();
            }

            if ("getBinding".equals(methodName) || "getEndpointReference".equals(methodName)) {
                return null;
            }

            if ("equals".equals(methodName)) {
                return args != null && args.length == 1 && proxy == args[0];
            }

            if ("hashCode".equals(methodName)) {
                return System.identityHashCode(proxy);
            }

            if ("toString".equals(methodName)) {
                return "Dir3CaibClientDouble";
            }

            return null;
        }

        private Dir3CaibObtenerUnidadesWs getClient() {
            return client;
        }

        private Map<String, Object> getRequestContext() {
            return requestContext;
        }

        private boolean isObtenirArbreInvocat() {
            return obtenirArbreInvocat;
        }

        private String getCodiRebut() {
            return codiRebut;
        }

        private Timestamp getDataActualitzacioRebuda() {
            return dataActualitzacioRebuda;
        }

        private Timestamp getDataSincronitzacioRebuda() {
            return dataSincronitzacioRebuda;
        }

        private void setResposta(java.util.List<UnidadWs> resposta) {
            this.resposta = resposta;
        }

        private void setExcepcio(RuntimeException excepcio) {
            this.excepcio = excepcio;
        }
    }

    private static Properties carregarPropertiesTest() {
        try (InputStream input = Dir3CaibPluginTest.class.getClassLoader().getResourceAsStream(TEST_PROPERTIES_FILE)) {
            if (input == null) {
                throw new IllegalStateException("No s'ha trobat el fitxer de test: " + TEST_PROPERTIES_FILE);
            }

            Properties properties = new Properties();
            properties.load(input);
            return properties;

        } catch (IOException e) {
            throw new IllegalStateException("Error carregant el fitxer de test: " + TEST_PROPERTIES_FILE, e);
        }
    }

    private static String propietatObligatoria(String key) {
        String value = TEST_PROPERTIES.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("No s'ha definit la propietat obligatoria: " + key);
        }
        return value.trim();
    }

    private static String normalitzarEndpoint(String endpoint) {
        String endpointTrim = endpoint.trim();
        int wsdlIndex = endpointTrim.toLowerCase().indexOf("?wsdl");
        if (wsdlIndex >= 0) {
            return endpointTrim.substring(0, wsdlIndex);
        }
        return endpointTrim;
    }
}

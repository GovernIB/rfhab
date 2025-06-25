package es.caib.rfhab.pluginsib.arxiu;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.pluginsib.arxiu.api.DocumentContingut;

@RunWith(Parameterized.class)
public class ArxiuPluginTest {
    //No emplearem org.jboss.logging.Logger peruqè el plugin està emprant aquest d'aquí
    private final static Logger log = LoggerFactory.getLogger(ArxiuPluginTest.class);

    @Parameterized.Parameter(0)
    public String uuidDocument;

    @Parameterized.Parameter(1)
    public String uuidExpedient;

    private static ArxiuPlugin pluginArxiu = null;
    private static String documentImprimibleDownloadDirectory = null;
    public static final String RFHAB_PROPERTY_BASE = "es.caib.rfhab.pluginsib.arxiu.";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        // uuid
        return Arrays.asList(new Object[][] {
                {
                        "7b27c48b-bce5-4a38-96a1-5ebe4a3a8f47",
                        "cdf73a15-b041-427d-8de5-012b0b91d2fa"
                }
        });
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        Properties configArxiuPlugin = new Properties();
        configArxiuPlugin.load(new FileInputStream("arxiuplugin.properties"));

        pluginArxiu = new ArxiuPlugin(configArxiuPlugin);
        documentImprimibleDownloadDirectory = getDocumentImprimibleDownloadDirectory(configArxiuPlugin);
    }

    @Before
    public void setUp() throws IOException {
    }

    @Test
    public void testDocumentImprimible() throws IOException {
        log.info("testDocumentImprimible::" + uuidDocument);
        System.out.println("testDocumentImprimible::" + uuidDocument);
        File filesPath = new File(documentImprimibleDownloadDirectory, "arxiu");
        filesPath.mkdirs();

        DocumentContingut document = pluginArxiu.documentImprimible(uuidDocument);
        assertNotNull("El document imprimible no pot ser null", document);
        log.info("testDocumentImprimible::obtenció del document imprimible correcta");
        System.out.println("testDocumentImprimible::obtenció del document imprimible correcta");

        File imprimibleFile = new File(filesPath, uuidDocument + "_arxiu." + document.getArxiuNom());

        log.info("testDocumentImprimible::guardant el document imprimible a: " + imprimibleFile.getAbsolutePath());
        System.out.println("testDocumentImprimible::guardant el document imprimible a: " + imprimibleFile.getAbsolutePath());

        FileOutputStream fos = new FileOutputStream(imprimibleFile);
        fos.write(document.getContingut());
        fos.flush();
        fos.close();
    }

    @Test
    public void testTancarExpedient() throws IOException, I18NException {
        log.info("testTancarExpedient::tancant expedient amb id: " + uuidExpedient);
        System.out.println("testTancarExpedient::tancant expedient amb id: " + uuidExpedient);
        String identificadorExpedient = pluginArxiu.tancarExpedientPerId(uuidExpedient);
        log.info("testTancarExpedient::tancat expedient amb id (si és buit, vol dir que no s'ha tancat res): " + identificadorExpedient);
        System.out.println("testTancarExpedient::tancat expedient amb id (si és buit, vol dir que no s'ha tancat res): " + identificadorExpedient);
    }

    public static String getDocumentImprimibleDownloadDirectory(Properties properties) {
        return properties.getProperty(RFHAB_PROPERTY_BASE + "documentImprimible.downloadDirectory");
    }
}

package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.Arrays;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CarregadorMassiuFhIllocsTest {

    protected final static Logger log = Logger.getLogger(CarregadorMassiuFhIllocsTest.class);
    @Parameter(0)
    public String mappingFilePath;

    @Parameter(1)
    public String odsFilePath;

    private static CarregadorMassiuFhIllocsLogicaService carregador = null;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "src/main/resources/ods-mapping.properties", "testfiles/20251002_RFH_Normalitzat_1fila.ods" },
        });
    }

    @Before
    public void setUp() throws Exception {
        Properties configCarregadorMassiu = new Properties();
        configCarregadorMassiu.load(new FileInputStream("carregadormassiu.properties"));
        carregador = CarregadorMassiuFhIllocsLogicaEJB.CrearCarregadorMassiuFhIllocsLogicaEJBambEjbsPerTests(
                odsFilePath, mappingFilePath, configCarregadorMassiu);
    }

    @Test
    public void testCarregaFh() throws Exception {
        // Només comprovem que no llença excepcions i processa DTOs
        carregador.carregaFh();
        // TODO: afegir asserts sobre l'estat intern o mocks
        assertNotNull(carregador);
        assertTrue(carregador.getOdsFilePath().endsWith(".ods"));
        assertTrue(carregador.getApiUrl().startsWith("http"));
        log.info("carregaFh executat correctament per: " + odsFilePath);
    }
}

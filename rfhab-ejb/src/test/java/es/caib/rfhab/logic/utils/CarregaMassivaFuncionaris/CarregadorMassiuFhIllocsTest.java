package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;

import java.util.Arrays;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import es.caib.rfhab.logic.EntitatManagerUtil;
import es.caib.rfhab.model.RFHabDaoManager;
import es.caib.rfhab.model.dao.IFuncionariManager;
import es.caib.rfhab.model.dao.IUnitatManager;

@RunWith(Parameterized.class)
public class CarregadorMassiuFhIllocsTest {

    protected final static Logger log = Logger.getLogger(CarregadorMassiuFhIllocsTest.class);

    private static EntityManager _em = null;
    protected static IFuncionariManager funcionariMan = null;
    protected static IUnitatManager unitatMan = null;
    private static Properties configCarregadorMassiu = null;
    private static CarregadorMassiuFhIllocsLogicaService carregador = null;

    @Parameter(0)
    public String mappingFilePath;

    @Parameter(1)
    public String odsFilePath;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "src/main/resources/ods-mapping.properties", "testfiles/20251002_RFH_Normalitzat_1fila.ods" },
        });
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        _em = EntitatManagerUtil.initDB();
        unitatMan = RFHabDaoManager.getDaoManagers().getUnitatManager();
        funcionariMan = RFHabDaoManager.getDaoManagers().getFuncionariManager();

        configCarregadorMassiu = new Properties();
        configCarregadorMassiu.load(new FileInputStream("carregadormassiu.properties"));
    }

    @Before
    public void setUp() throws Exception {
        carregador = CarregadorMassiuFhIllocsLogicaEJB.CrearCarregadorMassiuFhIllocsLogicaEJBambEjbsPerTests(
                odsFilePath, mappingFilePath, configCarregadorMassiu, funcionariMan, unitatMan);
    }

    @Test
    public void testCarregaFh() throws Exception {
        assertNotNull(carregador);
        assertTrue(carregador.getOdsFilePath().endsWith(".ods"));
        assertTrue(carregador.getApiUrl().startsWith("http"));

        // Només comprovem que no llença excepcions i processa DTOs
        List<String> errorsCarregant = carregador.carregaFh();
        // mostra el llistat d'errors
        log.info("S'han trobat " + errorsCarregant.size() + " Errors en carregar FH"
                + (errorsCarregant.size() > 0 ? ": " + errorsCarregant : "."));
        
        List<String> errorsAmbPrefix = errorsCarregant.stream()
            .map(e -> "##ERROR##" + e)
            .collect(java.util.stream.Collectors.toList());

        java.nio.file.Files.write(
            java.nio.file.Paths.get("errorsCarregant.txt"),
            errorsAmbPrefix,
            java.nio.charset.StandardCharsets.UTF_8,
            java.nio.file.StandardOpenOption.CREATE,
            java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
        );
        assertTrue(errorsCarregant.isEmpty());
        // TODO: afegir asserts sobre l'estat intern o mocks
        log.info("carregaFh executat correctament per: " + odsFilePath);
    }
}

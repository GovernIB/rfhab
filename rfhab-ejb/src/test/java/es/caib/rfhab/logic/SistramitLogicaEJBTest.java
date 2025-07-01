package es.caib.rfhab.logic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import es.caib.rfhab.logic.utils.TicketAccesDto.RpersonaInfo;
import es.caib.rfhab.model.entity.Funcionari;
import es.caib.rfhab.persistence.FuncionariJPA;

@RunWith(Parameterized.class)
public class SistramitLogicaEJBTest {
    protected final static Logger log = Logger.getLogger(SistramitLogicaEJBTest.class);

    @Parameter(0)
    public String codiDir3Fh;

    @Parameter(1)
    public String tramitLlengua;

    private static SistramitLogicaService sistramitLogicaEjb = null;

    @BeforeClass
    public static void setUpClass() throws IOException, I18NException {
        Properties configSistramit = new Properties();
        configSistramit.load(new FileInputStream("sistramit.properties"));
        sistramitLogicaEjb = new SistramitLogicaEJB(configSistramit);
    }

    @Before
    public void setUp() {
    }

    @Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][] {
                { "A04003003", "es" },
                { "A04003003", "ca" }
        });
    }

    @Test
    public void testGetTicketAccesoFh() throws Exception {
        log.info("Testing getTicketAccesoFh with properties: " + codiDir3Fh + ", " + tramitLlengua);
        String urlTramit = null;
        try {
            Funcionari funcionari = new FuncionariJPA();
            funcionari.setFuncionariID(1005);
            funcionari.setCorreu("blabla@fundaciobit.org");
            funcionari.setDataBaixa(null);
            funcionari.setEntitatID(1000);
            funcionari.setIdentificador("44444444A");
            // funcionari.setIdentificador("88855522W");
            funcionari.setLlinatge1("LlinatgeFuncionari1");
            funcionari.setLlinatge2("LlinatgeFuncionari2à");
            funcionari.setNom("Joanet");
            funcionari.setNumero("FH_0000004");
            funcionari.setObservacions(null);
            funcionari.setTipusIdentificador(1);
            funcionari.setUsuari("jFuncionari");

            RpersonaInfo interessat = new RpersonaInfo("Botó", "Jaume", "99999972C", "Mateu");
            RpersonaInfo representant = null;

            urlTramit = sistramitLogicaEjb.getTicketAccesoFh(funcionari, codiDir3Fh, interessat, representant, "3860378", tramitLlengua, "", false, "CAIB.TESTS_OLD.TEST-FIRMA", 5);
        } catch (Exception e) {
            log.error("Error retrieving ticket access. Message: " + e.getMessage());
            log.error("Error retrieving ticket access. LocalizedMessage: " + e.getLocalizedMessage());
            throw e;
        }
        assertNotNull(
                "sistramitLogicaServiceEJB.getTicketAccesoFh ha de tornar una cadena de text que contengui el ticket.",
                urlTramit);
        log.info("Ticket access URL: " + urlTramit);
    }
}

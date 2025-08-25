package es.caib.rfhab.logic;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import es.caib.rfhab.commons.utils.OdtToPdfService;
import es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits.PlantillaOdtModelConsentiment;
import es.caib.rfhab.logic.utils.GeneracioModelConsentimentTramits.TramitConsentimentDAO;

@RunWith(Parameterized.class)
public class OdtToPdfServiceTest {
    private static final String PLANTILLA_PROVES_CAST_ODT = "Plantilla_proves_cast.odt";

    private static final String PLANTILLA_PROVES_CAT_ODT = "Plantilla_proves_cat.odt";

    private static final String PLANTILLA_BASICA = "Plantilla_basica.odt";

    protected final static Logger log = Logger.getLogger(OdtToPdfServiceTest.class);

    @Parameter(0)
    public Map<String, Object> freemarkerDadesMap;

    @Parameter(1)
    public String nomFitxerPlantilla;

    @Parameter(2)
    public String nomFitxerSortida;

    @BeforeClass
    public static void setUpClass() throws IOException, I18NException {
    }

    @Before
    public void setUp() {
    }

    @Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][] {
                { PlantillaOdtModelConsentiment.buildFreemarkerContext(new TramitConsentimentDAO("Joanot", "Colom",
                        "No en tenien", "4561123111A", null, null, null, null, "Llorenç", "Cartutxo", "", "FH77766655",
                        "Procediment BLABLALBA", "45243343", "tramit bliblibli", "987SDFJ", new java.util.Date())),
                        PLANTILLA_BASICA, PLANTILLA_BASICA + "_1.pdf" },
                { PlantillaOdtModelConsentiment.buildFreemarkerContext(
                        new TramitConsentimentDAO("Joanot", "Colom", "No en tenien", "4561123111A", null, null, null,
                                null, "Joan", "Rois", "de Corella", "FH99988800", "Procediment 25 de proves",
                                "8767h9987i", "Tramit blaaaaaaa", "09uj99o", new java.util.Date())),
                        PLANTILLA_PROVES_CAT_ODT, PLANTILLA_PROVES_CAT_ODT + "_1.pdf" },
                { PlantillaOdtModelConsentiment.buildFreemarkerContext(new TramitConsentimentDAO("Joanot", "Martorell",
                        "No en tenien", "9561123111Q", "Martí Joan", "de Galba", null, "55522211W", "Company",
                        "Sense Llinatge2", null, "FLJSDIOFSD8",
                        "Procediment de proves nom una mica llarg per fer proves de tot tipus blalblalbal",
                        "43534 CODIAMBESPAIS 2348o2",
                        "tramit mooooooooooolt llarg també, amb signes de tot tipus ?¡¿!|ººªªçÇ[]+``^*5331/ per cobrir TOTES les proves possibles.,,;-_%&$€€)()",
                        "324LKJ", new java.util.Date())),
                        PLANTILLA_PROVES_CAT_ODT, PLANTILLA_PROVES_CAT_ODT + "_2.pdf" },
                { PlantillaOdtModelConsentiment.buildFreemarkerContext(new TramitConsentimentDAO("Joanot", "Martorell",
                        "No en tenien", "9561123111Q", "Martí Joan", "de Galba", null, "55522211W", "Ñimiñomi",
                        "pirpirpi", "porpopopÇÇ", "777uuuJJN99", "Proc. proves", "TP111222333444", "Tràmit provesssss",
                        "444666777888uuj", new java.util.Date())),
                        PLANTILLA_PROVES_CAST_ODT, PLANTILLA_PROVES_CAST_ODT + "_2.pdf" },
        });
    }

    @Test
    public void testGeneratePdf() throws Exception {
        log.info("Testing generatePdf with properties: " + freemarkerDadesMap + ", " + nomFitxerPlantilla + ", "
                + nomFitxerSortida);
        try {
            File templateFile = new File(nomFitxerPlantilla);

            // Genera PDF en memòria i el desa a disc
            byte[] pdf = OdtToPdfService.generatePdf(freemarkerDadesMap, templateFile);
            try (FileOutputStream fos = new FileOutputStream(nomFitxerSortida)) {
                fos.write(pdf);
            }
            log.info("PDF generat correctament!");
        } catch (Exception e) {
            log.error("Error generant el PDF: " + e.getMessage(), e);
            log.error("Error generant el PDF LocalizedMessage: " + e.getLocalizedMessage(), e);
        }
        File f = new File(nomFitxerSortida);
        assertTrue("testGeneratePdf ha de generar un fitxer anomenat " + nomFitxerSortida,
                f.exists() && !f.isDirectory());
    }
}

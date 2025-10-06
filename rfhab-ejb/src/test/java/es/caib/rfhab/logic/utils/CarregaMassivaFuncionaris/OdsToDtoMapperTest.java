package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class OdsToDtoMapperTest {

    protected final static Logger log = Logger.getLogger(OdsToDtoMapperTest.class);
    @Parameter(0)
    public String mappingFilePath;

    @Parameter(1)
    public String odsFilePath;

    @Parameters
    public static Collection<Object[]> data() {
        // Afegir més casos si tens més fitxers de test
        return Arrays.asList(new Object[][] {
                { "src/main/resources/ods-mapping.properties", "testfiles/20251002_RFH_1fila.ods" },
        });
    }

    private IOdsToDtoMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new OdsToDtoMapper(new File(mappingFilePath));
    }

    @Test
    public void testReadOds() throws Exception {
        List<Map<String, String>> dtos = mapper.readOds(new File(odsFilePath));
        log.info("Fitxer llegit correctament!");
        assertFalse("La llista de DTOs no hauria d'estar buida", dtos.isEmpty());

        // Comprova que cada DTO té alguna de les propietats mapejades
        for (Map<String, String> dto : dtos) {
            log.info(dto);
            assertTrue("El DTO ha de tenir almenys una propietat", dto.size() > 0);
            // Comprova que les claus del DTO són les del mapping
            for (String key : dto.keySet()) {
                assertFalse("La propietat no hauria d'estar buida", key.trim().isEmpty());
            }
        }
    }

    @Test
    public void testReadOdsToDto() throws Exception {
        List<FuncionariOdsDTO> dtos = mapper.readOdsToDto(new File(odsFilePath), FuncionariOdsDTO.class);
        log.info("Fitxer llegit correctament!");
        assertNotNull("La llista de DTOs no hauria de ser null", dtos);
        assertFalse("La llista de DTOs no hauria d'estar buida", dtos.isEmpty());
        for (FuncionariOdsDTO dto : dtos) {
            log.info(dto);
            // Comprova que almenys una propietat del DTO no sigui null ni buida
            boolean hasValue = false;
            for (java.lang.reflect.Field field : FuncionariOdsDTO.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(dto);
                if (value != null && !value.toString().trim().isEmpty()) {
                    hasValue = true;
                    break;
                }
            }
            assertTrue("El DTO ha de tenir almenys una propietat amb valor", hasValue);
        }
    }
}

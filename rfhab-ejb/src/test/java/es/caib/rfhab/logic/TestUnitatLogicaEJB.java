package es.caib.rfhab.logic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import es.caib.rfhab.model.RFHabDaoManager;
import es.caib.rfhab.model.dao.IUnitatManager;
import es.caib.rfhab.model.entity.Unitat;

@RunWith(Parameterized.class)
public class TestUnitatLogicaEJB {

    protected final static Logger log = Logger.getLogger(TestUnitatLogicaEJB.class);

    private static EntityManager _em = null;
    private static IUnitatManager pluginMan = null;

    @Parameter(0)
    public String codiDir3;

    @Parameter(1)
    public Integer versioDir3;

    @BeforeClass
    public static void setUpClass() throws Exception {
        _em = EntitatManagerUtil.initDB();
        pluginMan = RFHabDaoManager.getDaoManagers().getUnitatManager();
    }

    @Before
    public void setUp() {
    }

    @Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][] {
                { "A04047521", 1 },
                { "A04026911", null },
        });
    }

    @Test
    public void testLlistarUnitats() throws I18NException {
        List<Unitat> unitats = pluginMan.select();

        for (Unitat unitat : unitats) {
            log.info("CODI[" + unitat.getCodi() + "]");
        }
    }

    @Test
    public void testFindByCodiDir3() throws I18NException {
        Unitat unitatTrobada = UnitatLogicaEJB.findByCodiDir3(pluginMan, codiDir3, versioDir3 != null ? versioDir3 : 1);
        if (unitatTrobada != null) {
            log.info("UNITAT TROBADA --> CODI[" + unitatTrobada.getCodi() + "]");
        } else {
            log.info("UNITAT NO TROBADA --> codiDir3[" + codiDir3 + "]");
        }
    }

}

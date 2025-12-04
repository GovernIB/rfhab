package es.caib.rfhab.persistence.test;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;

import es.caib.rfhab.model.RFHabDaoManager;
import es.caib.rfhab.model.dao.IHistoricLlocManager;
import es.caib.rfhab.model.fields.HistoricLlocFields;

import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.SelectDistinct;
import org.fundaciobit.genapp.common.query.SelectGroupBy;
import org.fundaciobit.genapp.common.query.SelectMax;

public class TestPersistenceHistoricLloc {

    public static void main(String[] args) {
        try {
            EntityManager em = EntitatManagerUtil.initDB();

            IHistoricLlocManager historicLlocMan = RFHabDaoManager.getDaoManagers().getHistoricLlocManager();

            final Integer PAGINA = 1;
            final Integer NUM_ELEMENTS = 5;
            Where where = null;
            Integer firstResult = (PAGINA - 1) * NUM_ELEMENTS;
            Integer maxResults = NUM_ELEMENTS;
            // OrderBy orderBy = new OrderBy(HistoricLlocFields.DATACREACIO, OrderType.DESC);
            OrderBy orderBy = new OrderBy("max(datacreacio)", OrderType.DESC);
            // historicLlocMan.select(where, firstResult, maxResults, orderBy);

            Select2Columns<Long, Timestamp> s;
            s = new Select2Columns<>(new SelectGroupBy<>(HistoricLlocFields.LLOCID),
                    new SelectMax<>(HistoricLlocFields.DATACREACIO));
            List<Select2Values<Long, Timestamp>> resultat = historicLlocMan.executeQuery(s, where, orderBy);
            // for (Select2Values<Long, Timestamp> select2Values : resultat) {
            // System.out.println(select2Values.getValue1() + " " +
            // select2Values.getValue2());

            // }
            for (int i = firstResult; i < (firstResult + NUM_ELEMENTS); i++) {
                Select2Values<Long, Timestamp> select2Values = resultat.get(i);
                System.out.println(select2Values.getValue1() + " " + select2Values.getValue2());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

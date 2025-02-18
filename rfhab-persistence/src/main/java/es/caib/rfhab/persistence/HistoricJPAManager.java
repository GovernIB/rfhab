
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class HistoricJPAManager
         extends AbstractJPAManager<Historic, Long>
         implements HistoricIJPAManager, IHistoricManager, HistoricFields {



    public static final TableName<Historic> _TABLENAME =  new TableName<Historic>("HistoricJPA");


    @PersistenceContext
    protected EntityManager __em;

    public HistoricJPAManager() {
    }

    protected HistoricJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return HistoricJPA. class;
    }



    public TableName<Historic> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Historic[] listToArray(List<Historic> list)  {
        if(list == null) { return null; };
        return list.toArray(new Historic[list.size()]);
    };

    public Historic create( long _funcionariID_, java.lang.String _numeroCai_, java.lang.String _observacions_, java.sql.Timestamp _dataCreacio_, java.lang.Long _usuariID_) throws I18NException {
        HistoricJPA __bean =  new HistoricJPA(_funcionariID_,_numeroCai_,_observacions_,_dataCreacio_,_usuariID_);
        return create(__bean);
    }



 public void delete(long _historicID_) {
   delete(findByPrimaryKey(_historicID_));
 }




    public Historic findByPrimaryKey(long _historicID_) {
        return __em.find(HistoricJPA.class, _historicID_);  
    }
    @Override
    protected Historic getJPAInstance(Historic __bean) {
        return convertToJPA(__bean);
    }


    public static HistoricJPA convertToJPA(Historic __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof HistoricJPA) {
        return (HistoricJPA)__bean;
      }
      
      return HistoricJPA.toJPA(__bean);
    }


}
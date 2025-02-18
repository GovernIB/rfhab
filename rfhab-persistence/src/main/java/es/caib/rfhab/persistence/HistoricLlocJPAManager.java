
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class HistoricLlocJPAManager
         extends AbstractJPAManager<HistoricLloc, Long>
         implements HistoricLlocIJPAManager, IHistoricLlocManager, HistoricLlocFields {



    public static final TableName<HistoricLloc> _TABLENAME =  new TableName<HistoricLloc>("HistoricLlocJPA");


    @PersistenceContext
    protected EntityManager __em;

    public HistoricLlocJPAManager() {
    }

    protected HistoricLlocJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return HistoricLlocJPA. class;
    }



    public TableName<HistoricLloc> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public HistoricLloc[] listToArray(List<HistoricLloc> list)  {
        if(list == null) { return null; };
        return list.toArray(new HistoricLloc[list.size()]);
    };

    public HistoricLloc create( long _llocID_, java.lang.String _numeroCai_, java.lang.String _observacions_, java.sql.Timestamp _dataCreacio_, java.lang.Long _usuariID_) throws I18NException {
        HistoricLlocJPA __bean =  new HistoricLlocJPA(_llocID_,_numeroCai_,_observacions_,_dataCreacio_,_usuariID_);
        return create(__bean);
    }



 public void delete(long _historicllocID_) {
   delete(findByPrimaryKey(_historicllocID_));
 }




    public HistoricLloc findByPrimaryKey(long _historicllocID_) {
        return __em.find(HistoricLlocJPA.class, _historicllocID_);  
    }
    @Override
    protected HistoricLloc getJPAInstance(HistoricLloc __bean) {
        return convertToJPA(__bean);
    }


    public static HistoricLlocJPA convertToJPA(HistoricLloc __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof HistoricLlocJPA) {
        return (HistoricLlocJPA)__bean;
      }
      
      return HistoricLlocJPA.toJPA(__bean);
    }


}
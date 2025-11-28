
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class LlocHabilitacioJPAManager
         extends AbstractJPAManager<LlocHabilitacio, Long>
         implements LlocHabilitacioIJPAManager, ILlocHabilitacioManager, LlocHabilitacioFields {



    public static final TableName<LlocHabilitacio> _TABLENAME =  new TableName<LlocHabilitacio>("LlocHabilitacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public LlocHabilitacioJPAManager() {
    }

    protected LlocHabilitacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return LlocHabilitacioJPA. class;
    }



    public TableName<LlocHabilitacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public LlocHabilitacio[] listToArray(List<LlocHabilitacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new LlocHabilitacio[list.size()]);
    };

    public LlocHabilitacio create( java.sql.Timestamp _dataCreacio_, long _llocID_, long _habilitacioId_) throws I18NException {
        LlocHabilitacioJPA __bean =  new LlocHabilitacioJPA(_dataCreacio_,_llocID_,_habilitacioId_);
        return create(__bean);
    }



 public void delete(long _llocHabilitacioID_) {
   delete(findByPrimaryKey(_llocHabilitacioID_));
 }




    public LlocHabilitacio findByPrimaryKey(long _llocHabilitacioID_) {
        return __em.find(LlocHabilitacioJPA.class, _llocHabilitacioID_);  
    }
    @Override
    protected LlocHabilitacio getJPAInstance(LlocHabilitacio __bean) {
        return convertToJPA(__bean);
    }


    public static LlocHabilitacioJPA convertToJPA(LlocHabilitacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof LlocHabilitacioJPA) {
        return (LlocHabilitacioJPA)__bean;
      }
      
      return LlocHabilitacioJPA.toJPA(__bean);
    }


}
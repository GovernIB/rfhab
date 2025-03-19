
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class LlocRolJPAManager
         extends AbstractJPAManager<LlocRol, Long>
         implements LlocRolIJPAManager, ILlocRolManager, LlocRolFields {



    public static final TableName<LlocRol> _TABLENAME =  new TableName<LlocRol>("LlocRolJPA");


    @PersistenceContext
    protected EntityManager __em;

    public LlocRolJPAManager() {
    }

    protected LlocRolJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return LlocRolJPA. class;
    }



    public TableName<LlocRol> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public LlocRol[] listToArray(List<LlocRol> list)  {
        if(list == null) { return null; };
        return list.toArray(new LlocRol[list.size()]);
    };

    public LlocRol create( java.sql.Timestamp _dataCreacio_, long _llocID_, long _rolID_) throws I18NException {
        LlocRolJPA __bean =  new LlocRolJPA(_dataCreacio_,_llocID_,_rolID_);
        return create(__bean);
    }



 public void delete(long _llocRolID_) {
   delete(findByPrimaryKey(_llocRolID_));
 }




    public LlocRol findByPrimaryKey(long _llocRolID_) {
        return __em.find(LlocRolJPA.class, _llocRolID_);  
    }
    @Override
    protected LlocRol getJPAInstance(LlocRol __bean) {
        return convertToJPA(__bean);
    }


    public static LlocRolJPA convertToJPA(LlocRol __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof LlocRolJPA) {
        return (LlocRolJPA)__bean;
      }
      
      return LlocRolJPA.toJPA(__bean);
    }


}
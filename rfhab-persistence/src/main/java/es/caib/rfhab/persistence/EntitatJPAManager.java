
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EntitatJPAManager
         extends AbstractJPAManager<Entitat, Long>
         implements EntitatIJPAManager, IEntitatManager, EntitatFields {



    public static final TableName<Entitat> _TABLENAME =  new TableName<Entitat>("EntitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EntitatJPAManager() {
    }

    protected EntitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EntitatJPA. class;
    }



    public TableName<Entitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Entitat[] listToArray(List<Entitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new Entitat[list.size()]);
    };

    public Entitat create( java.lang.String _nom_, boolean _actiu_, java.lang.Long _unitatID_, java.sql.Timestamp _dataBaixa_) throws I18NException {
        EntitatJPA __bean =  new EntitatJPA(_nom_,_actiu_,_unitatID_,_dataBaixa_);
        return create(__bean);
    }



 public void delete(long _entitatID_) {
   delete(findByPrimaryKey(_entitatID_));
 }




    public Entitat findByPrimaryKey(long _entitatID_) {
        return __em.find(EntitatJPA.class, _entitatID_);  
    }
    @Override
    protected Entitat getJPAInstance(Entitat __bean) {
        return convertToJPA(__bean);
    }


    public static EntitatJPA convertToJPA(Entitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EntitatJPA) {
        return (EntitatJPA)__bean;
      }
      
      return EntitatJPA.toJPA(__bean);
    }


}
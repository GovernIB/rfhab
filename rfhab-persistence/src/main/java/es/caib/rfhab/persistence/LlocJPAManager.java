
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class LlocJPAManager
         extends AbstractJPAManager<Lloc, Long>
         implements LlocIJPAManager, ILlocManager, LlocFields {



    public static final TableName<Lloc> _TABLENAME =  new TableName<Lloc>("LlocJPA");


    @PersistenceContext
    protected EntityManager __em;

    public LlocJPAManager() {
    }

    protected LlocJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return LlocJPA. class;
    }



    public TableName<Lloc> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Lloc[] listToArray(List<Lloc> list)  {
        if(list == null) { return null; };
        return list.toArray(new Lloc[list.size()]);
    };

    public Lloc create( java.lang.String _codiLloc_, java.lang.String _nom_, int _personalOamr_, long _entitatID_, java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataBaixa_, java.lang.String _observacions_, long _unitatID_) throws I18NException {
        LlocJPA __bean =  new LlocJPA(_codiLloc_,_nom_,_personalOamr_,_entitatID_,_dataCreacio_,_dataBaixa_,_observacions_,_unitatID_);
        return create(__bean);
    }



 public void delete(long _llocID_) {
   delete(findByPrimaryKey(_llocID_));
 }




    public Lloc findByPrimaryKey(long _llocID_) {
        return __em.find(LlocJPA.class, _llocID_);  
    }
    @Override
    protected Lloc getJPAInstance(Lloc __bean) {
        return convertToJPA(__bean);
    }


    public static LlocJPA convertToJPA(Lloc __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof LlocJPA) {
        return (LlocJPA)__bean;
      }
      
      return LlocJPA.toJPA(__bean);
    }


}
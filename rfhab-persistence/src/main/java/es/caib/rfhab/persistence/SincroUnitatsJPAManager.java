
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class SincroUnitatsJPAManager
         extends AbstractJPAManager<SincroUnitats, Long>
         implements SincroUnitatsIJPAManager, ISincroUnitatsManager, SincroUnitatsFields {



    public static final TableName<SincroUnitats> _TABLENAME =  new TableName<SincroUnitats>("SincroUnitatsJPA");


    @PersistenceContext
    protected EntityManager __em;

    public SincroUnitatsJPAManager() {
    }

    protected SincroUnitatsJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return SincroUnitatsJPA. class;
    }



    public TableName<SincroUnitats> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public SincroUnitats[] listToArray(List<SincroUnitats> list)  {
        if(list == null) { return null; };
        return list.toArray(new SincroUnitats[list.size()]);
    };

    public SincroUnitats create( java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataDarreraSincro_, java.sql.Timestamp _dataPrimeraSincro_, java.lang.String _codiEntitat_, java.lang.String _observacions_, java.lang.Long _usuariId_) throws I18NException {
        SincroUnitatsJPA __bean =  new SincroUnitatsJPA(_dataCreacio_,_dataDarreraSincro_,_dataPrimeraSincro_,_codiEntitat_,_observacions_,_usuariId_);
        return create(__bean);
    }



 public void delete(long _sincrounitatsId_) {
   delete(findByPrimaryKey(_sincrounitatsId_));
 }




    public SincroUnitats findByPrimaryKey(long _sincrounitatsId_) {
        return __em.find(SincroUnitatsJPA.class, _sincrounitatsId_);  
    }
    @Override
    protected SincroUnitats getJPAInstance(SincroUnitats __bean) {
        return convertToJPA(__bean);
    }


    public static SincroUnitatsJPA convertToJPA(SincroUnitats __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof SincroUnitatsJPA) {
        return (SincroUnitatsJPA)__bean;
      }
      
      return SincroUnitatsJPA.toJPA(__bean);
    }


}

package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class HabilitacioJPAManager
         extends AbstractJPAManager<Habilitacio, Long>
         implements HabilitacioIJPAManager, IHabilitacioManager, HabilitacioFields {



    public static final TableName<Habilitacio> _TABLENAME =  new TableName<Habilitacio>("HabilitacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public HabilitacioJPAManager() {
    }

    protected HabilitacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return HabilitacioJPA. class;
    }



    public TableName<Habilitacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Habilitacio[] listToArray(List<Habilitacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Habilitacio[list.size()]);
    };

    public Habilitacio create( java.lang.Long _nomID_, java.lang.String _codi_, java.sql.Timestamp _dataCreacio_, java.lang.Long _entitatID_) throws I18NException {
        HabilitacioJPA __bean =  new HabilitacioJPA(_nomID_,_codi_,_dataCreacio_,_entitatID_);
        return create(__bean);
    }



 public void delete(long _habilitacioID_) {
   delete(findByPrimaryKey(_habilitacioID_));
 }




    public Habilitacio findByPrimaryKey(long _habilitacioID_) {
        return __em.find(HabilitacioJPA.class, _habilitacioID_);  
    }
    @Override
    protected Habilitacio getJPAInstance(Habilitacio __bean) {
        return convertToJPA(__bean);
    }


    public static HabilitacioJPA convertToJPA(Habilitacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof HabilitacioJPA) {
        return (HabilitacioJPA)__bean;
      }
      
      return HabilitacioJPA.toJPA(__bean);
    }

  @Override
  public Habilitacio create(Habilitacio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Habilitacio update(Habilitacio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Habilitacio transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == null) {
        if (transientInstance instanceof HabilitacioJPA) {
          HabilitacioJPA _jpa = (HabilitacioJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getNom();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setNomID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}
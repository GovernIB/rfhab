
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class RolJPAManager
         extends AbstractJPAManager<Rol, Long>
         implements RolIJPAManager, IRolManager, RolFields {



    public static final TableName<Rol> _TABLENAME =  new TableName<Rol>("RolJPA");


    @PersistenceContext
    protected EntityManager __em;

    public RolJPAManager() {
    }

    protected RolJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return RolJPA. class;
    }



    public TableName<Rol> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Rol[] listToArray(List<Rol> list)  {
        if(list == null) { return null; };
        return list.toArray(new Rol[list.size()]);
    };

    public Rol create( java.lang.Long _nomID_, java.lang.String _codi_, java.sql.Timestamp _dataCreacio_, java.lang.Long _entitatID_) throws I18NException {
        RolJPA __bean =  new RolJPA(_nomID_,_codi_,_dataCreacio_,_entitatID_);
        return create(__bean);
    }



 public void delete(long _rolID_) {
   delete(findByPrimaryKey(_rolID_));
 }




    public Rol findByPrimaryKey(long _rolID_) {
        return __em.find(RolJPA.class, _rolID_);  
    }
    @Override
    protected Rol getJPAInstance(Rol __bean) {
        return convertToJPA(__bean);
    }


    public static RolJPA convertToJPA(Rol __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof RolJPA) {
        return (RolJPA)__bean;
      }
      
      return RolJPA.toJPA(__bean);
    }

  @Override
  public Rol create(Rol transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Rol update(Rol transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Rol transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getNomID() == null) {
        if (transientInstance instanceof RolJPA) {
          RolJPA _jpa = (RolJPA)transientInstance;
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
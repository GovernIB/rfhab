
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class AutoritzacioJPAManager
         extends AbstractJPAManager<Autoritzacio, Long>
         implements AutoritzacioIJPAManager, IAutoritzacioManager, AutoritzacioFields {



    public static final TableName<Autoritzacio> _TABLENAME =  new TableName<Autoritzacio>("AutoritzacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public AutoritzacioJPAManager() {
    }

    protected AutoritzacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return AutoritzacioJPA. class;
    }



    public TableName<Autoritzacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Autoritzacio[] listToArray(List<Autoritzacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Autoritzacio[list.size()]);
    };

    public Autoritzacio create( long _llocID_, java.lang.String _codiSia_, java.lang.String _procediment_, java.lang.String _cai_, java.sql.Date _dataInici_, java.sql.Date _dataFi_, java.sql.Timestamp _dataCreacio_, java.lang.String _observacions_, java.lang.Long _usuariID_, java.lang.Long _funcionariID_) throws I18NException {
        AutoritzacioJPA __bean =  new AutoritzacioJPA(_llocID_,_codiSia_,_procediment_,_cai_,_dataInici_,_dataFi_,_dataCreacio_,_observacions_,_usuariID_,_funcionariID_);
        return create(__bean);
    }



 public void delete(long _autoritzacioID_) {
   delete(findByPrimaryKey(_autoritzacioID_));
 }




    public Autoritzacio findByPrimaryKey(long _autoritzacioID_) {
        return __em.find(AutoritzacioJPA.class, _autoritzacioID_);  
    }
    @Override
    protected Autoritzacio getJPAInstance(Autoritzacio __bean) {
        return convertToJPA(__bean);
    }


    public static AutoritzacioJPA convertToJPA(Autoritzacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof AutoritzacioJPA) {
        return (AutoritzacioJPA)__bean;
      }
      
      return AutoritzacioJPA.toJPA(__bean);
    }


}
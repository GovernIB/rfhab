
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class UnitatJPAManager
         extends AbstractJPAManager<Unitat, Long>
         implements UnitatIJPAManager, IUnitatManager, UnitatFields {



    public static final TableName<Unitat> _TABLENAME =  new TableName<Unitat>("UnitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public UnitatJPAManager() {
    }

    protected UnitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return UnitatJPA. class;
    }



    public TableName<Unitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Unitat[] listToArray(List<Unitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new Unitat[list.size()]);
    };

    public Unitat create( java.lang.String _codi_, int _versio_, java.lang.String _denominacio_, java.lang.String _cooficial_, java.lang.String _arrel_, java.lang.Integer _arrelVersio_, java.lang.String _superior_, java.lang.Integer _superiorVersio_, java.lang.String _estat_) throws I18NException {
        UnitatJPA __bean =  new UnitatJPA(_codi_,_versio_,_denominacio_,_cooficial_,_arrel_,_arrelVersio_,_superior_,_superiorVersio_,_estat_);
        return create(__bean);
    }



 public void delete(long _unitatID_) {
   delete(findByPrimaryKey(_unitatID_));
 }




    public Unitat findByPrimaryKey(long _unitatID_) {
        return __em.find(UnitatJPA.class, _unitatID_);  
    }
    @Override
    protected Unitat getJPAInstance(Unitat __bean) {
        return convertToJPA(__bean);
    }


    public static UnitatJPA convertToJPA(Unitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof UnitatJPA) {
        return (UnitatJPA)__bean;
      }
      
      return UnitatJPA.toJPA(__bean);
    }


}
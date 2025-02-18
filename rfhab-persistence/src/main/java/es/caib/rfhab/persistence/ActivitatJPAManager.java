
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class ActivitatJPAManager
         extends AbstractJPAManager<Activitat, Long>
         implements ActivitatIJPAManager, IActivitatManager, ActivitatFields {



    public static final TableName<Activitat> _TABLENAME =  new TableName<Activitat>("ActivitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public ActivitatJPAManager() {
    }

    protected ActivitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return ActivitatJPA. class;
    }



    public TableName<Activitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Activitat[] listToArray(List<Activitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new Activitat[list.size()]);
    };

    public Activitat create( long _funcionariID_, int _tipus_, java.lang.String _registre_, java.lang.String _tramit_, java.lang.String _codiSia_, java.lang.Long _autoritzacioID_, java.sql.Timestamp _dataCreacio_, java.lang.String _interessatNom_, java.lang.String _interessatLlinatge1_, java.lang.String _interessatLlinatge2_, int _interessatTipus_, java.lang.String _interessatIdentificacio_, java.lang.String _representantNom_, java.lang.String _representantLlinatge1_, java.lang.String _representantLlinatge2_, java.lang.Integer _representantTipus_, java.lang.String _representantIdentificacio_, java.lang.Integer _tramitVersio_, java.lang.String _arxiuDocumentID_, java.lang.String _arxiuExpedientID_, int _estat_, java.lang.String _url_) throws I18NException {
        ActivitatJPA __bean =  new ActivitatJPA(_funcionariID_,_tipus_,_registre_,_tramit_,_codiSia_,_autoritzacioID_,_dataCreacio_,_interessatNom_,_interessatLlinatge1_,_interessatLlinatge2_,_interessatTipus_,_interessatIdentificacio_,_representantNom_,_representantLlinatge1_,_representantLlinatge2_,_representantTipus_,_representantIdentificacio_,_tramitVersio_,_arxiuDocumentID_,_arxiuExpedientID_,_estat_,_url_);
        return create(__bean);
    }



 public void delete(long _activitatID_) {
   delete(findByPrimaryKey(_activitatID_));
 }




    public Activitat findByPrimaryKey(long _activitatID_) {
        return __em.find(ActivitatJPA.class, _activitatID_);  
    }
    @Override
    protected Activitat getJPAInstance(Activitat __bean) {
        return convertToJPA(__bean);
    }


    public static ActivitatJPA convertToJPA(Activitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof ActivitatJPA) {
        return (ActivitatJPA)__bean;
      }
      
      return ActivitatJPA.toJPA(__bean);
    }


}
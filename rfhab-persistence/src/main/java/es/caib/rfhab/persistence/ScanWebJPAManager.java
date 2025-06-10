
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class ScanWebJPAManager
         extends AbstractJPAManager<ScanWeb, Long>
         implements ScanWebIJPAManager, IScanWebManager, ScanWebFields {



    public static final TableName<ScanWeb> _TABLENAME =  new TableName<ScanWeb>("ScanWebJPA");


    @PersistenceContext
    protected EntityManager __em;

    public ScanWebJPAManager() {
    }

    protected ScanWebJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return ScanWebJPA. class;
    }



    public TableName<ScanWeb> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public ScanWeb[] listToArray(List<ScanWeb> list)  {
        if(list == null) { return null; };
        return list.toArray(new ScanWeb[list.size()]);
    };

    public ScanWeb create( java.lang.String _transactionID_, java.lang.String _transactionWebID_, long _status_, long _fitxerID_, java.lang.String _fileInfo_, java.lang.String _signedFileInfo_, java.lang.String _metadades_, java.lang.String _missatge_, java.lang.Long _usuariID_, java.sql.Timestamp _dataCreacio_, long _entitatID_) throws I18NException {
        ScanWebJPA __bean =  new ScanWebJPA(_transactionID_,_transactionWebID_,_status_,_fitxerID_,_fileInfo_,_signedFileInfo_,_metadades_,_missatge_,_usuariID_,_dataCreacio_,_entitatID_);
        return create(__bean);
    }



 public void delete(long _digitalID_) {
   delete(findByPrimaryKey(_digitalID_));
 }




    public ScanWeb findByPrimaryKey(long _digitalID_) {
        return __em.find(ScanWebJPA.class, _digitalID_);  
    }
    @Override
    protected ScanWeb getJPAInstance(ScanWeb __bean) {
        return convertToJPA(__bean);
    }


    public static ScanWebJPA convertToJPA(ScanWeb __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof ScanWebJPA) {
        return (ScanWebJPA)__bean;
      }
      
      return ScanWebJPA.toJPA(__bean);
    }


}
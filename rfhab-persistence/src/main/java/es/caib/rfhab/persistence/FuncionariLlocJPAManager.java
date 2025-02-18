
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class FuncionariLlocJPAManager
         extends AbstractJPAManager<FuncionariLloc, Long>
         implements FuncionariLlocIJPAManager, IFuncionariLlocManager, FuncionariLlocFields {



    public static final TableName<FuncionariLloc> _TABLENAME =  new TableName<FuncionariLloc>("FuncionariLlocJPA");


    @PersistenceContext
    protected EntityManager __em;

    public FuncionariLlocJPAManager() {
    }

    protected FuncionariLlocJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return FuncionariLlocJPA. class;
    }



    public TableName<FuncionariLloc> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public FuncionariLloc[] listToArray(List<FuncionariLloc> list)  {
        if(list == null) { return null; };
        return list.toArray(new FuncionariLloc[list.size()]);
    };

    public FuncionariLloc create( long _llocID_, long _funcionariID_, java.sql.Date _dataInici_, java.sql.Date _dataFi_, java.sql.Timestamp _dataCreacio_, java.lang.Long _usuariID_) throws I18NException {
        FuncionariLlocJPA __bean =  new FuncionariLlocJPA(_llocID_,_funcionariID_,_dataInici_,_dataFi_,_dataCreacio_,_usuariID_);
        return create(__bean);
    }



 public void delete(long _funcionarillocID_) {
   delete(findByPrimaryKey(_funcionarillocID_));
 }




    public FuncionariLloc findByPrimaryKey(long _funcionarillocID_) {
        return __em.find(FuncionariLlocJPA.class, _funcionarillocID_);  
    }
    @Override
    protected FuncionariLloc getJPAInstance(FuncionariLloc __bean) {
        return convertToJPA(__bean);
    }


    public static FuncionariLlocJPA convertToJPA(FuncionariLloc __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof FuncionariLlocJPA) {
        return (FuncionariLlocJPA)__bean;
      }
      
      return FuncionariLlocJPA.toJPA(__bean);
    }


}
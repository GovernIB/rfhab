
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class FuncionariRolJPAManager
         extends AbstractJPAManager<FuncionariRol, Long>
         implements FuncionariRolIJPAManager, IFuncionariRolManager, FuncionariRolFields {



    public static final TableName<FuncionariRol> _TABLENAME =  new TableName<FuncionariRol>("FuncionariRolJPA");


    @PersistenceContext
    protected EntityManager __em;

    public FuncionariRolJPAManager() {
    }

    protected FuncionariRolJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return FuncionariRolJPA. class;
    }



    public TableName<FuncionariRol> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public FuncionariRol[] listToArray(List<FuncionariRol> list)  {
        if(list == null) { return null; };
        return list.toArray(new FuncionariRol[list.size()]);
    };

    public FuncionariRol create( long _funcionariID_, long _rolID_, java.sql.Timestamp _dataCreacio_) throws I18NException {
        FuncionariRolJPA __bean =  new FuncionariRolJPA(_funcionariID_,_rolID_,_dataCreacio_);
        return create(__bean);
    }



 public void delete(long _funcionariRolID_) {
   delete(findByPrimaryKey(_funcionariRolID_));
 }




    public FuncionariRol findByPrimaryKey(long _funcionariRolID_) {
        return __em.find(FuncionariRolJPA.class, _funcionariRolID_);  
    }
    @Override
    protected FuncionariRol getJPAInstance(FuncionariRol __bean) {
        return convertToJPA(__bean);
    }


    public static FuncionariRolJPA convertToJPA(FuncionariRol __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof FuncionariRolJPA) {
        return (FuncionariRolJPA)__bean;
      }
      
      return FuncionariRolJPA.toJPA(__bean);
    }


}
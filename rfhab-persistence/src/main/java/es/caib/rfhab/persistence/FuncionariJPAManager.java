
package es.caib.rfhab.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.rfhab.model.entity.*;
import es.caib.rfhab.model.fields.*;
import es.caib.rfhab.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class FuncionariJPAManager
         extends AbstractJPAManager<Funcionari, Long>
         implements FuncionariIJPAManager, IFuncionariManager, FuncionariFields {



    public static final TableName<Funcionari> _TABLENAME =  new TableName<Funcionari>("FuncionariJPA");


    @PersistenceContext
    protected EntityManager __em;

    public FuncionariJPAManager() {
    }

    protected FuncionariJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return FuncionariJPA. class;
    }



    public TableName<Funcionari> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Funcionari[] listToArray(List<Funcionari> list)  {
        if(list == null) { return null; };
        return list.toArray(new Funcionari[list.size()]);
    };

    public Funcionari create( int _numero_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, int _tipusIdentificador_, java.lang.String _identificador_, java.lang.String _usuari_, java.lang.String _correu_, java.sql.Timestamp _dataCreacio_, java.lang.String _observacions_, java.sql.Timestamp _dataBaixa_, long _entitatID_) throws I18NException {
        FuncionariJPA __bean =  new FuncionariJPA(_numero_,_nom_,_llinatge1_,_llinatge2_,_tipusIdentificador_,_identificador_,_usuari_,_correu_,_dataCreacio_,_observacions_,_dataBaixa_,_entitatID_);
        return create(__bean);
    }



 public void delete(long _funcionariID_) {
   delete(findByPrimaryKey(_funcionariID_));
 }




    public Funcionari findByPrimaryKey(long _funcionariID_) {
        return __em.find(FuncionariJPA.class, _funcionariID_);  
    }
    @Override
    protected Funcionari getJPAInstance(Funcionari __bean) {
        return convertToJPA(__bean);
    }


    public static FuncionariJPA convertToJPA(Funcionari __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof FuncionariJPA) {
        return (FuncionariJPA)__bean;
      }
      
      return FuncionariJPA.toJPA(__bean);
    }


}
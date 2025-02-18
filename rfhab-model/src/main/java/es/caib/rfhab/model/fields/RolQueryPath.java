
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class RolQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public RolQueryPath() {
  }

  protected RolQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ROLID() {
    return new LongField(getQueryPath(), RolFields.ROLID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), RolFields.NOMID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), RolFields.CODI);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), RolFields.DATACREACIO);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), RolFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (RolFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public FuncionariRolQueryPath FUNCIONARIROLS() {
    return new FuncionariRolQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RolQueryPath.this.getQueryPath() + "funcionariRols" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return RolQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

}

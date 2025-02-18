
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FuncionariRolQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FuncionariRolQueryPath() {
  }

  protected FuncionariRolQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FUNCIONARIROLID() {
    return new LongField(getQueryPath(), FuncionariRolFields.FUNCIONARIROLID);
  }

  public LongField FUNCIONARIID() {
    return new LongField(getQueryPath(), FuncionariRolFields.FUNCIONARIID);
  }

  public LongField ROLID() {
    return new LongField(getQueryPath(), FuncionariRolFields.ROLID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), FuncionariRolFields.DATACREACIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FuncionariRolFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FuncionariQueryPath FUNCIONARI() {
    return new FuncionariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariRolQueryPath.this.getQueryPath() + "funcionari" + ".";
      }
    });
  }

  public RolQueryPath ROL() {
    return new RolQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariRolQueryPath.this.getQueryPath() + "rol" + ".";
      }
    });
  }

}

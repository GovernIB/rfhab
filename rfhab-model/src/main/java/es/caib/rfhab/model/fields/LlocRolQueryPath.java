
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class LlocRolQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public LlocRolQueryPath() {
  }

  protected LlocRolQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField LLOCROLID() {
    return new LongField(getQueryPath(), LlocRolFields.LLOCROLID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), LlocRolFields.DATACREACIO);
  }

  public LongField LLOCID() {
    return new LongField(getQueryPath(), LlocRolFields.LLOCID);
  }

  public LongField ROLID() {
    return new LongField(getQueryPath(), LlocRolFields.ROLID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (LlocRolFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public LlocQueryPath LLOC() {
    return new LlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocRolQueryPath.this.getQueryPath() + "lloc" + ".";
      }
    });
  }

  public RolQueryPath ROL() {
    return new RolQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocRolQueryPath.this.getQueryPath() + "rol" + ".";
      }
    });
  }

}

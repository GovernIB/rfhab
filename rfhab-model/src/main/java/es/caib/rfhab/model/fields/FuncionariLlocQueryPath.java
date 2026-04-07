
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FuncionariLlocQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FuncionariLlocQueryPath() {
  }

  protected FuncionariLlocQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FUNCIONARILLOCID() {
    return new LongField(getQueryPath(), FuncionariLlocFields.FUNCIONARILLOCID);
  }

  public LongField LLOCID() {
    return new LongField(getQueryPath(), FuncionariLlocFields.LLOCID);
  }

  public LongField FUNCIONARIID() {
    return new LongField(getQueryPath(), FuncionariLlocFields.FUNCIONARIID);
  }

  public DateField DATAINICI() {
    return new DateField(getQueryPath(), FuncionariLlocFields.DATAINICI);
  }

  public DateField DATAFI() {
    return new DateField(getQueryPath(), FuncionariLlocFields.DATAFI);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), FuncionariLlocFields.DATACREACIO);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), FuncionariLlocFields.USUARIID);
  }

  public StringField NUMEROCAI() {
    return new StringField(getQueryPath(), FuncionariLlocFields.NUMEROCAI);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FuncionariLlocFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public LlocQueryPath LLOC() {
    return new LlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariLlocQueryPath.this.getQueryPath() + "lloc" + ".";
      }
    });
  }

  public FuncionariQueryPath FUNCIONARI() {
    return new FuncionariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariLlocQueryPath.this.getQueryPath() + "funcionari" + ".";
      }
    });
  }

  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariLlocQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}

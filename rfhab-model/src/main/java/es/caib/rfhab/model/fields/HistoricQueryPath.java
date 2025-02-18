
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class HistoricQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public HistoricQueryPath() {
  }

  protected HistoricQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField HISTORICID() {
    return new LongField(getQueryPath(), HistoricFields.HISTORICID);
  }

  public LongField FUNCIONARIID() {
    return new LongField(getQueryPath(), HistoricFields.FUNCIONARIID);
  }

  public StringField NUMEROCAI() {
    return new StringField(getQueryPath(), HistoricFields.NUMEROCAI);
  }

  public StringField OBSERVACIONS() {
    return new StringField(getQueryPath(), HistoricFields.OBSERVACIONS);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), HistoricFields.DATACREACIO);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), HistoricFields.USUARIID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (HistoricFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FuncionariQueryPath FUNCIONARI() {
    return new FuncionariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return HistoricQueryPath.this.getQueryPath() + "funcionari" + ".";
      }
    });
  }

  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return HistoricQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}

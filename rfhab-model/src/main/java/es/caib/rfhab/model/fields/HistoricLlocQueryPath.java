
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class HistoricLlocQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public HistoricLlocQueryPath() {
  }

  protected HistoricLlocQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField HISTORICLLOCID() {
    return new LongField(getQueryPath(), HistoricLlocFields.HISTORICLLOCID);
  }

  public LongField LLOCID() {
    return new LongField(getQueryPath(), HistoricLlocFields.LLOCID);
  }

  public StringField NUMEROCAI() {
    return new StringField(getQueryPath(), HistoricLlocFields.NUMEROCAI);
  }

  public StringField OBSERVACIONS() {
    return new StringField(getQueryPath(), HistoricLlocFields.OBSERVACIONS);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), HistoricLlocFields.DATACREACIO);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), HistoricLlocFields.USUARIID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (HistoricLlocFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public LlocQueryPath LLOC() {
    return new LlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return HistoricLlocQueryPath.this.getQueryPath() + "lloc" + ".";
      }
    });
  }

  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return HistoricLlocQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}

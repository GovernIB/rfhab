
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class SincroUnitatsQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public SincroUnitatsQueryPath() {
  }

  protected SincroUnitatsQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField SINCROUNITATSID() {
    return new LongField(getQueryPath(), SincroUnitatsFields.SINCROUNITATSID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), SincroUnitatsFields.DATACREACIO);
  }

  public TimestampField DATADARRERASINCRO() {
    return new TimestampField(getQueryPath(), SincroUnitatsFields.DATADARRERASINCRO);
  }

  public TimestampField DATAPRIMERASINCRO() {
    return new TimestampField(getQueryPath(), SincroUnitatsFields.DATAPRIMERASINCRO);
  }

  public StringField CODIENTITAT() {
    return new StringField(getQueryPath(), SincroUnitatsFields.CODIENTITAT);
  }

  public StringField OBSERVACIONS() {
    return new StringField(getQueryPath(), SincroUnitatsFields.OBSERVACIONS);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), SincroUnitatsFields.USUARIID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (SincroUnitatsFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SincroUnitatsQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}

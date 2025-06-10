
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ScanWebQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ScanWebQueryPath() {
  }

  protected ScanWebQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DIGITALID() {
    return new LongField(getQueryPath(), ScanWebFields.DIGITALID);
  }

  public StringField TRANSACTIONID() {
    return new StringField(getQueryPath(), ScanWebFields.TRANSACTIONID);
  }

  public StringField TRANSACTIONWEBID() {
    return new StringField(getQueryPath(), ScanWebFields.TRANSACTIONWEBID);
  }

  public LongField STATUS() {
    return new LongField(getQueryPath(), ScanWebFields.STATUS);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), ScanWebFields.FITXERID);
  }

  public StringField FILEINFO() {
    return new StringField(getQueryPath(), ScanWebFields.FILEINFO);
  }

  public StringField SIGNEDFILEINFO() {
    return new StringField(getQueryPath(), ScanWebFields.SIGNEDFILEINFO);
  }

  public StringField METADADES() {
    return new StringField(getQueryPath(), ScanWebFields.METADADES);
  }

  public StringField MISSATGE() {
    return new StringField(getQueryPath(), ScanWebFields.MISSATGE);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), ScanWebFields.USUARIID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), ScanWebFields.DATACREACIO);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), ScanWebFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ScanWebFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ScanWebQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ScanWebQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}

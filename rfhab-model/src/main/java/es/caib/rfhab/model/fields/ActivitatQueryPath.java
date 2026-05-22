
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ActivitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ActivitatQueryPath() {
  }

  protected ActivitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ACTIVITATID() {
    return new LongField(getQueryPath(), ActivitatFields.ACTIVITATID);
  }

  public LongField FUNCIONARIID() {
    return new LongField(getQueryPath(), ActivitatFields.FUNCIONARIID);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), ActivitatFields.TIPUS);
  }

  public StringField REGISTRE() {
    return new StringField(getQueryPath(), ActivitatFields.REGISTRE);
  }

  public StringField TRAMIT() {
    return new StringField(getQueryPath(), ActivitatFields.TRAMIT);
  }

  public StringField CODISIA() {
    return new StringField(getQueryPath(), ActivitatFields.CODISIA);
  }

  public LongField AUTORITZACIOID() {
    return new LongField(getQueryPath(), ActivitatFields.AUTORITZACIOID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), ActivitatFields.DATACREACIO);
  }

  public StringField INTERESSATNOM() {
    return new StringField(getQueryPath(), ActivitatFields.INTERESSATNOM);
  }

  public StringField INTERESSATLLINATGE1() {
    return new StringField(getQueryPath(), ActivitatFields.INTERESSATLLINATGE1);
  }

  public StringField INTERESSATLLINATGE2() {
    return new StringField(getQueryPath(), ActivitatFields.INTERESSATLLINATGE2);
  }

  public IntegerField INTERESSATTIPUS() {
    return new IntegerField(getQueryPath(), ActivitatFields.INTERESSATTIPUS);
  }

  public StringField INTERESSATIDENTIFICACIO() {
    return new StringField(getQueryPath(), ActivitatFields.INTERESSATIDENTIFICACIO);
  }

  public StringField REPRESENTANTNOM() {
    return new StringField(getQueryPath(), ActivitatFields.REPRESENTANTNOM);
  }

  public StringField REPRESENTANTLLINATGE1() {
    return new StringField(getQueryPath(), ActivitatFields.REPRESENTANTLLINATGE1);
  }

  public StringField REPRESENTANTLLINATGE2() {
    return new StringField(getQueryPath(), ActivitatFields.REPRESENTANTLLINATGE2);
  }

  public IntegerField REPRESENTANTTIPUS() {
    return new IntegerField(getQueryPath(), ActivitatFields.REPRESENTANTTIPUS);
  }

  public StringField REPRESENTANTIDENTIFICACIO() {
    return new StringField(getQueryPath(), ActivitatFields.REPRESENTANTIDENTIFICACIO);
  }

  public IntegerField TRAMITVERSIO() {
    return new IntegerField(getQueryPath(), ActivitatFields.TRAMITVERSIO);
  }

  public StringField ARXIUDOCUMENTID() {
    return new StringField(getQueryPath(), ActivitatFields.ARXIUDOCUMENTID);
  }

  public StringField ARXIUEXPEDIENTID() {
    return new StringField(getQueryPath(), ActivitatFields.ARXIUEXPEDIENTID);
  }

  public IntegerField ESTAT() {
    return new IntegerField(getQueryPath(), ActivitatFields.ESTAT);
  }

  public StringField URL() {
    return new StringField(getQueryPath(), ActivitatFields.URL);
  }

  public TimestampField DATAACTIVITAT() {
    return new TimestampField(getQueryPath(), ActivitatFields.DATAACTIVITAT);
  }

  public StringField IDACTUACIOTRAMIT() {
    return new StringField(getQueryPath(), ActivitatFields.IDACTUACIOTRAMIT);
  }

  public StringField PROCEDIMENT() {
    return new StringField(getQueryPath(), ActivitatFields.PROCEDIMENT);
  }

  public IntegerField ARXIUREINTENTS() {
    return new IntegerField(getQueryPath(), ActivitatFields.ARXIUREINTENTS);
  }

  public IntegerField ARXIUESTAT() {
    return new IntegerField(getQueryPath(), ActivitatFields.ARXIUESTAT);
  }

  public TimestampField ARXIUDARRERINTENT() {
    return new TimestampField(getQueryPath(), ActivitatFields.ARXIUDARRERINTENT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ActivitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FuncionariQueryPath FUNCIONARI() {
    return new FuncionariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ActivitatQueryPath.this.getQueryPath() + "funcionari" + ".";
      }
    });
  }

}


package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class LlocHabilitacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public LlocHabilitacioQueryPath() {
  }

  protected LlocHabilitacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField LLOCHABILITACIOID() {
    return new LongField(getQueryPath(), LlocHabilitacioFields.LLOCHABILITACIOID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), LlocHabilitacioFields.DATACREACIO);
  }

  public LongField LLOCID() {
    return new LongField(getQueryPath(), LlocHabilitacioFields.LLOCID);
  }

  public LongField HABILITACIOID() {
    return new LongField(getQueryPath(), LlocHabilitacioFields.HABILITACIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (LlocHabilitacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public LlocQueryPath LLOC() {
    return new LlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocHabilitacioQueryPath.this.getQueryPath() + "lloc" + ".";
      }
    });
  }

  public HabilitacioQueryPath HABILITACIO() {
    return new HabilitacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocHabilitacioQueryPath.this.getQueryPath() + "habilitacio" + ".";
      }
    });
  }

}

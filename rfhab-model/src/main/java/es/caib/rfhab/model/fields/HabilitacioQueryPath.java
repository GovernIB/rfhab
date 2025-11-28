
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class HabilitacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public HabilitacioQueryPath() {
  }

  protected HabilitacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField HABILITACIOID() {
    return new LongField(getQueryPath(), HabilitacioFields.HABILITACIOID);
  }

  public LongField NOMID() {
    return new LongField(getQueryPath(), HabilitacioFields.NOMID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), HabilitacioFields.CODI);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), HabilitacioFields.DATACREACIO);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), HabilitacioFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (HabilitacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public LlocHabilitacioQueryPath LLOCHABILITACIOS() {
    return new LlocHabilitacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return HabilitacioQueryPath.this.getQueryPath() + "llocHabilitacios" + ".";
      }
    });
  }
*/

  public TraduccioQueryPath NOM() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return HabilitacioQueryPath.this.getQueryPath() + "nom" + ".";
      }
    });
  }

}


package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UnitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UnitatQueryPath() {
  }

  protected UnitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField UNITATID() {
    return new LongField(getQueryPath(), UnitatFields.UNITATID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), UnitatFields.CODI);
  }

  public IntegerField VERSIO() {
    return new IntegerField(getQueryPath(), UnitatFields.VERSIO);
  }

  public StringField DENOMINACIO() {
    return new StringField(getQueryPath(), UnitatFields.DENOMINACIO);
  }

  public StringField COOFICIAL() {
    return new StringField(getQueryPath(), UnitatFields.COOFICIAL);
  }

  public StringField ARREL() {
    return new StringField(getQueryPath(), UnitatFields.ARREL);
  }

  public IntegerField ARRELVERSIO() {
    return new IntegerField(getQueryPath(), UnitatFields.ARRELVERSIO);
  }

  public StringField SUPERIOR() {
    return new StringField(getQueryPath(), UnitatFields.SUPERIOR);
  }

  public IntegerField SUPERIORVERSIO() {
    return new IntegerField(getQueryPath(), UnitatFields.SUPERIORVERSIO);
  }

  public StringField ESTAT() {
    return new StringField(getQueryPath(), UnitatFields.ESTAT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UnitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITATS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UnitatQueryPath.this.getQueryPath() + "entitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public LlocQueryPath LLOCS() {
    return new LlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UnitatQueryPath.this.getQueryPath() + "llocs" + ".";
      }
    });
  }
*/

}

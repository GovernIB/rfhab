
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AutoritzacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AutoritzacioQueryPath() {
  }

  protected AutoritzacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField AUTORITZACIOID() {
    return new LongField(getQueryPath(), AutoritzacioFields.AUTORITZACIOID);
  }

  public LongField LLOCID() {
    return new LongField(getQueryPath(), AutoritzacioFields.LLOCID);
  }

  public StringField CODISIA() {
    return new StringField(getQueryPath(), AutoritzacioFields.CODISIA);
  }

  public StringField PROCEDIMENT() {
    return new StringField(getQueryPath(), AutoritzacioFields.PROCEDIMENT);
  }

  public StringField CAI() {
    return new StringField(getQueryPath(), AutoritzacioFields.CAI);
  }

  public DateField DATAINICI() {
    return new DateField(getQueryPath(), AutoritzacioFields.DATAINICI);
  }

  public DateField DATAFI() {
    return new DateField(getQueryPath(), AutoritzacioFields.DATAFI);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), AutoritzacioFields.DATACREACIO);
  }

  public StringField OBSERVACIONS() {
    return new StringField(getQueryPath(), AutoritzacioFields.OBSERVACIONS);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), AutoritzacioFields.USUARIID);
  }

  public LongField FUNCIONARIID() {
    return new LongField(getQueryPath(), AutoritzacioFields.FUNCIONARIID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AutoritzacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ActivitatQueryPath ACTIVITATS() {
    return new ActivitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AutoritzacioQueryPath.this.getQueryPath() + "activitats" + ".";
      }
    });
  }
*/

  public LlocQueryPath LLOC() {
    return new LlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AutoritzacioQueryPath.this.getQueryPath() + "lloc" + ".";
      }
    });
  }

  public FuncionariQueryPath FUNCIONARI() {
    return new FuncionariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AutoritzacioQueryPath.this.getQueryPath() + "funcionari" + ".";
      }
    });
  }

}

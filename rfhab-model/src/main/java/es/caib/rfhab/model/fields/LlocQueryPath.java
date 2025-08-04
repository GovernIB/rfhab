
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class LlocQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public LlocQueryPath() {
  }

  protected LlocQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField LLOCID() {
    return new LongField(getQueryPath(), LlocFields.LLOCID);
  }

  public StringField CODILLOC() {
    return new StringField(getQueryPath(), LlocFields.CODILLOC);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), LlocFields.NOM);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), LlocFields.ENTITATID);
  }

  public LongField UNITATID() {
    return new LongField(getQueryPath(), LlocFields.UNITATID);
  }

  public IntegerField PERSONALOAMR() {
    return new IntegerField(getQueryPath(), LlocFields.PERSONALOAMR);
  }

  public TimestampField DATAALTA() {
    return new TimestampField(getQueryPath(), LlocFields.DATAALTA);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), LlocFields.DATACREACIO);
  }

  public TimestampField DATABAIXA() {
    return new TimestampField(getQueryPath(), LlocFields.DATABAIXA);
  }

  public StringField OBSERVACIONS() {
    return new StringField(getQueryPath(), LlocFields.OBSERVACIONS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (LlocFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AutoritzacioQueryPath AUTORITZACIOS() {
    return new AutoritzacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocQueryPath.this.getQueryPath() + "autoritzacios" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public FuncionariLlocQueryPath FUNCIONARILLOCS() {
    return new FuncionariLlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocQueryPath.this.getQueryPath() + "funcionariLlocs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public HistoricLlocQueryPath HISTORICLLOCS() {
    return new HistoricLlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocQueryPath.this.getQueryPath() + "historicLlocs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public LlocRolQueryPath LLOCROLS() {
    return new LlocRolQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocQueryPath.this.getQueryPath() + "llocRols" + ".";
      }
    });
  }
*/

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public UnitatQueryPath UNITAT() {
    return new UnitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return LlocQueryPath.this.getQueryPath() + "unitat" + ".";
      }
    });
  }

}

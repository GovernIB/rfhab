
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FuncionariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FuncionariQueryPath() {
  }

  protected FuncionariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FUNCIONARIID() {
    return new LongField(getQueryPath(), FuncionariFields.FUNCIONARIID);
  }

  public IntegerField NUMERO() {
    return new IntegerField(getQueryPath(), FuncionariFields.NUMERO);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), FuncionariFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), FuncionariFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), FuncionariFields.LLINATGE2);
  }

  public IntegerField TIPUSIDENTIFICADOR() {
    return new IntegerField(getQueryPath(), FuncionariFields.TIPUSIDENTIFICADOR);
  }

  public StringField IDENTIFICADOR() {
    return new StringField(getQueryPath(), FuncionariFields.IDENTIFICADOR);
  }

  public StringField USUARI() {
    return new StringField(getQueryPath(), FuncionariFields.USUARI);
  }

  public StringField CORREU() {
    return new StringField(getQueryPath(), FuncionariFields.CORREU);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), FuncionariFields.DATACREACIO);
  }

  public StringField OBSERVACIONS() {
    return new StringField(getQueryPath(), FuncionariFields.OBSERVACIONS);
  }

  public TimestampField DATABAIXA() {
    return new TimestampField(getQueryPath(), FuncionariFields.DATABAIXA);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), FuncionariFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FuncionariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ActivitatQueryPath ACTIVITATS() {
    return new ActivitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariQueryPath.this.getQueryPath() + "activitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AutoritzacioQueryPath AUTORITZACIOS() {
    return new AutoritzacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariQueryPath.this.getQueryPath() + "autoritzacios" + ".";
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
          return FuncionariQueryPath.this.getQueryPath() + "funcionariLlocs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public HistoricQueryPath HISTORICS() {
    return new HistoricQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FuncionariQueryPath.this.getQueryPath() + "historics" + ".";
      }
    });
  }
*/

}

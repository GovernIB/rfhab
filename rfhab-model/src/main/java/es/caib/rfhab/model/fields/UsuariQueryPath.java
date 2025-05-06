
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariQueryPath() {
  }

  protected UsuariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), UsuariFields.USUARIID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), UsuariFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), UsuariFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), UsuariFields.LLINATGE2);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), UsuariFields.NIF);
  }

  public StringField USERNAME() {
    return new StringField(getQueryPath(), UsuariFields.USERNAME);
  }

  public StringField CORREU() {
    return new StringField(getQueryPath(), UsuariFields.CORREU);
  }

  public StringField IDIOMAID() {
    return new StringField(getQueryPath(), UsuariFields.IDIOMAID);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), UsuariFields.ACTIU);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), UsuariFields.DATACREACIO);
  }

  public LongField DARRERAENTITAT() {
    return new LongField(getQueryPath(), UsuariFields.DARRERAENTITAT);
  }

  public TimestampField DATABAIXA() {
    return new TimestampField(getQueryPath(), UsuariFields.DATABAIXA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ScanWebQueryPath SCANWEBS() {
    return new ScanWebQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "scanWebs" + ".";
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
          return UsuariQueryPath.this.getQueryPath() + "funcionariLlocs" + ".";
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
          return UsuariQueryPath.this.getQueryPath() + "historics" + ".";
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
          return UsuariQueryPath.this.getQueryPath() + "historicLlocs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariEntitatQueryPath USUARIENTITATS() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "usuariEntitats" + ".";
      }
    });
  }
*/

  public IdiomaQueryPath IDIOMA() {
    return new IdiomaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "idioma" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}

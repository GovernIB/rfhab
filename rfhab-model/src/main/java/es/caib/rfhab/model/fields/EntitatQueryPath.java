
package es.caib.rfhab.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntitatQueryPath() {
  }

  protected EntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), EntitatFields.ENTITATID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EntitatFields.NOM);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), EntitatFields.ACTIU);
  }

  public LongField UNITATID() {
    return new LongField(getQueryPath(), EntitatFields.UNITATID);
  }

  public TimestampField DATABAIXA() {
    return new TimestampField(getQueryPath(), EntitatFields.DATABAIXA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public LlocQueryPath LLOCS() {
    return new LlocQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "llocs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PluginQueryPath PLUGINS() {
    return new PluginQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "plugins" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariQueryPath USUARIS() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "usuaris" + ".";
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
          return EntitatQueryPath.this.getQueryPath() + "usuariEntitats" + ".";
      }
    });
  }
*/

  public UnitatQueryPath UNITAT() {
    return new UnitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "unitat" + ".";
      }
    });
  }

}


package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "PluginJPA")
@Table(name = "rfh_plugin" , indexes = { 
        @Index(name="rfh_plugin_pk_i", columnList = "pluginid"),
        @Index(name="rfh_plugin_entitatid_fk_i", columnList = "entitatid")})
@SequenceGenerator(name="PLUGIN_SEQ", sequenceName="rfh_plugin_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PluginJPA implements Plugin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLUGIN_SEQ")
    @Column(name="pluginid",nullable = false,length = 19)
    long pluginID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="descripcio",nullable = false,length = 255)
    java.lang.String descripcio;

    @Column(name="classe",length = 255)
    java.lang.String classe;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Column(name="properties",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String properties;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu;

    @Column(name="datacreacio",length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="tipus",length = 50)
    java.lang.String tipus;



  /** Constructor Buit */
  public PluginJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginJPA(long pluginID , java.lang.String nom , java.lang.String descripcio , java.lang.String classe , long entitatID , java.lang.String properties , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.String tipus) {
    this.pluginID=pluginID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.classe=classe;
    this.entitatID=entitatID;
    this.properties=properties;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public PluginJPA(java.lang.String nom , java.lang.String descripcio , java.lang.String classe , long entitatID , java.lang.String properties , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.String tipus) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.classe=classe;
    this.entitatID=entitatID;
    this.properties=properties;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public PluginJPA(long pluginID , java.lang.String nom , java.lang.String descripcio , long entitatID , boolean actiu) {
    this.pluginID=pluginID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  public PluginJPA(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setClasse(__bean.getClasse());
    this.setEntitatID(__bean.getEntitatID());
    this.setProperties(__bean.getProperties());
    this.setActiu(__bean.isActiu());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setTipus(__bean.getTipus());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getProperties() {
		return(properties);
	};
	public void setProperties(java.lang.String _properties_) {
		this.properties = _properties_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Plugin) {
      Plugin __instance = (Plugin)__obj;
      __result = true;
      __result = __result && (this.getPluginID() == __instance.getPluginID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatid | Table: rfh_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_plugin_entitat_entitati_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PluginJPA toJPA(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginJPA __tmp = new PluginJPA();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setProperties(__bean.getProperties());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}


  public static PluginJPA copyJPA(PluginJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginJPA> copyJPA(java.util.Set<PluginJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PluginJPA> __tmpSet = (java.util.Set<PluginJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginJPA copyJPA(PluginJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginJPA __tmp = (PluginJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"PluginJPA"));
    }

    return __tmp;
  }




}

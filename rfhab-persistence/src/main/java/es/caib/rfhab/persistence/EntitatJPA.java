
package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "EntitatJPA")
@Table(name = "rfh_entitat" , indexes = { 
        @Index(name="rfh_entitat_pk_i", columnList = "entitatid"),
        @Index(name="rfh_entitat_unitatid_fk_i", columnList = "unitatid")})
@SequenceGenerator(name="ENTITAT_SEQ", sequenceName="rfh_entitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntitatJPA implements Entitat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENTITAT_SEQ")
    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Column(name="nom",length = 255)
    java.lang.String nom;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu;

    @Column(name="unitatid",length = 19)
    java.lang.Long unitatID;

    @Column(name="databaixa",length = 29,precision = 6)
    java.sql.Timestamp dataBaixa;



  /** Constructor Buit */
  public EntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatJPA(long entitatID , java.lang.String nom , boolean actiu , java.lang.Long unitatID , java.sql.Timestamp dataBaixa) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.actiu=actiu;
    this.unitatID=unitatID;
    this.dataBaixa=dataBaixa;
}
  /** Constructor sense valors autoincrementals */
  public EntitatJPA(java.lang.String nom , boolean actiu , java.lang.Long unitatID , java.sql.Timestamp dataBaixa) {
    this.nom=nom;
    this.actiu=actiu;
    this.unitatID=unitatID;
    this.dataBaixa=dataBaixa;
}
  /** Constructor dels valors Not Null */
  public EntitatJPA(long entitatID , boolean actiu) {
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  public EntitatJPA(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNom(__bean.getNom());
    this.setActiu(__bean.isActiu());
    this.setUnitatID(__bean.getUnitatID());
    this.setDataBaixa(__bean.getDataBaixa());
	}

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.Long getUnitatID() {
		return(unitatID);
	};
	public void setUnitatID(java.lang.Long _unitatID_) {
		this.unitatID = _unitatID_;
	};

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Entitat) {
            Entitat __instance = (Entitat)__obj;
            __result = true;
            __result = __result && (this.getEntitatID() == __instance.getEntitatID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getEntitatID())).hashCode();
    }

// EXP  Field:entitatid | Table: rfh_lloc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<LlocJPA> llocs = new HashSet<LlocJPA>(0);
    public  Set<LlocJPA> getLlocs() {
    return this.llocs;
  }

    public void setLlocs(Set<LlocJPA> llocs) {
      this.llocs = llocs;
    }


// EXP  Field:entitatid | Table: rfh_plugin | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<PluginJPA> plugins = new HashSet<PluginJPA>(0);
    public  Set<PluginJPA> getPlugins() {
    return this.plugins;
  }

    public void setPlugins(Set<PluginJPA> plugins) {
      this.plugins = plugins;
    }


// EXP  Field:darreraentitat | Table: rfh_usuari | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<UsuariJPA> usuaris = new HashSet<UsuariJPA>(0);
    public  Set<UsuariJPA> getUsuaris() {
    return this.usuaris;
  }

    public void setUsuaris(Set<UsuariJPA> usuaris) {
      this.usuaris = usuaris;
    }


// EXP  Field:entitatid | Table: rfh_usuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
    public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

    public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
      this.usuariEntitats = usuariEntitats;
    }


// IMP Field:unitatid | Table: rfh_unitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitatid", referencedColumnName ="unitatID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_entitat_unitat_unitatid_fk"))
    private UnitatJPA unitat;

    public UnitatJPA getUnitat() {
    return this.unitat;
  }

    public  void setUnitat(UnitatJPA unitat) {
    this.unitat = unitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static EntitatJPA toJPA(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatJPA __tmp = new EntitatJPA();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setUnitatID(__bean.getUnitatID());
    __tmp.setDataBaixa(__bean.getDataBaixa());
		return __tmp;
	}


  public static EntitatJPA copyJPA(EntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EntitatJPA> copyJPA(java.util.Set<EntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EntitatJPA> __tmpSet = (java.util.Set<EntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EntitatJPA copyJPA(EntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EntitatJPA __tmp = (EntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"LlocJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.llocs) || org.hibernate.Hibernate.isInitialized(__jpa.getLlocs())) ) {
      __tmp.setLlocs(LlocJPA.copyJPA(__jpa.getLlocs(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugins) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugins())) ) {
      __tmp.setPlugins(PluginJPA.copyJPA(__jpa.getPlugins(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuaris) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuaris())) ) {
      __tmp.setUsuaris(UsuariJPA.copyJPA(__jpa.getUsuaris(), __alreadyCopied,"EntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UnitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.unitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUnitat()) ) ) {
      __tmp.setUnitat(UnitatJPA.copyJPA(__jpa.getUnitat(), __alreadyCopied,"EntitatJPA"));
    }

    return __tmp;
  }




}

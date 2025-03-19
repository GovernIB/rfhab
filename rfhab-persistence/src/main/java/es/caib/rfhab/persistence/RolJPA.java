
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


@Entity(name = "RolJPA")
@Table(name = "rfh_rol" , indexes = { 
        @Index(name="rfh_rol_pk_i", columnList = "rolid"),
        @Index(name="rfh_rol_nomid_fk_i", columnList = "nomid")})
@SequenceGenerator(name="ROL_SEQ", sequenceName="rfh_rol_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class RolJPA implements Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ROL_SEQ")
    @Column(name="rolid",nullable = false,length = 19)
    long rolID;

    @Column(name="nomid",nullable = false,length = 19)
    java.lang.Long nomID;

    @Column(name="codi",nullable = false,length = 50)
    java.lang.String codi;

    @Column(name="datacreacio",length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="entitatid",nullable = false,length = 19)
    java.lang.Long entitatID;



  /** Constructor Buit */
  public RolJPA() {
  }

  /** Constructor amb tots els camps  */
  public RolJPA(long rolID , java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.rolID=rolID;
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public RolJPA(java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  public RolJPA(Rol __bean) {
    this.setRolID(__bean.getRolID());
    this.setNomID(__bean.getNomID());
    this.setCodi(__bean.getCodi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getRolID() {
		return(rolID);
	};
	public void setRolID(long _rolID_) {
		this.rolID = _rolID_;
	};

	public java.lang.Long getNomID() {
		return(nomID);
	};
	public void setNomID(java.lang.Long _nomID_) {
		this.nomID = _nomID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Rol) {
            Rol __instance = (Rol)__obj;
            __result = true;
            __result = __result && (this.getRolID() == __instance.getRolID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getRolID())).hashCode();
    }

// EXP  Field:rolid | Table: rfh_llocrol | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<LlocRolJPA> llocRols = new HashSet<LlocRolJPA>(0);
    public  Set<LlocRolJPA> getLlocRols() {
    return this.llocRols;
  }

    public void setLlocRols(Set<LlocRolJPA> llocRols) {
      this.llocRols = llocRols;
    }


// IMP Field:traduccioid | Table: rfh_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_rol_traduccio_nomid_fk"))
    private TraduccioJPA nom;

    public TraduccioJPA getNom() {
    return this.nom;
  }

    public  void setNom(TraduccioJPA nom) {
    this.nom = nom;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.rfhab.persistence.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.rfhab.persistence.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }



 // ---------------  STATIC METHODS ------------------
  public static RolJPA toJPA(Rol __bean) {
    if (__bean == null) { return null;}
    RolJPA __tmp = new RolJPA();
    __tmp.setRolID(__bean.getRolID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static RolJPA copyJPA(RolJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<RolJPA> copyJPA(java.util.Set<RolJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<RolJPA> __tmpSet = (java.util.Set<RolJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<RolJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (RolJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static RolJPA copyJPA(RolJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    RolJPA __tmp = (RolJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"LlocRolJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.llocRols) || org.hibernate.Hibernate.isInitialized(__jpa.getLlocRols())) ) {
      __tmp.setLlocRols(LlocRolJPA.copyJPA(__jpa.getLlocRols(), __alreadyCopied,"RolJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"RolJPA"));
    }

    return __tmp;
  }




}


package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "LlocRolJPA")
@Table(name = "rfh_llocrol" , indexes = { 
        @Index(name="rfh_llocrol_pk_i", columnList = "llocrolid"),
        @Index(name="rfh_llocrol_llocid_fk_i", columnList = "llocid"),
        @Index(name="rfh_llocrol_rolid_fk_i", columnList = "rolid")})
@SequenceGenerator(name="LLOCROL_SEQ", sequenceName="rfh_llocrol_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class LlocRolJPA implements LlocRol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LLOCROL_SEQ")
    @Column(name="llocrolid",nullable = false,length = 19)
    long llocRolID;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="llocid",nullable = false,length = 19)
    long llocID;

    @Column(name="rolid",nullable = false,length = 19)
    long rolID;



  /** Constructor Buit */
  public LlocRolJPA() {
  }

  /** Constructor amb tots els camps  */
  public LlocRolJPA(long llocRolID , java.sql.Timestamp dataCreacio , long llocID , long rolID) {
    this.llocRolID=llocRolID;
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.rolID=rolID;
}
  /** Constructor sense valors autoincrementals */
  public LlocRolJPA(java.sql.Timestamp dataCreacio , long llocID , long rolID) {
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.rolID=rolID;
}
  public LlocRolJPA(LlocRol __bean) {
    this.setLlocRolID(__bean.getLlocRolID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setLlocID(__bean.getLlocID());
    this.setRolID(__bean.getRolID());
	}

	public long getLlocRolID() {
		return(llocRolID);
	};
	public void setLlocRolID(long _llocRolID_) {
		this.llocRolID = _llocRolID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public long getRolID() {
		return(rolID);
	};
	public void setRolID(long _rolID_) {
		this.rolID = _rolID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof LlocRol) {
            LlocRol __instance = (LlocRol)__obj;
            __result = true;
            __result = __result && (this.getLlocRolID() == __instance.getLlocRolID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getLlocRolID())).hashCode();
    }

// IMP Field:llocid | Table: rfh_lloc | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "llocid", referencedColumnName ="llocID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_llocrol_lloc_llocid_fk"))
    private LlocJPA lloc;

    public LlocJPA getLloc() {
    return this.lloc;
  }

    public  void setLloc(LlocJPA lloc) {
    this.lloc = lloc;
  }

// IMP Field:rolid | Table: rfh_rol | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolid", referencedColumnName ="rolID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_llocrol_rol_rolid_fk"))
    private RolJPA rol;

    public RolJPA getRol() {
    return this.rol;
  }

    public  void setRol(RolJPA rol) {
    this.rol = rol;
  }


 // ---------------  STATIC METHODS ------------------
  public static LlocRolJPA toJPA(LlocRol __bean) {
    if (__bean == null) { return null;}
    LlocRolJPA __tmp = new LlocRolJPA();
    __tmp.setLlocRolID(__bean.getLlocRolID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setRolID(__bean.getRolID());
		return __tmp;
	}


  public static LlocRolJPA copyJPA(LlocRolJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<LlocRolJPA> copyJPA(java.util.Set<LlocRolJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<LlocRolJPA> __tmpSet = (java.util.Set<LlocRolJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<LlocRolJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (LlocRolJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static LlocRolJPA copyJPA(LlocRolJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    LlocRolJPA __tmp = (LlocRolJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"LlocJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.lloc) || org.hibernate.Hibernate.isInitialized(__jpa.getLloc()) ) ) {
      __tmp.setLloc(LlocJPA.copyJPA(__jpa.getLloc(), __alreadyCopied,"LlocRolJPA"));
    }
    if(!"RolJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.rol) || org.hibernate.Hibernate.isInitialized(__jpa.getRol()) ) ) {
      __tmp.setRol(RolJPA.copyJPA(__jpa.getRol(), __alreadyCopied,"LlocRolJPA"));
    }

    return __tmp;
  }




}

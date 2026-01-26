
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


@Entity(name = "LlocHabilitacioJPA")
@Table(name = "rfh_llochabilitacio" , indexes = { 
        @Index(name="rfh_llochabilitacio_pk_i", columnList = "llochabilitacioid"),
        @Index(name="rfh_llochabil_llocid_fk_i", columnList = "llocid"),
        @Index(name="rfh_llochabil_hab_habid_fk_i", columnList = "habilitacioid")})
@SequenceGenerator(name="LLOCHABILITACIO_SEQ", sequenceName="rfh_llochabilitacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class LlocHabilitacioJPA implements LlocHabilitacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LLOCHABILITACIO_SEQ")
    @Column(name="llochabilitacioid",nullable = false,length = 19)
    long llocHabilitacioID;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="llocid",nullable = false,length = 19)
    long llocID;

    @Column(name="habilitacioid",nullable = false,length = 19)
    long habilitacioId;



  /** Constructor Buit */
  public LlocHabilitacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public LlocHabilitacioJPA(long llocHabilitacioID , java.sql.Timestamp dataCreacio , long llocID , long habilitacioId) {
    this.llocHabilitacioID=llocHabilitacioID;
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.habilitacioId=habilitacioId;
}
  /** Constructor sense valors autoincrementals */
  public LlocHabilitacioJPA(java.sql.Timestamp dataCreacio , long llocID , long habilitacioId) {
    this.dataCreacio=dataCreacio;
    this.llocID=llocID;
    this.habilitacioId=habilitacioId;
}
  public LlocHabilitacioJPA(LlocHabilitacio __bean) {
    this.setLlocHabilitacioID(__bean.getLlocHabilitacioID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setLlocID(__bean.getLlocID());
    this.setHabilitacioId(__bean.getHabilitacioId());
	}

	public long getLlocHabilitacioID() {
		return(llocHabilitacioID);
	};
	public void setLlocHabilitacioID(long _llocHabilitacioID_) {
		this.llocHabilitacioID = _llocHabilitacioID_;
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

	public long getHabilitacioId() {
		return(habilitacioId);
	};
	public void setHabilitacioId(long _habilitacioId_) {
		this.habilitacioId = _habilitacioId_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof LlocHabilitacio) {
            LlocHabilitacio __instance = (LlocHabilitacio)__obj;
            __result = true;
            __result = __result && (this.getLlocHabilitacioID() == __instance.getLlocHabilitacioID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:llocid | Table: rfh_lloc | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "llocid", referencedColumnName ="llocID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_llochabil_lloc_llocid_fk"))
    private LlocJPA lloc;

    public LlocJPA getLloc() {
    return this.lloc;
  }

    public  void setLloc(LlocJPA lloc) {
    this.lloc = lloc;
  }

// IMP Field:habilitacioid | Table: rfh_habilitacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilitacioid", referencedColumnName ="habilitacioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_llochabil_hab_habid_fk"))
    private HabilitacioJPA habilitacio;

    public HabilitacioJPA getHabilitacio() {
    return this.habilitacio;
  }

    public  void setHabilitacio(HabilitacioJPA habilitacio) {
    this.habilitacio = habilitacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static LlocHabilitacioJPA toJPA(LlocHabilitacio __bean) {
    if (__bean == null) { return null;}
    LlocHabilitacioJPA __tmp = new LlocHabilitacioJPA();
    __tmp.setLlocHabilitacioID(__bean.getLlocHabilitacioID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setHabilitacioId(__bean.getHabilitacioId());
		return __tmp;
	}


  public static LlocHabilitacioJPA copyJPA(LlocHabilitacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<LlocHabilitacioJPA> copyJPA(java.util.Set<LlocHabilitacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<LlocHabilitacioJPA> __tmpSet = (java.util.Set<LlocHabilitacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<LlocHabilitacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (LlocHabilitacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static LlocHabilitacioJPA copyJPA(LlocHabilitacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    LlocHabilitacioJPA __tmp = (LlocHabilitacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"LlocJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.lloc) || org.hibernate.Hibernate.isInitialized(__jpa.getLloc()) ) ) {
      __tmp.setLloc(LlocJPA.copyJPA(__jpa.getLloc(), __alreadyCopied,"LlocHabilitacioJPA"));
    }
    if(!"HabilitacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.habilitacio) || org.hibernate.Hibernate.isInitialized(__jpa.getHabilitacio()) ) ) {
      __tmp.setHabilitacio(HabilitacioJPA.copyJPA(__jpa.getHabilitacio(), __alreadyCopied,"LlocHabilitacioJPA"));
    }

    return __tmp;
  }




}

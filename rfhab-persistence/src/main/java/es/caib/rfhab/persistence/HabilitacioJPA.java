
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


@Entity(name = "HabilitacioJPA")
@Table(name = "rfh_habilitacio" , indexes = { 
        @Index(name="rfh_habilitacio_pk_i", columnList = "habilitacioid"),
        @Index(name="rfh_habilitacio_nomid_fk_i", columnList = "nomid")})
@SequenceGenerator(name="HABILITACIO_SEQ", sequenceName="rfh_habilitacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class HabilitacioJPA implements Habilitacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HABILITACIO_SEQ")
    @Column(name="habilitacioid",nullable = false,length = 19)
    long habilitacioID;

    @Column(name="nomid",nullable = false,length = 19)
    java.lang.Long nomID;

    @Column(name="codi",nullable = false,length = 50)
    java.lang.String codi;

    @Column(name="datacreacio",length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="entitatid",nullable = false,length = 19)
    java.lang.Long entitatID;



  /** Constructor Buit */
  public HabilitacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public HabilitacioJPA(long habilitacioID , java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.habilitacioID=habilitacioID;
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public HabilitacioJPA(java.lang.Long nomID , java.lang.String codi , java.sql.Timestamp dataCreacio , java.lang.Long entitatID) {
    this.nomID=nomID;
    this.codi=codi;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  public HabilitacioJPA(Habilitacio __bean) {
    this.setHabilitacioID(__bean.getHabilitacioID());
    this.setNomID(__bean.getNomID());
    this.setCodi(__bean.getCodi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getHabilitacioID() {
		return(habilitacioID);
	};
	public void setHabilitacioID(long _habilitacioID_) {
		this.habilitacioID = _habilitacioID_;
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
        if (__obj != null && __obj instanceof Habilitacio) {
            Habilitacio __instance = (Habilitacio)__obj;
            __result = true;
            __result = __result && (this.getHabilitacioID() == __instance.getHabilitacioID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:habilitacioid | Table: rfh_llochabilitacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "habilitacio")
    private Set<LlocHabilitacioJPA> llocHabilitacios = new HashSet<LlocHabilitacioJPA>(0);
    public  Set<LlocHabilitacioJPA> getLlocHabilitacios() {
    return this.llocHabilitacios;
  }

    public void setLlocHabilitacios(Set<LlocHabilitacioJPA> llocHabilitacios) {
      this.llocHabilitacios = llocHabilitacios;
    }


// IMP Field:traduccioid | Table: rfh_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_habil_traduccio_nomid_fk"))
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
  public static HabilitacioJPA toJPA(Habilitacio __bean) {
    if (__bean == null) { return null;}
    HabilitacioJPA __tmp = new HabilitacioJPA();
    __tmp.setHabilitacioID(__bean.getHabilitacioID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static HabilitacioJPA copyJPA(HabilitacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<HabilitacioJPA> copyJPA(java.util.Set<HabilitacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<HabilitacioJPA> __tmpSet = (java.util.Set<HabilitacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<HabilitacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (HabilitacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static HabilitacioJPA copyJPA(HabilitacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    HabilitacioJPA __tmp = (HabilitacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"LlocHabilitacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.llocHabilitacios) || org.hibernate.Hibernate.isInitialized(__jpa.getLlocHabilitacios())) ) {
      __tmp.setLlocHabilitacios(LlocHabilitacioJPA.copyJPA(__jpa.getLlocHabilitacios(), __alreadyCopied,"HabilitacioJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"HabilitacioJPA"));
    }

    return __tmp;
  }




}

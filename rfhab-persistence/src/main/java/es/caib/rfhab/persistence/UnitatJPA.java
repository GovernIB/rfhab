
package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "UnitatJPA")
@Table(name = "rfh_unitat" , indexes = { 
        @Index(name="rfh_unitat_pk_i", columnList = "unitatid")})
@SequenceGenerator(name="UNITAT_SEQ", sequenceName="rfh_unitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UnitatJPA implements Unitat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UNITAT_SEQ")
    @Column(name="unitatid",nullable = false,length = 19)
    long unitatID;

    @Column(name="codi",nullable = false,length = 50)
    java.lang.String codi;

    @Column(name="versio",nullable = false,length = 10)
    int versio;

    @Column(name="denominacio",nullable = false,length = 255)
    java.lang.String denominacio;

    @Column(name="cooficial",length = 255)
    java.lang.String cooficial;

    @Column(name="arrel",length = 50)
    java.lang.String arrel;

    @Column(name="arrelversio",length = 10)
    java.lang.Integer arrelVersio;

    @Column(name="superior",length = 50)
    java.lang.String superior;

    @Column(name="superiorversio",length = 10)
    java.lang.Integer superiorVersio;

    @Column(name="estat",length = 5)
    java.lang.String estat;



  /** Constructor Buit */
  public UnitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public UnitatJPA(long unitatID , java.lang.String codi , int versio , java.lang.String denominacio , java.lang.String cooficial , java.lang.String arrel , java.lang.Integer arrelVersio , java.lang.String superior , java.lang.Integer superiorVersio , java.lang.String estat) {
    this.unitatID=unitatID;
    this.codi=codi;
    this.versio=versio;
    this.denominacio=denominacio;
    this.cooficial=cooficial;
    this.arrel=arrel;
    this.arrelVersio=arrelVersio;
    this.superior=superior;
    this.superiorVersio=superiorVersio;
    this.estat=estat;
}
  /** Constructor sense valors autoincrementals */
  public UnitatJPA(java.lang.String codi , int versio , java.lang.String denominacio , java.lang.String cooficial , java.lang.String arrel , java.lang.Integer arrelVersio , java.lang.String superior , java.lang.Integer superiorVersio , java.lang.String estat) {
    this.codi=codi;
    this.versio=versio;
    this.denominacio=denominacio;
    this.cooficial=cooficial;
    this.arrel=arrel;
    this.arrelVersio=arrelVersio;
    this.superior=superior;
    this.superiorVersio=superiorVersio;
    this.estat=estat;
}
  /** Constructor dels valors Not Null */
  public UnitatJPA(long unitatID , java.lang.String codi , int versio , java.lang.String denominacio) {
    this.unitatID=unitatID;
    this.codi=codi;
    this.versio=versio;
    this.denominacio=denominacio;
}
  public UnitatJPA(Unitat __bean) {
    this.setUnitatID(__bean.getUnitatID());
    this.setCodi(__bean.getCodi());
    this.setVersio(__bean.getVersio());
    this.setDenominacio(__bean.getDenominacio());
    this.setCooficial(__bean.getCooficial());
    this.setArrel(__bean.getArrel());
    this.setArrelVersio(__bean.getArrelVersio());
    this.setSuperior(__bean.getSuperior());
    this.setSuperiorVersio(__bean.getSuperiorVersio());
    this.setEstat(__bean.getEstat());
	}

	public long getUnitatID() {
		return(unitatID);
	};
	public void setUnitatID(long _unitatID_) {
		this.unitatID = _unitatID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public int getVersio() {
		return(versio);
	};
	public void setVersio(int _versio_) {
		this.versio = _versio_;
	};

	public java.lang.String getDenominacio() {
		return(denominacio);
	};
	public void setDenominacio(java.lang.String _denominacio_) {
		this.denominacio = _denominacio_;
	};

	public java.lang.String getCooficial() {
		return(cooficial);
	};
	public void setCooficial(java.lang.String _cooficial_) {
		this.cooficial = _cooficial_;
	};

	public java.lang.String getArrel() {
		return(arrel);
	};
	public void setArrel(java.lang.String _arrel_) {
		this.arrel = _arrel_;
	};

	public java.lang.Integer getArrelVersio() {
		return(arrelVersio);
	};
	public void setArrelVersio(java.lang.Integer _arrelVersio_) {
		this.arrelVersio = _arrelVersio_;
	};

	public java.lang.String getSuperior() {
		return(superior);
	};
	public void setSuperior(java.lang.String _superior_) {
		this.superior = _superior_;
	};

	public java.lang.Integer getSuperiorVersio() {
		return(superiorVersio);
	};
	public void setSuperiorVersio(java.lang.Integer _superiorVersio_) {
		this.superiorVersio = _superiorVersio_;
	};

	public java.lang.String getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.String _estat_) {
		this.estat = _estat_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Unitat) {
            Unitat __instance = (Unitat)__obj;
            __result = true;
            __result = __result && (this.getUnitatID() == __instance.getUnitatID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:unitatid | Table: rfh_entitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unitat")
    private Set<EntitatJPA> entitats = new HashSet<EntitatJPA>(0);
    public  Set<EntitatJPA> getEntitats() {
    return this.entitats;
  }

    public void setEntitats(Set<EntitatJPA> entitats) {
      this.entitats = entitats;
    }


// EXP  Field:unitatid | Table: rfh_lloc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unitat")
    private Set<LlocJPA> llocs = new HashSet<LlocJPA>(0);
    public  Set<LlocJPA> getLlocs() {
    return this.llocs;
  }

    public void setLlocs(Set<LlocJPA> llocs) {
      this.llocs = llocs;
    }



 // ---------------  STATIC METHODS ------------------
  public static UnitatJPA toJPA(Unitat __bean) {
    if (__bean == null) { return null;}
    UnitatJPA __tmp = new UnitatJPA();
    __tmp.setUnitatID(__bean.getUnitatID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setDenominacio(__bean.getDenominacio());
    __tmp.setCooficial(__bean.getCooficial());
    __tmp.setArrel(__bean.getArrel());
    __tmp.setArrelVersio(__bean.getArrelVersio());
    __tmp.setSuperior(__bean.getSuperior());
    __tmp.setSuperiorVersio(__bean.getSuperiorVersio());
    __tmp.setEstat(__bean.getEstat());
		return __tmp;
	}


  public static UnitatJPA copyJPA(UnitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UnitatJPA> copyJPA(java.util.Set<UnitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UnitatJPA> __tmpSet = (java.util.Set<UnitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UnitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UnitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UnitatJPA copyJPA(UnitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UnitatJPA __tmp = (UnitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitats) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitats())) ) {
      __tmp.setEntitats(EntitatJPA.copyJPA(__jpa.getEntitats(), __alreadyCopied,"UnitatJPA"));
    }
    if(!"LlocJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.llocs) || org.hibernate.Hibernate.isInitialized(__jpa.getLlocs())) ) {
      __tmp.setLlocs(LlocJPA.copyJPA(__jpa.getLlocs(), __alreadyCopied,"UnitatJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}


package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "LlocJPA")
@Table(name = "rfh_lloc" , indexes = { 
        @Index(name="rfh_lloc_pk_i", columnList = "llocid"),
        @Index(name="rfh_lloc_entitatid_fk_i", columnList = "entitatid"),
        @Index(name="rfh_lloc_unitatid_fk_i", columnList = "unitatid")})
@SequenceGenerator(name="LLOC_SEQ", sequenceName="rfh_lloc_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class LlocJPA implements Lloc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LLOC_SEQ")
    @Column(name="llocid",nullable = false,length = 19)
    long llocID;

    @Column(name="codilloc",nullable = false,unique = true,length = 50)
    java.lang.String codiLloc;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="personaloamr",nullable = false,length = 10)
    int personalOamr;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="databaixa",length = 29,precision = 6)
    java.sql.Timestamp dataBaixa;

    @Column(name="observacions",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String observacions;

    @Column(name="unitatid",nullable = false,length = 19)
    long unitatID;



  /** Constructor Buit */
  public LlocJPA() {
  }

  /** Constructor amb tots els camps  */
  public LlocJPA(long llocID , java.lang.String codiLloc , java.lang.String nom , int personalOamr , long entitatID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataBaixa , java.lang.String observacions , long unitatID) {
    this.llocID=llocID;
    this.codiLloc=codiLloc;
    this.nom=nom;
    this.personalOamr=personalOamr;
    this.entitatID=entitatID;
    this.dataCreacio=dataCreacio;
    this.dataBaixa=dataBaixa;
    this.observacions=observacions;
    this.unitatID=unitatID;
}
  /** Constructor sense valors autoincrementals */
  public LlocJPA(java.lang.String codiLloc , java.lang.String nom , int personalOamr , long entitatID , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataBaixa , java.lang.String observacions , long unitatID) {
    this.codiLloc=codiLloc;
    this.nom=nom;
    this.personalOamr=personalOamr;
    this.entitatID=entitatID;
    this.dataCreacio=dataCreacio;
    this.dataBaixa=dataBaixa;
    this.observacions=observacions;
    this.unitatID=unitatID;
}
  /** Constructor dels valors Not Null */
  public LlocJPA(long llocID , java.lang.String codiLloc , java.lang.String nom , int personalOamr , long entitatID , java.sql.Timestamp dataCreacio , long unitatID) {
    this.llocID=llocID;
    this.codiLloc=codiLloc;
    this.nom=nom;
    this.personalOamr=personalOamr;
    this.entitatID=entitatID;
    this.dataCreacio=dataCreacio;
    this.unitatID=unitatID;
}
  public LlocJPA(Lloc __bean) {
    this.setLlocID(__bean.getLlocID());
    this.setCodiLloc(__bean.getCodiLloc());
    this.setNom(__bean.getNom());
    this.setPersonalOamr(__bean.getPersonalOamr());
    this.setEntitatID(__bean.getEntitatID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataBaixa(__bean.getDataBaixa());
    this.setObservacions(__bean.getObservacions());
    this.setUnitatID(__bean.getUnitatID());
	}

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public java.lang.String getCodiLloc() {
		return(codiLloc);
	};
	public void setCodiLloc(java.lang.String _codiLloc_) {
		this.codiLloc = _codiLloc_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public int getPersonalOamr() {
		return(personalOamr);
	};
	public void setPersonalOamr(int _personalOamr_) {
		this.personalOamr = _personalOamr_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public long getUnitatID() {
		return(unitatID);
	};
	public void setUnitatID(long _unitatID_) {
		this.unitatID = _unitatID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Lloc) {
            Lloc __instance = (Lloc)__obj;
            __result = true;
            __result = __result && (this.getLlocID() == __instance.getLlocID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:llocid | Table: rfh_autoritzacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lloc")
    private Set<AutoritzacioJPA> autoritzacios = new HashSet<AutoritzacioJPA>(0);
    public  Set<AutoritzacioJPA> getAutoritzacios() {
    return this.autoritzacios;
  }

    public void setAutoritzacios(Set<AutoritzacioJPA> autoritzacios) {
      this.autoritzacios = autoritzacios;
    }


// EXP  Field:llocid | Table: rfh_funcionarilloc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lloc")
    private Set<FuncionariLlocJPA> funcionariLlocs = new HashSet<FuncionariLlocJPA>(0);
    public  Set<FuncionariLlocJPA> getFuncionariLlocs() {
    return this.funcionariLlocs;
  }

    public void setFuncionariLlocs(Set<FuncionariLlocJPA> funcionariLlocs) {
      this.funcionariLlocs = funcionariLlocs;
    }


// EXP  Field:llocid | Table: rfh_historiclloc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lloc")
    private Set<HistoricLlocJPA> historicLlocs = new HashSet<HistoricLlocJPA>(0);
    public  Set<HistoricLlocJPA> getHistoricLlocs() {
    return this.historicLlocs;
  }

    public void setHistoricLlocs(Set<HistoricLlocJPA> historicLlocs) {
      this.historicLlocs = historicLlocs;
    }


// EXP  Field:llocid | Table: rfh_llocrol | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lloc")
    private Set<LlocRolJPA> llocRols = new HashSet<LlocRolJPA>(0);
    public  Set<LlocRolJPA> getLlocRols() {
    return this.llocRols;
  }

    public void setLlocRols(Set<LlocRolJPA> llocRols) {
      this.llocRols = llocRols;
    }


// IMP Field:entitatid | Table: rfh_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_lloc_entitat_entitatid_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:unitatid | Table: rfh_unitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitatid", referencedColumnName ="unitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_lloc_unitat_unitatid_fk"))
    private UnitatJPA unitat;

    public UnitatJPA getUnitat() {
    return this.unitat;
  }

    public  void setUnitat(UnitatJPA unitat) {
    this.unitat = unitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static LlocJPA toJPA(Lloc __bean) {
    if (__bean == null) { return null;}
    LlocJPA __tmp = new LlocJPA();
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setCodiLloc(__bean.getCodiLloc());
    __tmp.setNom(__bean.getNom());
    __tmp.setPersonalOamr(__bean.getPersonalOamr());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataBaixa(__bean.getDataBaixa());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setUnitatID(__bean.getUnitatID());
		return __tmp;
	}


  public static LlocJPA copyJPA(LlocJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<LlocJPA> copyJPA(java.util.Set<LlocJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<LlocJPA> __tmpSet = (java.util.Set<LlocJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<LlocJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (LlocJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static LlocJPA copyJPA(LlocJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    LlocJPA __tmp = (LlocJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"FuncionariLlocJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.funcionariLlocs) || org.hibernate.Hibernate.isInitialized(__jpa.getFuncionariLlocs())) ) {
      __tmp.setFuncionariLlocs(FuncionariLlocJPA.copyJPA(__jpa.getFuncionariLlocs(), __alreadyCopied,"LlocJPA"));
    }
    if(!"AutoritzacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.autoritzacios) || org.hibernate.Hibernate.isInitialized(__jpa.getAutoritzacios())) ) {
      __tmp.setAutoritzacios(AutoritzacioJPA.copyJPA(__jpa.getAutoritzacios(), __alreadyCopied,"LlocJPA"));
    }
    if(!"LlocRolJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.llocRols) || org.hibernate.Hibernate.isInitialized(__jpa.getLlocRols())) ) {
      __tmp.setLlocRols(LlocRolJPA.copyJPA(__jpa.getLlocRols(), __alreadyCopied,"LlocJPA"));
    }
    if(!"HistoricLlocJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.historicLlocs) || org.hibernate.Hibernate.isInitialized(__jpa.getHistoricLlocs())) ) {
      __tmp.setHistoricLlocs(HistoricLlocJPA.copyJPA(__jpa.getHistoricLlocs(), __alreadyCopied,"LlocJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UnitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.unitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUnitat()) ) ) {
      __tmp.setUnitat(UnitatJPA.copyJPA(__jpa.getUnitat(), __alreadyCopied,"LlocJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"LlocJPA"));
    }

    return __tmp;
  }




}

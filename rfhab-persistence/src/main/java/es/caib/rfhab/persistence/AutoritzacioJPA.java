
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


@Entity(name = "AutoritzacioJPA")
@Table(name = "rfh_autoritzacio" , indexes = { 
        @Index(name="rfh_autoritzacio_pk_i", columnList = "autoritzacioid"),
        @Index(name="rfh_autoritza_funid_fk_i", columnList = "llocid"),
        @Index(name="rfh_autoritza_funcid_fk_i", columnList = "funcionariid")})
@SequenceGenerator(name="AUTORITZACIO_SEQ", sequenceName="rfh_autoritzacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AutoritzacioJPA implements Autoritzacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AUTORITZACIO_SEQ")
    @Column(name="autoritzacioid",nullable = false,length = 19)
    long autoritzacioID;

    @Column(name="llocid",nullable = false,length = 19)
    long llocID;

    @Column(name="codisia",nullable = false,length = 50)
    java.lang.String codiSia;

    @Column(name="procediment",nullable = false,length = 255)
    java.lang.String procediment;

    @Column(name="cai",nullable = false,length = 255)
    java.lang.String cai;

    @Column(name="datainici",length = 13)
    java.sql.Date dataInici;

    @Column(name="datafi",length = 13)
    java.sql.Date dataFi;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="observacions",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String observacions;

    @Column(name="usuariid",length = 19)
    java.lang.Long usuariID;

    @Column(name="funcionariid",length = 19)
    java.lang.Long funcionariID;



  /** Constructor Buit */
  public AutoritzacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public AutoritzacioJPA(long autoritzacioID , long llocID , java.lang.String codiSia , java.lang.String procediment , java.lang.String cai , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.lang.Long usuariID , java.lang.Long funcionariID) {
    this.autoritzacioID=autoritzacioID;
    this.llocID=llocID;
    this.codiSia=codiSia;
    this.procediment=procediment;
    this.cai=cai;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.usuariID=usuariID;
    this.funcionariID=funcionariID;
}
  /** Constructor sense valors autoincrementals */
  public AutoritzacioJPA(long llocID , java.lang.String codiSia , java.lang.String procediment , java.lang.String cai , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.lang.Long usuariID , java.lang.Long funcionariID) {
    this.llocID=llocID;
    this.codiSia=codiSia;
    this.procediment=procediment;
    this.cai=cai;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.usuariID=usuariID;
    this.funcionariID=funcionariID;
}
  /** Constructor dels valors Not Null */
  public AutoritzacioJPA(long autoritzacioID , long llocID , java.lang.String codiSia , java.lang.String procediment , java.lang.String cai , java.sql.Timestamp dataCreacio) {
    this.autoritzacioID=autoritzacioID;
    this.llocID=llocID;
    this.codiSia=codiSia;
    this.procediment=procediment;
    this.cai=cai;
    this.dataCreacio=dataCreacio;
}
  public AutoritzacioJPA(Autoritzacio __bean) {
    this.setAutoritzacioID(__bean.getAutoritzacioID());
    this.setLlocID(__bean.getLlocID());
    this.setCodiSia(__bean.getCodiSia());
    this.setProcediment(__bean.getProcediment());
    this.setCai(__bean.getCai());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setObservacions(__bean.getObservacions());
    this.setUsuariID(__bean.getUsuariID());
    this.setFuncionariID(__bean.getFuncionariID());
	}

	public long getAutoritzacioID() {
		return(autoritzacioID);
	};
	public void setAutoritzacioID(long _autoritzacioID_) {
		this.autoritzacioID = _autoritzacioID_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public java.lang.String getCodiSia() {
		return(codiSia);
	};
	public void setCodiSia(java.lang.String _codiSia_) {
		this.codiSia = _codiSia_;
	};

	public java.lang.String getProcediment() {
		return(procediment);
	};
	public void setProcediment(java.lang.String _procediment_) {
		this.procediment = _procediment_;
	};

	public java.lang.String getCai() {
		return(cai);
	};
	public void setCai(java.lang.String _cai_) {
		this.cai = _cai_;
	};

	public java.sql.Date getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Date _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Date getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Date _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.Long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(java.lang.Long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Autoritzacio) {
            Autoritzacio __instance = (Autoritzacio)__obj;
            __result = true;
            __result = __result && (this.getAutoritzacioID() == __instance.getAutoritzacioID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:llocid | Table: rfh_lloc | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "llocid", referencedColumnName ="llocID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_autoritza_lloc_fd_fk"))
    private LlocJPA lloc;

    public LlocJPA getLloc() {
    return this.lloc;
  }

    public  void setLloc(LlocJPA lloc) {
    this.lloc = lloc;
  }

// IMP Field:funcionariid | Table: rfh_funcionari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionariid", referencedColumnName ="funcionariID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_autoritza_funcionari_i_fk"))
    private FuncionariJPA funcionari;

    public FuncionariJPA getFuncionari() {
    return this.funcionari;
  }

    public  void setFuncionari(FuncionariJPA funcionari) {
    this.funcionari = funcionari;
  }


 // ---------------  STATIC METHODS ------------------
  public static AutoritzacioJPA toJPA(Autoritzacio __bean) {
    if (__bean == null) { return null;}
    AutoritzacioJPA __tmp = new AutoritzacioJPA();
    __tmp.setAutoritzacioID(__bean.getAutoritzacioID());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setCodiSia(__bean.getCodiSia());
    __tmp.setProcediment(__bean.getProcediment());
    __tmp.setCai(__bean.getCai());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
		return __tmp;
	}


  public static AutoritzacioJPA copyJPA(AutoritzacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AutoritzacioJPA> copyJPA(java.util.Set<AutoritzacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<AutoritzacioJPA> __tmpSet = (java.util.Set<AutoritzacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AutoritzacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AutoritzacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AutoritzacioJPA copyJPA(AutoritzacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AutoritzacioJPA __tmp = (AutoritzacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"LlocJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.lloc) || org.hibernate.Hibernate.isInitialized(__jpa.getLloc()) ) ) {
      __tmp.setLloc(LlocJPA.copyJPA(__jpa.getLloc(), __alreadyCopied,"AutoritzacioJPA"));
    }
    if(!"FuncionariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.funcionari) || org.hibernate.Hibernate.isInitialized(__jpa.getFuncionari()) ) ) {
      __tmp.setFuncionari(FuncionariJPA.copyJPA(__jpa.getFuncionari(), __alreadyCopied,"AutoritzacioJPA"));
    }

    return __tmp;
  }




}

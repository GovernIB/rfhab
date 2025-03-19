
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


@Entity(name = "HistoricJPA")
@Table(name = "rfh_historic" , indexes = { 
        @Index(name="rfh_historic_pk_i", columnList = "historicid"),
        @Index(name="rfh_historic_funcionariid_fk_i", columnList = "funcionariid"),
        @Index(name="rfh_historic_usuariid_fk_i", columnList = "usuariid")})
@SequenceGenerator(name="HISTORIC_SEQ", sequenceName="rfh_historic_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class HistoricJPA implements Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HISTORIC_SEQ")
    @Column(name="historicid",nullable = false,length = 19)
    long historicID;

    @Column(name="funcionariid",nullable = false,length = 19)
    long funcionariID;

    @Column(name="numerocai",nullable = false,length = 50)
    java.lang.String numeroCai;

    @Column(name="observacions",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String observacions;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="usuariid",length = 19)
    java.lang.Long usuariID;



  /** Constructor Buit */
  public HistoricJPA() {
  }

  /** Constructor amb tots els camps  */
  public HistoricJPA(long historicID , long funcionariID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.historicID=historicID;
    this.funcionariID=funcionariID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public HistoricJPA(long funcionariID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.funcionariID=funcionariID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public HistoricJPA(long historicID , long funcionariID , java.lang.String numeroCai , java.sql.Timestamp dataCreacio) {
    this.historicID=historicID;
    this.funcionariID=funcionariID;
    this.numeroCai=numeroCai;
    this.dataCreacio=dataCreacio;
}
  public HistoricJPA(Historic __bean) {
    this.setHistoricID(__bean.getHistoricID());
    this.setFuncionariID(__bean.getFuncionariID());
    this.setNumeroCai(__bean.getNumeroCai());
    this.setObservacions(__bean.getObservacions());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getHistoricID() {
		return(historicID);
	};
	public void setHistoricID(long _historicID_) {
		this.historicID = _historicID_;
	};

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};

	public java.lang.String getNumeroCai() {
		return(numeroCai);
	};
	public void setNumeroCai(java.lang.String _numeroCai_) {
		this.numeroCai = _numeroCai_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Historic) {
            Historic __instance = (Historic)__obj;
            __result = true;
            __result = __result && (this.getHistoricID() == __instance.getHistoricID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getHistoricID())).hashCode();
    }

// IMP Field:funcionariid | Table: rfh_funcionari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionariid", referencedColumnName ="funcionariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_historic_funcionari_f_fk"))
    private FuncionariJPA funcionari;

    public FuncionariJPA getFuncionari() {
    return this.funcionari;
  }

    public  void setFuncionari(FuncionariJPA funcionari) {
    this.funcionari = funcionari;
  }

// IMP Field:usuariid | Table: rfh_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_historic_usuari_usuid_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }


 // ---------------  STATIC METHODS ------------------
  public static HistoricJPA toJPA(Historic __bean) {
    if (__bean == null) { return null;}
    HistoricJPA __tmp = new HistoricJPA();
    __tmp.setHistoricID(__bean.getHistoricID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setNumeroCai(__bean.getNumeroCai());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}


  public static HistoricJPA copyJPA(HistoricJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<HistoricJPA> copyJPA(java.util.Set<HistoricJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<HistoricJPA> __tmpSet = (java.util.Set<HistoricJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<HistoricJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (HistoricJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static HistoricJPA copyJPA(HistoricJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    HistoricJPA __tmp = (HistoricJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"FuncionariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.funcionari) || org.hibernate.Hibernate.isInitialized(__jpa.getFuncionari()) ) ) {
      __tmp.setFuncionari(FuncionariJPA.copyJPA(__jpa.getFuncionari(), __alreadyCopied,"HistoricJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"HistoricJPA"));
    }

    return __tmp;
  }




}

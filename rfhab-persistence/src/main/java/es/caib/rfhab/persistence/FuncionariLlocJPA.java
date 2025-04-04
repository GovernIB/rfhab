
package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "FuncionariLlocJPA")
@Table(name = "rfh_funcionarilloc" , indexes = { 
        @Index(name="rfh_funcionarientitat_pk_i", columnList = "funcionarillocid"),
        @Index(name="rfh_funcionarilloc_llocid_fk_i", columnList = "llocid"),
        @Index(name="rfh_funlloc_funcionariid_fk_i", columnList = "funcionariid")},
           uniqueConstraints = {
            @UniqueConstraint(name="rfh_funlloc_multiple_uk", columnNames={"llocid","funcionariid"}) } )
@SequenceGenerator(name="FUNCIONARILLOC_SEQ", sequenceName="rfh_funcionarilloc_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FuncionariLlocJPA implements FuncionariLloc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FUNCIONARILLOC_SEQ")
    @Column(name="funcionarillocid",nullable = false,length = 19)
    long funcionarillocID;

    @Column(name="llocid",nullable = false,length = 19)
    long llocID;

    @Column(name="funcionariid",nullable = false,length = 19)
    long funcionariID;

    @Column(name="datainici",length = 13)
    java.sql.Date dataInici;

    @Column(name="datafi",length = 13)
    java.sql.Date dataFi;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="usuariid",length = 19)
    java.lang.Long usuariID;



  /** Constructor Buit */
  public FuncionariLlocJPA() {
  }

  /** Constructor amb tots els camps  */
  public FuncionariLlocJPA(long funcionarillocID , long llocID , long funcionariID , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.funcionarillocID=funcionarillocID;
    this.llocID=llocID;
    this.funcionariID=funcionariID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public FuncionariLlocJPA(long llocID , long funcionariID , java.sql.Date dataInici , java.sql.Date dataFi , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.llocID=llocID;
    this.funcionariID=funcionariID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public FuncionariLlocJPA(long funcionarillocID , long llocID , long funcionariID , java.sql.Timestamp dataCreacio) {
    this.funcionarillocID=funcionarillocID;
    this.llocID=llocID;
    this.funcionariID=funcionariID;
    this.dataCreacio=dataCreacio;
}
  public FuncionariLlocJPA(FuncionariLloc __bean) {
    this.setFuncionarillocID(__bean.getFuncionarillocID());
    this.setLlocID(__bean.getLlocID());
    this.setFuncionariID(__bean.getFuncionariID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getFuncionarillocID() {
		return(funcionarillocID);
	};
	public void setFuncionarillocID(long _funcionarillocID_) {
		this.funcionarillocID = _funcionarillocID_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
	};

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
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

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof FuncionariLloc) {
            FuncionariLloc __instance = (FuncionariLloc)__obj;
            __result = true;
            __result = __result && (this.getFuncionarillocID() == __instance.getFuncionarillocID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:llocid | Table: rfh_lloc | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "llocid", referencedColumnName ="llocID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_funlloc_lloc_llocid_fk"))
    private LlocJPA lloc;

    public LlocJPA getLloc() {
    return this.lloc;
  }

    public  void setLloc(LlocJPA lloc) {
    this.lloc = lloc;
  }

// IMP Field:funcionariid | Table: rfh_funcionari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionariid", referencedColumnName ="funcionariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_funlloc_funcionari_f_fk"))
    private FuncionariJPA funcionari;

    public FuncionariJPA getFuncionari() {
    return this.funcionari;
  }

    public  void setFuncionari(FuncionariJPA funcionari) {
    this.funcionari = funcionari;
  }


 // ---------------  STATIC METHODS ------------------
  public static FuncionariLlocJPA toJPA(FuncionariLloc __bean) {
    if (__bean == null) { return null;}
    FuncionariLlocJPA __tmp = new FuncionariLlocJPA();
    __tmp.setFuncionarillocID(__bean.getFuncionarillocID());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}


  public static FuncionariLlocJPA copyJPA(FuncionariLlocJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FuncionariLlocJPA> copyJPA(java.util.Set<FuncionariLlocJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FuncionariLlocJPA> __tmpSet = (java.util.Set<FuncionariLlocJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FuncionariLlocJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FuncionariLlocJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FuncionariLlocJPA copyJPA(FuncionariLlocJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FuncionariLlocJPA __tmp = (FuncionariLlocJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"LlocJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.lloc) || org.hibernate.Hibernate.isInitialized(__jpa.getLloc()) ) ) {
      __tmp.setLloc(LlocJPA.copyJPA(__jpa.getLloc(), __alreadyCopied,"FuncionariLlocJPA"));
    }
    if(!"FuncionariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.funcionari) || org.hibernate.Hibernate.isInitialized(__jpa.getFuncionari()) ) ) {
      __tmp.setFuncionari(FuncionariJPA.copyJPA(__jpa.getFuncionari(), __alreadyCopied,"FuncionariLlocJPA"));
    }

    return __tmp;
  }




}

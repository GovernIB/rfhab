
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


@Entity(name = "HistoricLlocJPA")
@Table(name = "rfh_historiclloc" , indexes = { 
        @Index(name="rfh_historiclloc_pk_i", columnList = "historicllocid"),
        @Index(name="rfh_historiclloc_llocid_fk_i", columnList = "llocid"),
        @Index(name="rfh_historiclloc_usuariid_fk_i", columnList = "usuariid")})
@SequenceGenerator(name="HISTORICLLOC_SEQ", sequenceName="rfh_historiclloc_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class HistoricLlocJPA implements HistoricLloc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HISTORICLLOC_SEQ")
    @Column(name="historicllocid",nullable = false,length = 19)
    long historicllocID;

    @Column(name="llocid",nullable = false,length = 19)
    long llocID;

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
  public HistoricLlocJPA() {
  }

  /** Constructor amb tots els camps  */
  public HistoricLlocJPA(long historicllocID , long llocID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.historicllocID=historicllocID;
    this.llocID=llocID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public HistoricLlocJPA(long llocID , java.lang.String numeroCai , java.lang.String observacions , java.sql.Timestamp dataCreacio , java.lang.Long usuariID) {
    this.llocID=llocID;
    this.numeroCai=numeroCai;
    this.observacions=observacions;
    this.dataCreacio=dataCreacio;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public HistoricLlocJPA(long historicllocID , long llocID , java.lang.String numeroCai , java.sql.Timestamp dataCreacio) {
    this.historicllocID=historicllocID;
    this.llocID=llocID;
    this.numeroCai=numeroCai;
    this.dataCreacio=dataCreacio;
}
  public HistoricLlocJPA(HistoricLloc __bean) {
    this.setHistoricllocID(__bean.getHistoricllocID());
    this.setLlocID(__bean.getLlocID());
    this.setNumeroCai(__bean.getNumeroCai());
    this.setObservacions(__bean.getObservacions());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getHistoricllocID() {
		return(historicllocID);
	};
	public void setHistoricllocID(long _historicllocID_) {
		this.historicllocID = _historicllocID_;
	};

	public long getLlocID() {
		return(llocID);
	};
	public void setLlocID(long _llocID_) {
		this.llocID = _llocID_;
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
        if (__obj != null && __obj instanceof HistoricLloc) {
            HistoricLloc __instance = (HistoricLloc)__obj;
            __result = true;
            __result = __result && (this.getHistoricllocID() == __instance.getHistoricllocID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getHistoricllocID())).hashCode();
    }

// IMP Field:llocid | Table: rfh_lloc | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "llocid", referencedColumnName ="llocID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_histolloc_lloc_llocid_fk"))
    private LlocJPA lloc;

    public LlocJPA getLloc() {
    return this.lloc;
  }

    public  void setLloc(LlocJPA lloc) {
    this.lloc = lloc;
  }

// IMP Field:usuariid | Table: rfh_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_histolloc_usuari_id_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }


 // ---------------  STATIC METHODS ------------------
  public static HistoricLlocJPA toJPA(HistoricLloc __bean) {
    if (__bean == null) { return null;}
    HistoricLlocJPA __tmp = new HistoricLlocJPA();
    __tmp.setHistoricllocID(__bean.getHistoricllocID());
    __tmp.setLlocID(__bean.getLlocID());
    __tmp.setNumeroCai(__bean.getNumeroCai());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}


  public static HistoricLlocJPA copyJPA(HistoricLlocJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<HistoricLlocJPA> copyJPA(java.util.Set<HistoricLlocJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<HistoricLlocJPA> __tmpSet = (java.util.Set<HistoricLlocJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<HistoricLlocJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (HistoricLlocJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static HistoricLlocJPA copyJPA(HistoricLlocJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    HistoricLlocJPA __tmp = (HistoricLlocJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"LlocJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.lloc) || org.hibernate.Hibernate.isInitialized(__jpa.getLloc()) ) ) {
      __tmp.setLloc(LlocJPA.copyJPA(__jpa.getLloc(), __alreadyCopied,"HistoricLlocJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"HistoricLlocJPA"));
    }

    return __tmp;
  }




}

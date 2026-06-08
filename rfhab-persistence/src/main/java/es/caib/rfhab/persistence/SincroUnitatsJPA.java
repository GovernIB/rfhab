
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


@Entity(name = "SincroUnitatsJPA")
@Table(name = "rfh_sincrounitats" , indexes = { 
        @Index(name="rfh_sincrounitats_pk_i", columnList = "sincrounitatsid"),
        @Index(name="rfh_sncunitats_usuariid_fk_i", columnList = "usuariid")})
@SequenceGenerator(name="SINCROUNITATS_SEQ", sequenceName="rfh_sincrounitats_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class SincroUnitatsJPA implements SincroUnitats {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SINCROUNITATS_SEQ")
    @Column(name="sincrounitatsid",nullable = false,length = 19)
    long sincrounitatsId;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="datadarrerasincro",length = 29,precision = 6)
    java.sql.Timestamp dataDarreraSincro;

    @Column(name="dataprimerasincro",length = 29,precision = 6)
    java.sql.Timestamp dataPrimeraSincro;

    @Column(name="codientitat",nullable = false,length = 50)
    java.lang.String codiEntitat;

    @Column(name="observacions",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String observacions;

    @Column(name="usuariid",nullable = false,length = 19)
    java.lang.Long usuariId;



  /** Constructor Buit */
  public SincroUnitatsJPA() {
  }

  /** Constructor amb tots els camps  */
  public SincroUnitatsJPA(long sincrounitatsId , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataDarreraSincro , java.sql.Timestamp dataPrimeraSincro , java.lang.String codiEntitat , java.lang.String observacions , java.lang.Long usuariId) {
    this.sincrounitatsId=sincrounitatsId;
    this.dataCreacio=dataCreacio;
    this.dataDarreraSincro=dataDarreraSincro;
    this.dataPrimeraSincro=dataPrimeraSincro;
    this.codiEntitat=codiEntitat;
    this.observacions=observacions;
    this.usuariId=usuariId;
}
  /** Constructor sense valors autoincrementals */
  public SincroUnitatsJPA(java.sql.Timestamp dataCreacio , java.sql.Timestamp dataDarreraSincro , java.sql.Timestamp dataPrimeraSincro , java.lang.String codiEntitat , java.lang.String observacions , java.lang.Long usuariId) {
    this.dataCreacio=dataCreacio;
    this.dataDarreraSincro=dataDarreraSincro;
    this.dataPrimeraSincro=dataPrimeraSincro;
    this.codiEntitat=codiEntitat;
    this.observacions=observacions;
    this.usuariId=usuariId;
}
  /** Constructor dels valors Not Null */
  public SincroUnitatsJPA(long sincrounitatsId , java.sql.Timestamp dataCreacio , java.lang.String codiEntitat , java.lang.Long usuariId) {
    this.sincrounitatsId=sincrounitatsId;
    this.dataCreacio=dataCreacio;
    this.codiEntitat=codiEntitat;
    this.usuariId=usuariId;
}
  public SincroUnitatsJPA(SincroUnitats __bean) {
    this.setSincrounitatsId(__bean.getSincrounitatsId());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataDarreraSincro(__bean.getDataDarreraSincro());
    this.setDataPrimeraSincro(__bean.getDataPrimeraSincro());
    this.setCodiEntitat(__bean.getCodiEntitat());
    this.setObservacions(__bean.getObservacions());
    this.setUsuariId(__bean.getUsuariId());
	}

	public long getSincrounitatsId() {
		return(sincrounitatsId);
	};
	public void setSincrounitatsId(long _sincrounitatsId_) {
		this.sincrounitatsId = _sincrounitatsId_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.sql.Timestamp getDataDarreraSincro() {
		return(dataDarreraSincro);
	};
	public void setDataDarreraSincro(java.sql.Timestamp _dataDarreraSincro_) {
		this.dataDarreraSincro = _dataDarreraSincro_;
	};

	public java.sql.Timestamp getDataPrimeraSincro() {
		return(dataPrimeraSincro);
	};
	public void setDataPrimeraSincro(java.sql.Timestamp _dataPrimeraSincro_) {
		this.dataPrimeraSincro = _dataPrimeraSincro_;
	};

	public java.lang.String getCodiEntitat() {
		return(codiEntitat);
	};
	public void setCodiEntitat(java.lang.String _codiEntitat_) {
		this.codiEntitat = _codiEntitat_;
	};

	public java.lang.String getObservacions() {
		return(observacions);
	};
	public void setObservacions(java.lang.String _observacions_) {
		this.observacions = _observacions_;
	};

	public java.lang.Long getUsuariId() {
		return(usuariId);
	};
	public void setUsuariId(java.lang.Long _usuariId_) {
		this.usuariId = _usuariId_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof SincroUnitats) {
            SincroUnitats __instance = (SincroUnitats)__obj;
            __result = true;
            __result = __result && (this.getSincrounitatsId() == __instance.getSincrounitatsId()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:usuariid | Table: rfh_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_sncunitats_usuari_usuid_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }


 // ---------------  STATIC METHODS ------------------
  public static SincroUnitatsJPA toJPA(SincroUnitats __bean) {
    if (__bean == null) { return null;}
    SincroUnitatsJPA __tmp = new SincroUnitatsJPA();
    __tmp.setSincrounitatsId(__bean.getSincrounitatsId());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataDarreraSincro(__bean.getDataDarreraSincro());
    __tmp.setDataPrimeraSincro(__bean.getDataPrimeraSincro());
    __tmp.setCodiEntitat(__bean.getCodiEntitat());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setUsuariId(__bean.getUsuariId());
		return __tmp;
	}


  public static SincroUnitatsJPA copyJPA(SincroUnitatsJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<SincroUnitatsJPA> copyJPA(java.util.Set<SincroUnitatsJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<SincroUnitatsJPA> __tmpSet = (java.util.Set<SincroUnitatsJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<SincroUnitatsJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (SincroUnitatsJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static SincroUnitatsJPA copyJPA(SincroUnitatsJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    SincroUnitatsJPA __tmp = (SincroUnitatsJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"SincroUnitatsJPA"));
    }

    return __tmp;
  }




}

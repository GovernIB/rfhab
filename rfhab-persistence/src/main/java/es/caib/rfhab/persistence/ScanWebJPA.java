
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


@Entity(name = "ScanWebJPA")
@Table(name = "rfh_digitalib" , indexes = { 
        @Index(name="rfh_digitalib_pk_i", columnList = "digitalid"),
        @Index(name="rfh_digitalib_fitxerid_fk_i", columnList = "fitxerid"),
        @Index(name="rfh_digitalib_usuariid_fk_i", columnList = "usuariid")})
@SequenceGenerator(name="SCANWEB_SEQ", sequenceName="rfh_digitalib_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class ScanWebJPA implements ScanWeb {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SCANWEB_SEQ")
    @Column(name="digitalid",nullable = false,length = 19)
    long digitalID;

    @Column(name="transactionid",length = 19)
    long transactionID;

    @Column(name="transactionwebid",length = 255)
    java.lang.String transactionWebID;

    @Column(name="status",nullable = false,length = 19)
    long status;

    @Column(name="fitxerid",nullable = false,length = 19)
    long fitxerID;

    @Column(name="fileinfo",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String fileInfo;

    @Column(name="signedfileinfo",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String signedFileInfo;

    @Column(name="metadades",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String metadades;

    @Column(name="missatge",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String missatge;

    @Column(name="usuariid",nullable = false,length = 19)
    java.lang.Long usuariID;

    @Column(name="datacreacio",length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;



  /** Constructor Buit */
  public ScanWebJPA() {
  }

  /** Constructor amb tots els camps  */
  public ScanWebJPA(long digitalID , long transactionID , java.lang.String transactionWebID , long status , long fitxerID , java.lang.String fileInfo , java.lang.String signedFileInfo , java.lang.String metadades , java.lang.String missatge , java.lang.Long usuariID , java.sql.Timestamp dataCreacio , long entitatID) {
    this.digitalID=digitalID;
    this.transactionID=transactionID;
    this.transactionWebID=transactionWebID;
    this.status=status;
    this.fitxerID=fitxerID;
    this.fileInfo=fileInfo;
    this.signedFileInfo=signedFileInfo;
    this.metadades=metadades;
    this.missatge=missatge;
    this.usuariID=usuariID;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public ScanWebJPA(long transactionID , java.lang.String transactionWebID , long status , long fitxerID , java.lang.String fileInfo , java.lang.String signedFileInfo , java.lang.String metadades , java.lang.String missatge , java.lang.Long usuariID , java.sql.Timestamp dataCreacio , long entitatID) {
    this.transactionID=transactionID;
    this.transactionWebID=transactionWebID;
    this.status=status;
    this.fitxerID=fitxerID;
    this.fileInfo=fileInfo;
    this.signedFileInfo=signedFileInfo;
    this.metadades=metadades;
    this.missatge=missatge;
    this.usuariID=usuariID;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public ScanWebJPA(long digitalID , long status , long fitxerID , java.lang.Long usuariID , long entitatID) {
    this.digitalID=digitalID;
    this.status=status;
    this.fitxerID=fitxerID;
    this.usuariID=usuariID;
    this.entitatID=entitatID;
}
  public ScanWebJPA(ScanWeb __bean) {
    this.setDigitalID(__bean.getDigitalID());
    this.setTransactionID(__bean.getTransactionID());
    this.setTransactionWebID(__bean.getTransactionWebID());
    this.setStatus(__bean.getStatus());
    this.setFitxerID(__bean.getFitxerID());
    this.setFileInfo(__bean.getFileInfo());
    this.setSignedFileInfo(__bean.getSignedFileInfo());
    this.setMetadades(__bean.getMetadades());
    this.setMissatge(__bean.getMissatge());
    this.setUsuariID(__bean.getUsuariID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setEntitatID(__bean.getEntitatID());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
	}

	public long getDigitalID() {
		return(digitalID);
	};
	public void setDigitalID(long _digitalID_) {
		this.digitalID = _digitalID_;
	};

	public long getTransactionID() {
		return(transactionID);
	};
	public void setTransactionID(long _transactionID_) {
		this.transactionID = _transactionID_;
	};

	public java.lang.String getTransactionWebID() {
		return(transactionWebID);
	};
	public void setTransactionWebID(java.lang.String _transactionWebID_) {
		this.transactionWebID = _transactionWebID_;
	};

	public long getStatus() {
		return(status);
	};
	public void setStatus(long _status_) {
		this.status = _status_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.String getFileInfo() {
		return(fileInfo);
	};
	public void setFileInfo(java.lang.String _fileInfo_) {
		this.fileInfo = _fileInfo_;
	};

	public java.lang.String getSignedFileInfo() {
		return(signedFileInfo);
	};
	public void setSignedFileInfo(java.lang.String _signedFileInfo_) {
		this.signedFileInfo = _signedFileInfo_;
	};

	public java.lang.String getMetadades() {
		return(metadades);
	};
	public void setMetadades(java.lang.String _metadades_) {
		this.metadades = _metadades_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof ScanWeb) {
            ScanWeb __instance = (ScanWeb)__obj;
            __result = true;
            __result = __result && (this.getDigitalID() == __instance.getDigitalID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getDigitalID())).hashCode();
    }

// IMP Field:fitxerid | Table: rfh_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_scanweb_fitxer_fitxerid_fk"))
    private FitxerJPA fitxer;

    public FitxerJPA getFitxer() {
    return this.fitxer;
  }

    public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }

// IMP Field:usuariid | Table: rfh_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_scanweb_usuari_usuariid_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }


 // ---------------  STATIC METHODS ------------------
  public static ScanWebJPA toJPA(ScanWeb __bean) {
    if (__bean == null) { return null;}
    ScanWebJPA __tmp = new ScanWebJPA();
    __tmp.setDigitalID(__bean.getDigitalID());
    __tmp.setTransactionID(__bean.getTransactionID());
    __tmp.setTransactionWebID(__bean.getTransactionWebID());
    __tmp.setStatus(__bean.getStatus());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setFileInfo(__bean.getFileInfo());
    __tmp.setSignedFileInfo(__bean.getSignedFileInfo());
    __tmp.setMetadades(__bean.getMetadades());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setEntitatID(__bean.getEntitatID());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
		return __tmp;
	}


  public static ScanWebJPA copyJPA(ScanWebJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ScanWebJPA> copyJPA(java.util.Set<ScanWebJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ScanWebJPA> __tmpSet = (java.util.Set<ScanWebJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ScanWebJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ScanWebJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ScanWebJPA copyJPA(ScanWebJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ScanWebJPA __tmp = (ScanWebJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"ScanWebJPA"));
    }

    return __tmp;
  }




}


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


@Entity(name = "UsuariJPA")
@Table(name = "rfh_usuari" , indexes = { 
        @Index(name="rfh_usuari_pk_i", columnList = "usuariid"),
        @Index(name="rfh_usuari_idiomaid_fk_i", columnList = "idiomaid"),
        @Index(name="rfh_usuari_darreraentitat_fk_i", columnList = "darreraentitat")})
@SequenceGenerator(name="USUARI_SEQ", sequenceName="rfh_usuari_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariJPA implements Usuari {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USUARI_SEQ")
    @Column(name="usuariid",nullable = false,length = 19)
    long usuariID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="llinatge1",nullable = false,length = 255)
    java.lang.String llinatge1;

    @Column(name="llinatge2",length = 255)
    java.lang.String llinatge2;

    @Column(name="nif",nullable = false,length = 50)
    java.lang.String nif;

    @Column(name="username",unique = true,length = 255)
    java.lang.String username;

    @Column(name="correu",nullable = false,length = 255)
    java.lang.String correu;

    @Column(name="idiomaid",nullable = false,length = 5)
    java.lang.String idiomaID;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="darreraentitat",length = 19)
    java.lang.Long darreraEntitat;

    @Column(name="databaixa",length = 29,precision = 6)
    java.sql.Timestamp dataBaixa;



  /** Constructor Buit */
  public UsuariJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariJPA(long usuariID , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String username , java.lang.String correu , java.lang.String idiomaID , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.Long darreraEntitat , java.sql.Timestamp dataBaixa) {
    this.usuariID=usuariID;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.username=username;
    this.correu=correu;
    this.idiomaID=idiomaID;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.darreraEntitat=darreraEntitat;
    this.dataBaixa=dataBaixa;
}
  /** Constructor sense valors autoincrementals */
  public UsuariJPA(java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String username , java.lang.String correu , java.lang.String idiomaID , boolean actiu , java.sql.Timestamp dataCreacio , java.lang.Long darreraEntitat , java.sql.Timestamp dataBaixa) {
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.username=username;
    this.correu=correu;
    this.idiomaID=idiomaID;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
    this.darreraEntitat=darreraEntitat;
    this.dataBaixa=dataBaixa;
}
  /** Constructor dels valors Not Null */
  public UsuariJPA(long usuariID , java.lang.String nom , java.lang.String llinatge1 , java.lang.String nif , java.lang.String correu , java.lang.String idiomaID , boolean actiu , java.sql.Timestamp dataCreacio) {
    this.usuariID=usuariID;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.nif=nif;
    this.correu=correu;
    this.idiomaID=idiomaID;
    this.actiu=actiu;
    this.dataCreacio=dataCreacio;
}
  public UsuariJPA(Usuari __bean) {
    this.setUsuariID(__bean.getUsuariID());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setNif(__bean.getNif());
    this.setUsername(__bean.getUsername());
    this.setCorreu(__bean.getCorreu());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setActiu(__bean.isActiu());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDarreraEntitat(__bean.getDarreraEntitat());
    this.setDataBaixa(__bean.getDataBaixa());
	}

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatge1() {
		return(llinatge1);
	};
	public void setLlinatge1(java.lang.String _llinatge1_) {
		this.llinatge1 = _llinatge1_;
	};

	public java.lang.String getLlinatge2() {
		return(llinatge2);
	};
	public void setLlinatge2(java.lang.String _llinatge2_) {
		this.llinatge2 = _llinatge2_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getCorreu() {
		return(correu);
	};
	public void setCorreu(java.lang.String _correu_) {
		this.correu = _correu_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.Long getDarreraEntitat() {
		return(darreraEntitat);
	};
	public void setDarreraEntitat(java.lang.Long _darreraEntitat_) {
		this.darreraEntitat = _darreraEntitat_;
	};

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Usuari) {
            Usuari __instance = (Usuari)__obj;
            __result = true;
            __result = __result && (this.getUsuariID() == __instance.getUsuariID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:usuariid | Table: rfh_digitalib | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<ScanWebJPA> scanWebs = new HashSet<ScanWebJPA>(0);
    public  Set<ScanWebJPA> getScanWebs() {
    return this.scanWebs;
  }

    public void setScanWebs(Set<ScanWebJPA> scanWebs) {
      this.scanWebs = scanWebs;
    }


// EXP  Field:usuariid | Table: rfh_funcionarilloc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<FuncionariLlocJPA> funcionariLlocs = new HashSet<FuncionariLlocJPA>(0);
    public  Set<FuncionariLlocJPA> getFuncionariLlocs() {
    return this.funcionariLlocs;
  }

    public void setFuncionariLlocs(Set<FuncionariLlocJPA> funcionariLlocs) {
      this.funcionariLlocs = funcionariLlocs;
    }


// EXP  Field:usuariid | Table: rfh_historic | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<HistoricJPA> historics = new HashSet<HistoricJPA>(0);
    public  Set<HistoricJPA> getHistorics() {
    return this.historics;
  }

    public void setHistorics(Set<HistoricJPA> historics) {
      this.historics = historics;
    }


// EXP  Field:usuariid | Table: rfh_historiclloc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<HistoricLlocJPA> historicLlocs = new HashSet<HistoricLlocJPA>(0);
    public  Set<HistoricLlocJPA> getHistoricLlocs() {
    return this.historicLlocs;
  }

    public void setHistoricLlocs(Set<HistoricLlocJPA> historicLlocs) {
      this.historicLlocs = historicLlocs;
    }


// EXP  Field:usuariid | Table: rfh_sincrounitats | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<SincroUnitatsJPA> sincroUnitatss = new HashSet<SincroUnitatsJPA>(0);
    public  Set<SincroUnitatsJPA> getSincroUnitatss() {
    return this.sincroUnitatss;
  }

    public void setSincroUnitatss(Set<SincroUnitatsJPA> sincroUnitatss) {
      this.sincroUnitatss = sincroUnitatss;
    }


// EXP  Field:usuariid | Table: rfh_usuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
    public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

    public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
      this.usuariEntitats = usuariEntitats;
    }


// IMP Field:idiomaid | Table: rfh_idioma | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_usuari_idioma_idiomaid_fk"))
    private IdiomaJPA idioma;

    public IdiomaJPA getIdioma() {
    return this.idioma;
  }

    public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:entitatid | Table: rfh_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "darreraentitat", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_usuari_entitat_last_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariJPA toJPA(Usuari __bean) {
    if (__bean == null) { return null;}
    UsuariJPA __tmp = new UsuariJPA();
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setNif(__bean.getNif());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setCorreu(__bean.getCorreu());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDarreraEntitat(__bean.getDarreraEntitat());
    __tmp.setDataBaixa(__bean.getDataBaixa());
		return __tmp;
	}


  public static UsuariJPA copyJPA(UsuariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariJPA> copyJPA(java.util.Set<UsuariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariJPA> __tmpSet = (java.util.Set<UsuariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariJPA copyJPA(UsuariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariJPA __tmp = (UsuariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"FuncionariLlocJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.funcionariLlocs) || org.hibernate.Hibernate.isInitialized(__jpa.getFuncionariLlocs())) ) {
      __tmp.setFuncionariLlocs(FuncionariLlocJPA.copyJPA(__jpa.getFuncionariLlocs(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"SincroUnitatsJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.sincroUnitatss) || org.hibernate.Hibernate.isInitialized(__jpa.getSincroUnitatss())) ) {
      __tmp.setSincroUnitatss(SincroUnitatsJPA.copyJPA(__jpa.getSincroUnitatss(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"ScanWebJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.scanWebs) || org.hibernate.Hibernate.isInitialized(__jpa.getScanWebs())) ) {
      __tmp.setScanWebs(ScanWebJPA.copyJPA(__jpa.getScanWebs(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"HistoricLlocJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.historicLlocs) || org.hibernate.Hibernate.isInitialized(__jpa.getHistoricLlocs())) ) {
      __tmp.setHistoricLlocs(HistoricLlocJPA.copyJPA(__jpa.getHistoricLlocs(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"HistoricJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.historics) || org.hibernate.Hibernate.isInitialized(__jpa.getHistorics())) ) {
      __tmp.setHistorics(HistoricJPA.copyJPA(__jpa.getHistorics(), __alreadyCopied,"UsuariJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariJPA"));
    }

    return __tmp;
  }




}

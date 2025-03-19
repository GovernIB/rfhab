
package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "FuncionariJPA")
@Table(name = "rfh_funcionari" , indexes = { 
        @Index(name="rfh_funcionari_pk_i", columnList = "funcionariid")})
@SequenceGenerator(name="FUNCIONARI_SEQ", sequenceName="rfh_funcionari_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FuncionariJPA implements Funcionari {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FUNCIONARI_SEQ")
    @Column(name="funcionariid",nullable = false,length = 19)
    long funcionariID;

    @Column(name="numero",nullable = false,length = 10)
    int numero;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="llinatge1",nullable = false,length = 255)
    java.lang.String llinatge1;

    @Column(name="llinatge2",length = 255)
    java.lang.String llinatge2;

    @Column(name="tipusidentificador",nullable = false,length = 10)
    int tipusIdentificador;

    @Column(name="identificador",nullable = false,length = 50)
    java.lang.String identificador;

    @Column(name="usuari",nullable = false,length = 50)
    java.lang.String usuari;

    @Column(name="correu",nullable = false,length = 255)
    java.lang.String correu;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="observacions",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String observacions;

    @Column(name="databaixa",length = 29,precision = 6)
    java.sql.Timestamp dataBaixa;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;



  /** Constructor Buit */
  public FuncionariJPA() {
  }

  /** Constructor amb tots els camps  */
  public FuncionariJPA(long funcionariID , int numero , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , int tipusIdentificador , java.lang.String identificador , java.lang.String usuari , java.lang.String correu , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.sql.Timestamp dataBaixa , long entitatID) {
    this.funcionariID=funcionariID;
    this.numero=numero;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.tipusIdentificador=tipusIdentificador;
    this.identificador=identificador;
    this.usuari=usuari;
    this.correu=correu;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.dataBaixa=dataBaixa;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public FuncionariJPA(int numero , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , int tipusIdentificador , java.lang.String identificador , java.lang.String usuari , java.lang.String correu , java.sql.Timestamp dataCreacio , java.lang.String observacions , java.sql.Timestamp dataBaixa , long entitatID) {
    this.numero=numero;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.tipusIdentificador=tipusIdentificador;
    this.identificador=identificador;
    this.usuari=usuari;
    this.correu=correu;
    this.dataCreacio=dataCreacio;
    this.observacions=observacions;
    this.dataBaixa=dataBaixa;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public FuncionariJPA(long funcionariID , int numero , java.lang.String nom , java.lang.String llinatge1 , int tipusIdentificador , java.lang.String identificador , java.lang.String usuari , java.lang.String correu , java.sql.Timestamp dataCreacio , long entitatID) {
    this.funcionariID=funcionariID;
    this.numero=numero;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.tipusIdentificador=tipusIdentificador;
    this.identificador=identificador;
    this.usuari=usuari;
    this.correu=correu;
    this.dataCreacio=dataCreacio;
    this.entitatID=entitatID;
}
  public FuncionariJPA(Funcionari __bean) {
    this.setFuncionariID(__bean.getFuncionariID());
    this.setNumero(__bean.getNumero());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setTipusIdentificador(__bean.getTipusIdentificador());
    this.setIdentificador(__bean.getIdentificador());
    this.setUsuari(__bean.getUsuari());
    this.setCorreu(__bean.getCorreu());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setObservacions(__bean.getObservacions());
    this.setDataBaixa(__bean.getDataBaixa());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};

	public int getNumero() {
		return(numero);
	};
	public void setNumero(int _numero_) {
		this.numero = _numero_;
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

	public int getTipusIdentificador() {
		return(tipusIdentificador);
	};
	public void setTipusIdentificador(int _tipusIdentificador_) {
		this.tipusIdentificador = _tipusIdentificador_;
	};

	public java.lang.String getIdentificador() {
		return(identificador);
	};
	public void setIdentificador(java.lang.String _identificador_) {
		this.identificador = _identificador_;
	};

	public java.lang.String getUsuari() {
		return(usuari);
	};
	public void setUsuari(java.lang.String _usuari_) {
		this.usuari = _usuari_;
	};

	public java.lang.String getCorreu() {
		return(correu);
	};
	public void setCorreu(java.lang.String _correu_) {
		this.correu = _correu_;
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

	public java.sql.Timestamp getDataBaixa() {
		return(dataBaixa);
	};
	public void setDataBaixa(java.sql.Timestamp _dataBaixa_) {
		this.dataBaixa = _dataBaixa_;
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
        if (__obj != null && __obj instanceof Funcionari) {
            Funcionari __instance = (Funcionari)__obj;
            __result = true;
            __result = __result && (this.getFuncionariID() == __instance.getFuncionariID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getFuncionariID())).hashCode();
    }

// EXP  Field:funcionariid | Table: rfh_activitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionari")
    private Set<ActivitatJPA> activitats = new HashSet<ActivitatJPA>(0);
    public  Set<ActivitatJPA> getActivitats() {
    return this.activitats;
  }

    public void setActivitats(Set<ActivitatJPA> activitats) {
      this.activitats = activitats;
    }


// EXP  Field:funcionariid | Table: rfh_autoritzacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionari")
    private Set<AutoritzacioJPA> autoritzacios = new HashSet<AutoritzacioJPA>(0);
    public  Set<AutoritzacioJPA> getAutoritzacios() {
    return this.autoritzacios;
  }

    public void setAutoritzacios(Set<AutoritzacioJPA> autoritzacios) {
      this.autoritzacios = autoritzacios;
    }


// EXP  Field:funcionariid | Table: rfh_funcionarilloc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionari")
    private Set<FuncionariLlocJPA> funcionariLlocs = new HashSet<FuncionariLlocJPA>(0);
    public  Set<FuncionariLlocJPA> getFuncionariLlocs() {
    return this.funcionariLlocs;
  }

    public void setFuncionariLlocs(Set<FuncionariLlocJPA> funcionariLlocs) {
      this.funcionariLlocs = funcionariLlocs;
    }


// EXP  Field:funcionariid | Table: rfh_historic | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionari")
    private Set<HistoricJPA> historics = new HashSet<HistoricJPA>(0);
    public  Set<HistoricJPA> getHistorics() {
    return this.historics;
  }

    public void setHistorics(Set<HistoricJPA> historics) {
      this.historics = historics;
    }



 // ---------------  STATIC METHODS ------------------
  public static FuncionariJPA toJPA(Funcionari __bean) {
    if (__bean == null) { return null;}
    FuncionariJPA __tmp = new FuncionariJPA();
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setNumero(__bean.getNumero());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setTipusIdentificador(__bean.getTipusIdentificador());
    __tmp.setIdentificador(__bean.getIdentificador());
    __tmp.setUsuari(__bean.getUsuari());
    __tmp.setCorreu(__bean.getCorreu());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setObservacions(__bean.getObservacions());
    __tmp.setDataBaixa(__bean.getDataBaixa());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static FuncionariJPA copyJPA(FuncionariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FuncionariJPA> copyJPA(java.util.Set<FuncionariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FuncionariJPA> __tmpSet = (java.util.Set<FuncionariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FuncionariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FuncionariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FuncionariJPA copyJPA(FuncionariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FuncionariJPA __tmp = (FuncionariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"FuncionariLlocJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.funcionariLlocs) || org.hibernate.Hibernate.isInitialized(__jpa.getFuncionariLlocs())) ) {
      __tmp.setFuncionariLlocs(FuncionariLlocJPA.copyJPA(__jpa.getFuncionariLlocs(), __alreadyCopied,"FuncionariJPA"));
    }
    if(!"AutoritzacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.autoritzacios) || org.hibernate.Hibernate.isInitialized(__jpa.getAutoritzacios())) ) {
      __tmp.setAutoritzacios(AutoritzacioJPA.copyJPA(__jpa.getAutoritzacios(), __alreadyCopied,"FuncionariJPA"));
    }
    if(!"ActivitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.activitats) || org.hibernate.Hibernate.isInitialized(__jpa.getActivitats())) ) {
      __tmp.setActivitats(ActivitatJPA.copyJPA(__jpa.getActivitats(), __alreadyCopied,"FuncionariJPA"));
    }
    if(!"HistoricJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.historics) || org.hibernate.Hibernate.isInitialized(__jpa.getHistorics())) ) {
      __tmp.setHistorics(HistoricJPA.copyJPA(__jpa.getHistorics(), __alreadyCopied,"FuncionariJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}

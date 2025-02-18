
package es.caib.rfhab.persistence;
import es.caib.rfhab.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "FuncionariRolJPA")
@Table(name = "rfh_funcionarirol" , indexes = { 
        @Index(name="rfh_funcionarirol_pk_i", columnList = "funcionarirolid"),
        @Index(name="rfh_funrol_funcionariid_fk_i", columnList = "funcionariid"),
        @Index(name="rfh_funcionarirol_rolid_fk_i", columnList = "rolid")})
@SequenceGenerator(name="FUNCIONARIROL_SEQ", sequenceName="rfh_funcionarirol_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FuncionariRolJPA implements FuncionariRol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FUNCIONARIROL_SEQ")
    @Column(name="funcionarirolid",nullable = false,length = 19)
    long funcionariRolID;

    @Column(name="funcionariid",nullable = false,length = 19)
    long funcionariID;

    @Column(name="rolid",nullable = false,length = 19)
    long rolID;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;



  /** Constructor Buit */
  public FuncionariRolJPA() {
  }

  /** Constructor amb tots els camps  */
  public FuncionariRolJPA(long funcionariRolID , long funcionariID , long rolID , java.sql.Timestamp dataCreacio) {
    this.funcionariRolID=funcionariRolID;
    this.funcionariID=funcionariID;
    this.rolID=rolID;
    this.dataCreacio=dataCreacio;
}
  /** Constructor sense valors autoincrementals */
  public FuncionariRolJPA(long funcionariID , long rolID , java.sql.Timestamp dataCreacio) {
    this.funcionariID=funcionariID;
    this.rolID=rolID;
    this.dataCreacio=dataCreacio;
}
  public FuncionariRolJPA(FuncionariRol __bean) {
    this.setFuncionariRolID(__bean.getFuncionariRolID());
    this.setFuncionariID(__bean.getFuncionariID());
    this.setRolID(__bean.getRolID());
    this.setDataCreacio(__bean.getDataCreacio());
	}

	public long getFuncionariRolID() {
		return(funcionariRolID);
	};
	public void setFuncionariRolID(long _funcionariRolID_) {
		this.funcionariRolID = _funcionariRolID_;
	};

	public long getFuncionariID() {
		return(funcionariID);
	};
	public void setFuncionariID(long _funcionariID_) {
		this.funcionariID = _funcionariID_;
	};

	public long getRolID() {
		return(rolID);
	};
	public void setRolID(long _rolID_) {
		this.rolID = _rolID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof FuncionariRol) {
      FuncionariRol __instance = (FuncionariRol)__obj;
      __result = true;
      __result = __result && (this.getFuncionariRolID() == __instance.getFuncionariRolID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:funcionariid | Table: rfh_funcionari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionariid", referencedColumnName ="funcionariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_funrol_funcionari_funci_fk"))
    private FuncionariJPA funcionari;

    public FuncionariJPA getFuncionari() {
    return this.funcionari;
  }

    public  void setFuncionari(FuncionariJPA funcionari) {
    this.funcionari = funcionari;
  }

// IMP Field:rolid | Table: rfh_rol | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolid", referencedColumnName ="rolID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="rfh_funrol_rol_rolid_fk"))
    private RolJPA rol;

    public RolJPA getRol() {
    return this.rol;
  }

    public  void setRol(RolJPA rol) {
    this.rol = rol;
  }


 // ---------------  STATIC METHODS ------------------
  public static FuncionariRolJPA toJPA(FuncionariRol __bean) {
    if (__bean == null) { return null;}
    FuncionariRolJPA __tmp = new FuncionariRolJPA();
    __tmp.setFuncionariRolID(__bean.getFuncionariRolID());
    __tmp.setFuncionariID(__bean.getFuncionariID());
    __tmp.setRolID(__bean.getRolID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
		return __tmp;
	}


  public static FuncionariRolJPA copyJPA(FuncionariRolJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FuncionariRolJPA> copyJPA(java.util.Set<FuncionariRolJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FuncionariRolJPA> __tmpSet = (java.util.Set<FuncionariRolJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FuncionariRolJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FuncionariRolJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FuncionariRolJPA copyJPA(FuncionariRolJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FuncionariRolJPA __tmp = (FuncionariRolJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"FuncionariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.funcionari) || org.hibernate.Hibernate.isInitialized(__jpa.getFuncionari()) ) ) {
      __tmp.setFuncionari(FuncionariJPA.copyJPA(__jpa.getFuncionari(), __alreadyCopied,"FuncionariRolJPA"));
    }
    if(!"RolJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.rol) || org.hibernate.Hibernate.isInitialized(__jpa.getRol()) ) ) {
      __tmp.setRol(RolJPA.copyJPA(__jpa.getRol(), __alreadyCopied,"FuncionariRolJPA"));
    }

    return __tmp;
  }




}


package es.caib.rfhab.model.bean;

import es.caib.rfhab.model.entity.UsuariEntitat;


public class UsuariEntitatBean implements UsuariEntitat {



	long usuariEntitatID;// PK
	long entitatID;
	long usuariID;
	boolean actiu;


  /** Constructor Buit */
  public UsuariEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatBean(long usuariEntitatID , long entitatID , long usuariID , boolean actiu) {
    this.usuariEntitatID=usuariEntitatID;
    this.entitatID=entitatID;
    this.usuariID=usuariID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatBean(long entitatID , long usuariID , boolean actiu) {
    this.entitatID=entitatID;
    this.usuariID=usuariID;
    this.actiu=actiu;
}
  public UsuariEntitatBean(UsuariEntitat __bean) {
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setEntitatID(__bean.getEntitatID());
    this.setUsuariID(__bean.getUsuariID());
    this.setActiu(__bean.isActiu());
	}

	public long getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(long _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  // ======================================

  public static UsuariEntitatBean toBean(UsuariEntitat __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatBean __tmp = new UsuariEntitatBean();
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}



}

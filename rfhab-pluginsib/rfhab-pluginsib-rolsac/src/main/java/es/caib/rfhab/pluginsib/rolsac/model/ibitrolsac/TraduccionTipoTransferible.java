package es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac;

import java.io.Serializable;

public class TraduccionTipoTransferible extends AbstractTraduccion implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

package es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac;

/** 
 * Traduccion documento de la normativa.
 * @author slromero
 *
 */
public class TraduccionDocumentoNormativa implements Traduccion {

	/** Archivo. **/
	private Archivo archivo;
    /** Enlace. **/
    private String enlace;
    /** Titulo. **/
    private String titulo;
    /** Descripción. **/
    private String descripcion;
   
	/**
	 * @return the archivo
	 */
	public Archivo getArchivo() {
		return archivo;
	}
	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}
	/**
	 * @return the enlace
	 */
	public String getEnlace() {
		return enlace;
	}
	/**
	 * @param enlace the enlace to set
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
    
}

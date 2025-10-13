package es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac;

import java.util.HashMap;
import es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.EdificioTransferible;
import es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.TraduccionEdificioTransferible;



public class EdificioRemoto extends Edificio implements Remoto{
    
	private static final long serialVersionUID = 1L;

	private AdministracionRemota administracionRemota;
	private Long idExterno;

    public AdministracionRemota getAdministracionRemota() {
        return administracionRemota;
    }
    public void setAdministracionRemota(AdministracionRemota administracionRemota) {
        this.administracionRemota = administracionRemota;
    }
    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

	public void rellenar(final EdificioTransferible edificioTransferible){
		this.setIdExterno(edificioTransferible.getId());
		this.setCodigoPostal(edificioTransferible.getCodigoPostal());
		this.setDireccion(edificioTransferible.getDireccion());
		this.setEmail(edificioTransferible.getEmail());
		this.setPoblacion(edificioTransferible.getPoblacion());
		this.setTelefono(edificioTransferible.getTelefono());
		this.setFax(edificioTransferible.getFax());
		this.setLatitud(edificioTransferible.getLatitud());
		this.setLongitud(edificioTransferible.getLongitud());
		
		 //Relleno las traducciones
		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
        if (edificioTransferible.getTraducciones() != null) {
            for (final TraduccionEdificioTransferible traduccion : edificioTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionEdificio temp =  new TraduccionEdificio();
                    temp.setDescripcion(traduccion.getDescripcion());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                }
            }
        }
        this.setTraduccionMap(traducciones);
	}
	


	
}

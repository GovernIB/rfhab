package es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac;

import java.io.Serializable;

/**
 *  Clase que representa la información traducida a transferir de UnidadMateria(PORMAD)
 */
public class TraduccionUnidadMateriaTransferible extends AbstractTraduccion implements Serializable {

    private String urlUnidadMateria;

    public String getUrlUnidadMateria() {
        return urlUnidadMateria;
    }

    public void setUrlUnidadMateria(String urlUnidadMateria) {
        this.urlUnidadMateria = urlUnidadMateria;
    }
}

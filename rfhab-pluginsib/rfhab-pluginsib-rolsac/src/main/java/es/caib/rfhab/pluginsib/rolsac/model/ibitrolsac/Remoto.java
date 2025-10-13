package es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac;

/**
 * Interfaz para los objetos remotos.
 */
public interface Remoto {

   /* public String getParamName();

    public void setParamName(String paramName);

    public String getParamValue();

    public void setParamValue(String paramValue);
*/
    public Long getIdExterno();

    public void setIdExterno(Long idExterno);

    public AdministracionRemota getAdministracionRemota();

    public void setAdministracionRemota(AdministracionRemota administracionRemota);

}

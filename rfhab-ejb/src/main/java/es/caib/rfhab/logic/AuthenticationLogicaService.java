package es.caib.rfhab.logic;

import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;

import es.caib.rfhab.persistence.UsuariEntitatJPA;
import es.caib.rfhab.persistence.UsuariJPA;
import es.caib.rfhab.persistence.EntitatJPA;

/**
 * 
 * @author jagarcia
 * @author jpou
 *
 */
@Local
public interface AuthenticationLogicaService {

    public static final String JNDI_NAME = "java:app/rfhab-ejb/AuthenticationLogicaEJB!es.caib.rfhab.logic.AuthenticationLogicaService";

    public UsuariJPA findByUsername(String username) throws I18NException;

    public Long findEntitatByDir3(String dir3) throws I18NException;

    public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException;

    public UsuariJPA updateUsuariActiu(UsuariJPA usuari, boolean actiu) throws I18NException;

    public UsuariEntitatJPA create(UsuariEntitatJPA usuariEntitat) throws I18NException;

    public List<EntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException;

    public IUserInformationPlugin getUserInformationPluginInstance() throws EJBException;

}

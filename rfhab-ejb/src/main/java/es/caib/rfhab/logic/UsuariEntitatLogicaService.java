package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.UsuariEntitatService;
import es.caib.rfhab.model.entity.UsuariEntitat;
import es.caib.rfhab.persistence.EntitatJPA;
import es.caib.rfhab.persistence.UsuariEntitatJPA;

/**
 * 
 * @autor jagarcia
 * @autor jpou
 *
 */

@Local
public interface UsuariEntitatLogicaService extends UsuariEntitatService {

	public static final String JNDI_NAME = "java:app/rfhab-ejb/UsuariEntitatLogicaEJB!es.caib.rfhab.logic.UsuariEntitatLogicaService";

	public List<UsuariEntitatJPA> findAllByUsuariId(@NotNull long usuarioID) throws I18NException;

	public List<EntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException;

	public List<UsuariEntitat> findAllByUsuariIdWithEntitatId(@NotNull long usuariID, @NotNull long entitatID)
			throws I18NException;

	public boolean isActiuUsuariEntitat(@NotNull long usuariID, @NotNull long entitatID) throws I18NException;

}

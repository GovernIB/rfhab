package es.caib.rfhab.logic;

import java.util.List;

import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.rfhab.ejb.AutoritzacioEJB;
import es.caib.rfhab.model.entity.Autoritzacio;
import es.caib.rfhab.model.fields.AutoritzacioFields;

/**
 * 
 * @autor jagarcia
 *
 */

@Stateless
public class AutoritzacioLogicaEJB extends AutoritzacioEJB implements AutoritzacioLogicaService{

	@Override
	public List<Autoritzacio> getAutoritzacionsByFuncionariID(Long funcionariId) throws I18NException {
		return this.select(AutoritzacioFields.FUNCIONARIID.equal(funcionariId));
	}

	@Override
	public List<Autoritzacio> getAutoritzacionsByLlocID(Long llocId) throws I18NException {
		return this.select(AutoritzacioFields.LLOCID.equal(llocId));
	}
	
	
}

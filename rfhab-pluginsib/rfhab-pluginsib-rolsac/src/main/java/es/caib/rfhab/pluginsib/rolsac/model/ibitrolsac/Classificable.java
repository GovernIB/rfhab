package es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Classificable extends Traducible {

	private Set<Materia> materias;

	/* (non-Javadoc)
	 * @see es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.Classificable#getMaterias()
	 */
	public Set<Materia> getMaterias() {
	    return materias;
	}

	/* (non-Javadoc)
	 * @see es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.Classificable#setMaterias(java.util.Set)
	 */
	public void setMaterias(Set<Materia> materias) {
	    this.materias = materias;
	}

	/* (non-Javadoc)
	 * @see es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.Classificable#addMateria(es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.Materia)
	 */
	public void addMateria(Materia materia) {
		materias.add(materia);
		
	}
	
	
	public void removeMateria(long id) {
		Materia materia=new Materia();
		materia.setId(id);
		materias.remove(materia);
	}


	/* (non-Javadoc)
	 * @see es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.Classificable#tieneMaterias()
	 */
	public boolean tieneMaterias() {
		return 0<materias.size();
	}
	
	/* (non-Javadoc)
	 * @see es.caib.rfhab.pluginsib.rolsac.model.ibitrolsac.Classificable#estaClasificado()
	 */
	public boolean estaClasificado() {
		if(0==materias.size()) 
			return false;
		if(tieneMateriaSinClasificar()) 
			return false;
		return true;
	}

	private boolean tieneMateriaSinClasificar() {
		Iterator<Materia> it = materias.iterator();
		return Materia.CE_SENSECLASSIFICAR.equals(it.next().getCodigoEstandar());
	}

	@Override
	public String toString() {
		return "[materias=" + Arrays.toString(materias.toArray()) + "]";
	}

	
}
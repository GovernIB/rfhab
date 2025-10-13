package es.caib.rfhab.pluginsib.rolsac.model.respostes;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import es.caib.rfhab.pluginsib.rolsac.model.Procediments;
import es.caib.rfhab.pluginsib.rolsac.model.utils.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Respuesta Procediments
 *
 * @author Indra
 *
 */

@XmlRootElement
@ApiModel(value = Constantes.TXT_RESPUESTA + Constantes.ENTIDAD_PROCEDIMIENTO, description = Constantes.TXT_RESPUESTA
		+ Constantes.ENTIDAD_PROCEDIMIENTO)
public class RespuestaProcediments extends RespuestaBase {

	/** Resultado. **/
	@ApiModelProperty(value = "Listado con los objetos de resultado", required = false)
	private List<Procediments> resultado;

	public RespuestaProcediments(final String status, final String mensaje, final Integer numeroElementos,
			final List<Procediments> resultado) {
		super(status, mensaje, numeroElementos);
		this.resultado = resultado;
	};

	public RespuestaProcediments() {
		super();
	}

	public List<Procediments> getResultado() {
		return resultado;
	}

	public void setResultado(final List<Procediments> resultado) {
		this.resultado = resultado;
	}

}
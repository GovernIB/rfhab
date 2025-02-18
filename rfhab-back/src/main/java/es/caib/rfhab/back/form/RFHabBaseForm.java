package es.caib.rfhab.back.form;

import org.fundaciobit.genapp.common.web.form.BaseForm;

/**
 * 
 * @author anadal
 *
 */
public abstract class RFHabBaseForm extends BaseForm {

  public RFHabBaseForm() {
  }
  
  public RFHabBaseForm(boolean nou) {
    super(nou);
  }
  
  public RFHabBaseForm(RFHabBaseForm __toClone) {
    super(__toClone);
  }
  
}

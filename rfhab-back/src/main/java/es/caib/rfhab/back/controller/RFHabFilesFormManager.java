package es.caib.rfhab.back.controller;

import es.caib.rfhab.persistence.FitxerJPA;
import es.caib.rfhab.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;


/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class RFHabFilesFormManager extends FilesFormManager<Fitxer> {

  public RFHabFilesFormManager(IFileManager<Fitxer> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}

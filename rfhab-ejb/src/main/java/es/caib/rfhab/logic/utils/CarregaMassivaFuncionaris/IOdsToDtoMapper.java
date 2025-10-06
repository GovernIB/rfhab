package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import java.io.File;
import java.util.*;

public interface IOdsToDtoMapper {
    /**
     * Llegeix el fitxer ODS i genera instàncies de la classe DTO passada per
     * paràmetre,
     * assignant els valors segons el mapping .properties.
     * 
     * @param odsFile  Fitxer ODS a llegir
     * @param dtoClass Classe DTO a instanciar
     * @param <T>      Tipus de DTO
     * @return Llista d'instàncies DTO
     */
    public <T> List<T> readOdsToDto(File odsFile, Class<T> dtoClass) throws Exception;

    public List<Map<String, String>> readOds(File odsFile) throws Exception;
}

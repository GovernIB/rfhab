package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import org.odftoolkit.odfdom.doc.table.OdfTableCell;
import org.odftoolkit.odfdom.doc.table.OdfTableRow;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class OdsToDtoMapperNoLibreOffice implements IOdsToDtoMapper {
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
    @Override
    public <T> List<T> readOdsToDto(File odsFile, Class<T> dtoClass) throws Exception {
        return readOdsToDto(odsFile, dtoClass, false, false);
    }

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
    @Override
    public <T> List<T> readOdsToDto(File odsFile, Class<T> dtoClass, boolean trimQuotes, boolean trimBlanks)
            throws Exception {
        OdfSpreadsheetDocument doc = OdfSpreadsheetDocument.loadDocument(odsFile);
        OdfTable table = doc.getTableList().get(0);
        List<T> result = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        List<OdfTableRow> rows = table.getRowList();
        if (rows.isEmpty())
            return result;

        // Llegeix la capçalera
        OdfTableRow headerRow = rows.get(0);
        for (int c = 0; c < headerRow.getCellCount(); c++) {
            OdfTableCell cell = headerRow.getCellByIndex(c);
            headers.add(cell.getStringValue());
        }

        // Llegeix les files i crea DTOs
        for (int r = 1; r < rows.size(); r++) {
            OdfTableRow row = rows.get(r);
            boolean isEmpty = true;
            T dto = dtoClass.getDeclaredConstructor().newInstance();
            for (int c = 0; c < headers.size(); c++) {
                String colName = headers.get(c);
                String dtoProp = mapping.getProperty(colName);
                if (dtoProp != null) {
                    OdfTableCell cell = row.getCellByIndex(c);
                    String value = cell.getStringValue();
                    if (value != null && !value.trim().isEmpty()) {
                        isEmpty = false;
                    }
                    try {
                        java.lang.reflect.Field field = dtoClass.getDeclaredField(dtoProp);
                        field.setAccessible(true);
                        field.set(dto, value);
                    } catch (NoSuchFieldException e) {
                        // Si la propietat no existeix al DTO, ignora
                    }
                }
            }
            if (isEmpty)
                break;
            result.add(dto);
        }
        return result;
    }

    private final Properties mapping;

    public OdsToDtoMapperNoLibreOffice(File mappingFile) throws Exception {
        mapping = new Properties();
        try (FileInputStream fis = new FileInputStream(mappingFile)) {
            mapping.load(fis);
        }
    }

    @Override
    public List<Map<String, String>> readOds(File odsFile) throws Exception {
        OdfSpreadsheetDocument doc = OdfSpreadsheetDocument.loadDocument(odsFile);
        OdfTable table = doc.getTableList().get(0);
        List<Map<String, String>> result = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        List<OdfTableRow> rows = table.getRowList();
        if (rows.isEmpty())
            return result;

        // Llegeix la capçalera
        OdfTableRow headerRow = rows.get(0);
        for (int c = 0; c < headerRow.getCellCount(); c++) {
            OdfTableCell cell = headerRow.getCellByIndex(c);
            headers.add(cell.getStringValue());
        }

        // Llegeix les files i aplica el mapping
        for (int r = 1; r < rows.size(); r++) {
            OdfTableRow row = rows.get(r);
            boolean isEmpty = true;
            Map<String, String> dto = new HashMap<>();
            for (int c = 0; c < headers.size(); c++) {
                String colName = headers.get(c);
                String dtoProp = mapping.getProperty(colName);
                if (dtoProp != null) {
                    OdfTableCell cell = row.getCellByIndex(c);
                    String value = cell.getStringValue();
                    if (value != null && !value.trim().isEmpty()) {
                        isEmpty = false;
                    }
                    dto.put(dtoProp, value);
                }
            }
            if (isEmpty)
                break;
            result.add(dto);
        }
        return result;
    }
}

package es.caib.rfhab.logic.utils.CarregaMassivaFuncionaris;

import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;

import java.io.File;
import java.io.FileInputStream;
import java.text.Normalizer;
import java.util.*;

public class OdsToDtoMapper implements IOdsToDtoMapper {
    /**
     * Normalitza el nom de columna per fer-lo compatible amb el mapping.
     */
    private String normalizeColName(String colName) {
        if (colName == null)
            return null;
        // Elimina espais al principi/final, substitueix non-breaking space per espai
        // normal, col·lapsa múltiples espais
        String norm = colName.trim().replace('\u00A0', ' ');
        norm = norm.replaceAll("\\s+", " ");
        norm = Normalizer.normalize(norm, Normalizer.Form.NFC);
        return norm;
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

        org.odftoolkit.odfdom.dom.element.table.TableTableElement tableElement = (org.odftoolkit.odfdom.dom.element.table.TableTableElement) table
                .getOdfElement();
        org.w3c.dom.NodeList rowNodes = tableElement.getChildNodes();
        int rowIndex = 0;
        // Troba la primera fila (capçalera)
        org.odftoolkit.odfdom.dom.element.table.TableTableRowElement headerRowElement = null;
        for (int i = 0; i < rowNodes.getLength(); i++) {
            org.w3c.dom.Node node = rowNodes.item(i);
            if (node instanceof org.odftoolkit.odfdom.dom.element.table.TableTableRowElement) {
                headerRowElement = (org.odftoolkit.odfdom.dom.element.table.TableTableRowElement) node;
                rowIndex = i + 1;
                break;
            }
        }
        if (headerRowElement == null)
            return result;
        // Llegeix capçalera directament via DOM
        org.w3c.dom.NodeList headerCells = headerRowElement.getChildNodes();
        for (int c = 0; c < headerCells.getLength(); c++) {
            org.w3c.dom.Node cellNode = headerCells.item(c);
            if (cellNode instanceof org.odftoolkit.odfdom.dom.element.table.TableTableCellElement) {
                String value = cellNode.getTextContent();
                headers.add(value);
            }
        }

        // TODO: només debug
        printNormalizedHeadersAndMappingKeys(headers);

        // Itera per la resta de files
        for (int i = rowIndex; i < rowNodes.getLength(); i++) {
            org.w3c.dom.Node node = rowNodes.item(i);
            if (!(node instanceof org.odftoolkit.odfdom.dom.element.table.TableTableRowElement))
                continue;
            org.odftoolkit.odfdom.dom.element.table.TableTableRowElement rowElement = (org.odftoolkit.odfdom.dom.element.table.TableTableRowElement) node;
            org.w3c.dom.NodeList cellNodes = rowElement.getChildNodes();
            boolean isEmpty = true;
            T dto = dtoClass.getDeclaredConstructor().newInstance();
            int cellIndex = 0;
            for (int c = 0; c < headers.size(); c++) {
                // Busca la cel·la corresponent
                String colName = headers.get(c);
                String normColName = normalizeColName(colName);
                String dtoProp = mapping.get(normColName);
                String value = null;
                // Troba la cel·la corresponent (ignora nodes que no siguin cel·les)
                while (cellIndex < cellNodes.getLength()) {
                    org.w3c.dom.Node cellNode = cellNodes.item(cellIndex++);
                    if (cellNode instanceof org.odftoolkit.odfdom.dom.element.table.TableTableCellElement) {
                        value = cellNode.getTextContent();
                        break;
                    }
                }
                if (dtoProp != null) {
                    if (value != null && !value.trim().isEmpty()) {
                        isEmpty = false;
                    }
                    try {
                        java.lang.reflect.Field field = dtoClass.getDeclaredField(dtoProp);
                        field.setAccessible(true);
                        String finalValue = null;
                        if (value != null && !value.isEmpty()) {
                            finalValue = value;
                            if (trimBlanks) {
                                finalValue = finalValue.trim();
                            }
                            if (trimQuotes) {
                                finalValue = finalValue.replaceAll("^\"'|\"'$", "");
                            }
                            if (trimQuotes && trimBlanks) {
                                finalValue = finalValue.trim();
                            }
                        }
                        field.set(dto, finalValue);
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

    private final Map<String, String> mapping;

    public OdsToDtoMapper(File mappingFile) throws Exception {
        mapping = new HashMap<>();
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(mappingFile);
                java.io.InputStreamReader isr = new java.io.InputStreamReader(fis,
                        java.nio.charset.StandardCharsets.UTF_8)) {
            props.load(isr);
        }
        for (String key : props.stringPropertyNames()) {
            String normKey = normalizeColName(key);
            mapping.put(normKey, props.getProperty(key));
        }
    }

    @Override
    public List<Map<String, String>> readOds(File odsFile) throws Exception {
        OdfSpreadsheetDocument doc = OdfSpreadsheetDocument.loadDocument(odsFile);
        OdfTable table = doc.getTableList().get(0);
        List<Map<String, String>> result = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        org.odftoolkit.odfdom.dom.element.table.TableTableElement tableElement = (org.odftoolkit.odfdom.dom.element.table.TableTableElement) table
                .getOdfElement();
        org.w3c.dom.NodeList rowNodes = tableElement.getChildNodes();
        int rowIndex = 0;
        // Troba la primera fila (capçalera)
        org.odftoolkit.odfdom.dom.element.table.TableTableRowElement headerRowElement = null;
        for (int i = 0; i < rowNodes.getLength(); i++) {
            org.w3c.dom.Node node = rowNodes.item(i);
            if (node instanceof org.odftoolkit.odfdom.dom.element.table.TableTableRowElement) {
                headerRowElement = (org.odftoolkit.odfdom.dom.element.table.TableTableRowElement) node;
                rowIndex = i + 1;
                break;
            }
        }
        if (headerRowElement == null)
            return result;
        // Llegeix capçalera directament via DOM
        org.w3c.dom.NodeList headerCells = headerRowElement.getChildNodes();
        for (int c = 0; c < headerCells.getLength(); c++) {
            org.w3c.dom.Node cellNode = headerCells.item(c);
            if (cellNode instanceof org.odftoolkit.odfdom.dom.element.table.TableTableCellElement) {
                String value = cellNode.getTextContent();
                headers.add(value);
            }
        }

        // Itera per la resta de files
        for (int i = rowIndex; i < rowNodes.getLength(); i++) {
            org.w3c.dom.Node node = rowNodes.item(i);
            if (!(node instanceof org.odftoolkit.odfdom.dom.element.table.TableTableRowElement))
                continue;
            org.odftoolkit.odfdom.dom.element.table.TableTableRowElement rowElement = (org.odftoolkit.odfdom.dom.element.table.TableTableRowElement) node;
            org.w3c.dom.NodeList cellNodes = rowElement.getChildNodes();
            boolean isEmpty = true;
            Map<String, String> dto = new HashMap<>();
            int cellIndex = 0;
            for (int c = 0; c < headers.size(); c++) {
                String colName = headers.get(c);
                String normColName = normalizeColName(colName);
                String dtoProp = mapping.get(normColName);
                String value = null;
                // Troba la cel·la corresponent (ignora nodes que no siguin cel·les)
                while (cellIndex < cellNodes.getLength()) {
                    org.w3c.dom.Node cellNode = cellNodes.item(cellIndex++);
                    if (cellNode instanceof org.odftoolkit.odfdom.dom.element.table.TableTableCellElement) {
                        value = cellNode.getTextContent();
                        break;
                    }
                }
                if (dtoProp != null) {
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

    private void printNormalizedHeadersAndMappingKeys(List<String> headers) {
        System.out.println("ODS headers normalized:");
        for (String h : headers) {
            System.out.println("'" + normalizeColName(h) + "'");
        }
        System.out.println("Mapping keys normalized:");
        for (String k : mapping.keySet()) {
            System.out.println("'" + k + "'");
        }
    }
}

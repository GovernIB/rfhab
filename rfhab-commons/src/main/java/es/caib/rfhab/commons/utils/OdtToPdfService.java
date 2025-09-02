package es.caib.rfhab.commons.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.odftoolkit.odfdom.doc.OdfTextDocument;

import fr.opensagres.odfdom.converter.pdf.PdfConverter;
import fr.opensagres.odfdom.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

/**
 * Utilitat per generar documents ODT i PDF a partir de plantilles Freemarker.
 * Permet obtenir el PDF directament en memòria per ús web.
 */
public class OdtToPdfService {

    /**
     * Genera un document ODT a partir d'una plantilla.
     * 
     * @param map          Map amb les dades per Freemarker
     * @param templateFile Plantilla ODT (amb camps Freemarker)
     * @return ODT com a byte[]
     * @throws Exception
     */
    public static byte[] generateOdt(Map<String, Object> map, File templateFile) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        generateUsingXDocReport(map, templateFile, out);
        return out.toByteArray();
    }

    /**
     * Genera un document ODT a partir d'una plantilla.
     * 
     * @param map          Map amb les dades per Freemarker
     * @param templateFile Plantilla ODT (amb camps Freemarker)
     * @return ODT com a byte[]
     * @throws Exception
     */
    public static byte[] generateOdt(Map<String, Object> map, InputStream templateFile) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        generateUsingXDocReport(map, templateFile, out);
        return out.toByteArray();
    }

    /**
     * Genera un PDF a partir d'un fitxer ODT, sense guardar
     * fitxers al disc.
     * 
     * @param map          Map amb les dades per Freemarker
     * @param templateFile Plantilla ODT (amb camps Freemarker)
     * @return PDF com a byte[]
     * @throws Exception
     */
    public static byte[] generatePdf(byte[] odtTemplateBytes) throws Exception {
        // Ara converteix l'ODT a PDF en memòria
        ByteArrayInputStream odtInput = new ByteArrayInputStream(odtTemplateBytes);
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();

        OdfTextDocument document = OdfTextDocument.loadDocument(odtInput);
        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(document, pdfOutput, options);

        return pdfOutput.toByteArray();
    }

    /**
     * Genera un PDF a partir d'un fitxer ODT, sense guardar
     * fitxers al disc.
     * 
     * @param map          Map amb les dades per Freemarker
     * @param templateFile Plantilla ODT (amb camps Freemarker)
     * @return PDF com a byte[]
     * @throws Exception
     */
    public static byte[] generatePdf(byte[] odtTemplateBytes, PdfOptions options) throws Exception {
        // Ara converteix l'ODT a PDF en memòria
        ByteArrayInputStream odtInput = new ByteArrayInputStream(odtTemplateBytes);
        ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();

        OdfTextDocument document = OdfTextDocument.loadDocument(odtInput);
        PdfConverter.getInstance().convert(document, pdfOutput, options);

        return pdfOutput.toByteArray();
    }

    /**
     * Genera un PDF a partir d'una plantilla ODT, sense guardar
     * fitxers al disc.
     * 
     * @param map             Map amb les dades per Freemarker
     * @param odtTemplateFile Plantilla ODT (amb camps Freemarker)
     * @return PDF com a byte[]
     * @throws Exception
     */
    public static byte[] generatePdf(Map<String, Object> map, InputStream odtTemplateFile) throws Exception {
        // Primer genera l'ODT en memòria
        byte[] odtBytes = generateOdt(map, odtTemplateFile);

        // Ara converteix l'ODT a PDF en memòria
        return generatePdf(odtBytes);
    }

    /**
     * Genera un PDF a partir d'una plantilla ODT, sense guardar
     * fitxers al disc.
     * 
     * @param map             Map amb les dades per Freemarker
     * @param odtTemplateFile Plantilla ODT (amb camps Freemarker)
     * @return PDF com a byte[]
     * @throws Exception
     */
    public static byte[] generatePdf(Map<String, Object> map, File odtTemplateFile) throws Exception {
        // Primer genera l'ODT en memòria
        byte[] odtBytes = generateOdt(map, odtTemplateFile);

        // Ara converteix l'ODT a PDF en memòria
        return generatePdf(odtBytes);
    }

    /**
     * Utilitza XDocReport i Freemarker per fusionar la plantilla amb el context.
     */
    private static void generateUsingXDocReport(Map<String, Object> map, File templateFile, OutputStream outStream)
            throws IOException, XDocReportException {
        try (InputStream in = new FileInputStream(templateFile)) {
            generateUsingXDocReport(map, in, outStream);
        }
    }

    /**
     * Utilitza XDocReport i Freemarker per fusionar la plantilla amb el context.
     */
    private static void generateUsingXDocReport(Map<String, Object> map, InputStream in, OutputStream outStream)
            throws IOException, XDocReportException {
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
        FieldsMetadata metadata = report.createFieldsMetadata();
        report.setFieldsMetadata(metadata);
        IContext context = report.createContext();
        context.putMap(map);
        report.process(context, outStream);
    }

    /**
     * Utilitza XDocReport i Freemarker per fusionar la plantilla amb el context.
     */
    public static byte[] generatePdfUsingXDocReport(Map<String, Object> map, InputStream in,
            ByteArrayOutputStream outStream)
            throws IOException, XDocReportException {
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
        FieldsMetadata metadata = report.createFieldsMetadata();
        report.setFieldsMetadata(metadata);
        IContext context = report.createContext();
        context.putMap(map);
        Options options = Options.getFrom(
                fr.opensagres.xdocreport.core.document.DocumentKind.ODT).to(ConverterTypeTo.PDF);
        report.convert(context, options, outStream);
        return outStream.toByteArray();
    }

    /**
     * Utilitza XDocReport i Freemarker per fusionar la plantilla amb el context.
     */
    public static byte[] generatePdfDirectFromMapUsingXDocReport(Map<String, Object> map, InputStream in,
            ByteArrayOutputStream outStream)
            throws IOException, XDocReportException {
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
        Options options = Options.getFrom(
                fr.opensagres.xdocreport.core.document.DocumentKind.ODT).to(ConverterTypeTo.PDF);
        report.convert(map, options, outStream);
        return outStream.toByteArray();
    }

    // Exemple d'ús 1 per a una aplicació web (Spring, etc.)
    // response.setContentType("application/pdf");
    // response.setHeader("Content-Disposition", "attachment;
    // filename=\"document.pdf\"");
    // generateUsingXDocReport(map, new File("Plantilla.odt"),
    // response.getOutputStream());

    // Exemple d'ús 2 per a una aplicació web (Spring, etc.)
    // byte[] pdf = OdtToPdfService.generatePdf(map, plantillaOdt);
    // response.setContentType("application/pdf");
    // response.setHeader("Content-Disposition", "attachment;
    // filename=\"document.pdf\"");
    // response.getOutputStream().write(pdf);

    // El main només per proves manuals
    public static void main(String[] args) {
        try {
            // Prepara les dades per Freemarker
            Map<String, Object> dades = new HashMap<>();
            dades.put("nom", "NomUsuari");
            dades.put("llinatge", "LlinatgeUsuari");
            File templateFile = new File("Plantilla.odt");

            // Genera PDF en memòria i el desa a disc (només per proves)
            byte[] pdf = generatePdf(dades, templateFile);
            try (FileOutputStream fos = new FileOutputStream("prova.pdf")) {
                fos.write(pdf);
            }
            System.out.println("PDF generat correctament!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
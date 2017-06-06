package net.subey.starter.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

/**
 * Created by subey on 4/26/17.
 */
public class PdfTest {

    @Test
    public void htmlToPdf() throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream("pdf.pdf"));

        document.open();
        Path html = Paths.get("src/test/resources/pdf.html");

        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(html.toFile()));

        document.close();
    }
    @Test
    public void twigHtmlToPdf() throws IOException, DocumentException {

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("File", "stuff.txt");
        map.put("Name", "Some stuff");

        JtwigTemplate template = JtwigTemplate.classpathTemplate("pdf.twig");
        JtwigModel model = JtwigModel.newModel()
                .with("title", "My title")
                .with("data", map);

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        template.render(model, bs);

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream("pdf-twig.pdf"));

        document.open();

        InputStream is = new ByteArrayInputStream(bs.toByteArray());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,is);

        document.close();
    }
}

package edu.najah.cap.ExportData;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFCreator implements IFile {
    @Override
    public void CreateFile(String filename, String[] data) {
        Document document = new Document();
        String homeDirectory = System.getProperty("user.home");
        Path downloadPath = Paths.get(homeDirectory, "Downloads");

        Path filePath = Path.of(downloadPath + "\\" + filename + ".pdf");

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(filePath)));
            document.open();
            for (String line : data) {
                document.add(new Paragraph(line));
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

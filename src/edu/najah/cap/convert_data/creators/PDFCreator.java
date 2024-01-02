package edu.najah.cap.convert_data.creators;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.najah.cap.convert_data.IFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;


public class PDFCreator implements IFile {
    private static final Logger PDFCreatorLogger = Logger.getLogger(PDFCreator.class.getName());
    @Override
    public void createFile(String filename, List<String> data) {
        Document document = new Document();
        String homeDirectory = System.getProperty("user.home");
        Path downloadPath = Paths.get(homeDirectory, "Downloads");

        Path filePath = Paths.get(downloadPath.toString(), filename + ".pdf");

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath.toString()));
            document.open();
            for (String line : data) {
                document.add(new Paragraph(line));
            }
            document.close();
            PDFCreatorLogger.log(Level.INFO, "PDF file created successfully: {0}", filePath);
        } catch (IOException e) {
            PDFCreatorLogger.log(Level.SEVERE, "Error creating PDF file: {0}, Error: {1}", new Object[]{filePath, e.getMessage()});
        } catch (Exception e) {
            PDFCreatorLogger.log(Level.SEVERE, "Unexpected error creating PDF file: {0}, Error: {1}", new Object[]{filePath, e.getMessage()});
        }
    }

    @Override
    public void deleteFile(String filename){
        String homeDirectory = System.getProperty("user.home");
        Path downloadPath = Paths.get(homeDirectory, "Downloads");
        String filePath =downloadPath + "\\" + filename + ".pdf";
        try {
            Files.delete(Path.of(filePath));
            PDFCreatorLogger.log(Level.INFO, "File deleted successfully: {0}", filePath);
        } catch (NoSuchFileException e) {
            PDFCreatorLogger.log(Level.WARNING, "File not found: {0}", filePath);
        } catch (DirectoryNotEmptyException e) {
            PDFCreatorLogger.log(Level.WARNING, "Directory is not empty: {0}", filePath);
        } catch (IOException e) {
            PDFCreatorLogger.log(Level.SEVERE, "Error deleting file: {0}, Error: {1}", new Object[]{filePath, e.getMessage()});
        }
    }
}

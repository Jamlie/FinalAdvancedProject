package edu.najah.cap.convert_data;

import edu.najah.cap.convert_data.creators.PDFCreator;
import edu.najah.cap.convert_data.creators.ZipCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Generate {
    private static final Logger generateLogger = Logger.getLogger(Generate.class.getName());
    List<String> pdfFileNames = Arrays.asList("ProfileFile", "PostsFile", "ActivitiesFile", "PaymentsFile");
    public Generate(List<List<String>> data) {

        List<String> pdfNames = new ArrayList<>();
        PDFCreator pdfCreator = new PDFCreator();

        try {
            int i = 0;
            for (List<String> listOfData : data) {
                pdfCreator.createFile(pdfFileNames.get(i), listOfData);
                generateLogger.log(Level.INFO, pdfFileNames.get(i)+".pdf File generated successfully");
                pdfNames.add(pdfFileNames.get(i));
                i++;
            }

            ZipCreator.createZipFolder("UserData", pdfNames);

            int j = 0;
            for (String pdfName : pdfNames) {
                pdfCreator.deleteFile(pdfName);
                generateLogger.log(Level.INFO, pdfFileNames.get(j)+".pdf File generated successfully");

                j++;
            }
        } catch (Exception e) {
            generateLogger.log(Level.SEVERE, "An unexpected error occurred during generation", e);
        }

    }
}

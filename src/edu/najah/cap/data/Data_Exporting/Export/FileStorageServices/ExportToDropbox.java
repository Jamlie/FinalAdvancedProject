package edu.najah.cap.data.Data_Exporting.Export.FileStorageServices;

import edu.najah.cap.data.Data_Exporting.Export.ExportTypes.DirectExport;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToDropbox implements FileStorageService{
    private static final Logger exportToDropboxLogger = Logger.getLogger(DirectExport.class.getName());
    public ExportToDropbox() {
        exportToFileStorageService();
    }

    @Override
    public void exportToFileStorageService() {
        try{
            exportToDropboxLogger.log(Level.INFO, "File exporting to Dropbox storage file service.");
            Thread.sleep(1000);
            System.out.println("ZIP File Exported to Dropbox file Storage Service successfully.");
        } catch (Exception e) {
            exportToDropboxLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
        }
    }
}

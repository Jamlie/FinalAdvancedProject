package edu.najah.cap.data.data_exporting.export.file_storage_services;

import edu.najah.cap.data.data_exporting.export.export_types.DirectExport;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToDropbox implements FileStorageService{
    private static final Logger ExportToDropboxLogger = Logger.getLogger(DirectExport.class.getName());
    public ExportToDropbox() {
        exportToFileStorageService();
    }

    @Override
    public void exportToFileStorageService() {
        try{
            ExportToDropboxLogger.log(Level.INFO, "File exporting to Dropbox storage file service.");
            Thread.sleep(1000);
            System.out.println("ZIP File Exported to Dropbox file Storage Service successfully.");
        } catch (Exception e) {
            ExportToDropboxLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
        }
    }
}

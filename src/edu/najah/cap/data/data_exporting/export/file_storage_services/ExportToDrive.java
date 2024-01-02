package edu.najah.cap.data.data_exporting.export.file_storage_services;

import edu.najah.cap.data.data_exporting.export.export_types.DirectExport;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToDrive implements FileStorageService{
    private static final Logger exportToDriveLogger = Logger.getLogger(DirectExport.class.getName());
    public ExportToDrive() {
        exportToFileStorageService();
    }

    @Override
    public void exportToFileStorageService() {
        try{
            exportToDriveLogger.log(Level.INFO, "File exporting to Drive storage file service.");
            Thread.sleep(1000);
            System.out.println("ZIP File Exported to Drive file Storage Service successfully.");
        } catch (Exception e) {
            exportToDriveLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
        }
    }
}
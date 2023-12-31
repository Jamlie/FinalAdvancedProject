package edu.najah.cap.data.Data_Exporting.Export.FileStorageServices;


import edu.najah.cap.data.Data_Exporting.Export.ExportTypes.ExportTypeFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileStorageServiceFactory {
    private static final Logger FileStorageServiceFactoryLogger = Logger.getLogger(ExportTypeFactory.class.getName());
    private final String fileStorageServiceName;

    public FileStorageServiceFactory(String fileStorageServiceName){
        this.fileStorageServiceName = fileStorageServiceName;
    }

    public FileStorageService getFileStorageService(){
        try {
            FileStorageService fileStorageService = switch (fileStorageServiceName) {
                case "Drive" -> new ExportToDrive();
                case "Dropbox" -> new ExportToDropbox();
                default -> throw new IllegalStateException("Unexpected value: " + fileStorageServiceName);
            };
            FileStorageServiceFactoryLogger.log(Level.INFO, "ExportType created successfully: {0}", fileStorageServiceName);
            return fileStorageService;
        } catch (Exception e) {
            FileStorageServiceFactoryLogger.log(Level.SEVERE, "An unexpected error occurred during export type creation", new Object[]{fileStorageServiceName, e});
            return null;
        }
    }
}

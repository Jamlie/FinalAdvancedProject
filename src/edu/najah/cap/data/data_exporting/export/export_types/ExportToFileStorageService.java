package edu.najah.cap.data.data_exporting.export.export_types;

import edu.najah.cap.data.data_exporting.data_collection.user_types.CollectData;
import edu.najah.cap.data.data_exporting.data_collection.user_types.CollectDataForFactory;
import edu.najah.cap.data.data_exporting.export.file_storage_services.FileStorageService;
import edu.najah.cap.data.data_exporting.export.file_storage_services.FileStorageServiceFactory;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportToFileStorageService implements Export {
    private static final Logger exportToFileStorageServiceLogger = Logger.getLogger(DirectExport.class.getName());
    private final CollectDataForFactory collectDataForFactory;

    public ExportToFileStorageService(CollectDataForFactory collectDataForFactory) {
        this.collectDataForFactory = collectDataForFactory;
        export();
    }
    @Override
    public void export() {
        try {
            CollectData collectData = collectDataForFactory.getCollectionDataFor();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter file storage service name you want to upload file to: ");
            System.out.println("Chose between Drive or Dropbox");
            String fileStorageServiceType = scanner.nextLine();

            FileStorageService fileStorageService = new FileStorageServiceFactory(fileStorageServiceType).getFileStorageService();


        } catch (Exception e) {
            exportToFileStorageServiceLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
        }
    }
}

package edu.najah.cap.dataCollection.Export.ExportTypes;

import edu.najah.cap.dataCollection.CollectData;
import edu.najah.cap.dataCollection.CollectDataForFactory;
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
            exportToFileStorageServiceLogger.log(Level.FINE, "Export To File Storage Service process started");
            CollectData collectData = collectDataForFactory.getCollectionDataFor();

            System.out.println(collectData);
            System.out.println(collectData.collect().getUserProfile().getUserName() + " Storage Service");
        } catch (Exception e) {
            exportToFileStorageServiceLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
            throw e;
        }
    }
}

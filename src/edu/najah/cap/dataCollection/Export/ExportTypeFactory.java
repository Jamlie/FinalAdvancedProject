package edu.najah.cap.dataCollection.Export;

import edu.najah.cap.dataCollection.CollectDataForFactory;
import edu.najah.cap.dataCollection.Export.ExportTypes.DirectExport;
import edu.najah.cap.dataCollection.Export.ExportTypes.Export;
import edu.najah.cap.dataCollection.Export.ExportTypes.ExportToFileStorageService;
import edu.najah.cap.dataCollection.Export.ExportTypes.ExportType;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportTypeFactory {
    private static final Logger logger = Logger.getLogger(ExportTypeFactory.class.getName());

    private final ExportType exportType;
    private final CollectDataForFactory collectDataForFactory;

    public ExportTypeFactory(ExportType exportType, CollectDataForFactory collectDataForFactory) {
        this.exportType = exportType;
        this.collectDataForFactory = collectDataForFactory;
    }

    public Export getExportType(){
        try {
            Export export = switch (exportType) {
                case Direct -> new DirectExport(collectDataForFactory);
                case ToFileStorageService -> new ExportToFileStorageService(collectDataForFactory);
                default -> throw new IllegalStateException("Unexpected value: " + exportType);
            };
            logger.log(Level.INFO, "ExportType created successfully: {0}", exportType);
            return export;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred during export type creation", e);
            throw e;
        }
    }
}

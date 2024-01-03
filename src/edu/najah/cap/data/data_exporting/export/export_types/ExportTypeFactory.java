package edu.najah.cap.data.data_exporting.export.export_types;

import edu.najah.cap.data.data_exporting.data_collection.user_types.CollectDataForFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportTypeFactory {
    private static final Logger exportTypeFactorylogger = Logger.getLogger(ExportTypeFactory.class.getName());
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
            exportTypeFactorylogger.log(Level.INFO, "ExportType created successfully: {0}", exportType);
            return export;
        } catch (Exception e) {
            exportTypeFactorylogger.log(Level.SEVERE, "An unexpected error occurred during export type creation", new Object[]{exportType, e});
            throw e;
        }
    }
}

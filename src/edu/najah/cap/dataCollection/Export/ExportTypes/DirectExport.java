package edu.najah.cap.dataCollection.Export.ExportTypes;

import edu.najah.cap.dataCollection.CollectData;
import edu.najah.cap.dataCollection.CollectDataForFactory;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DirectExport implements Export {
    private static final Logger directExportLogger = Logger.getLogger(DirectExport.class.getName());
    private final CollectDataForFactory collectDataForFactory;
    public DirectExport(CollectDataForFactory collectDataForFactory) {
        this.collectDataForFactory = collectDataForFactory;
        export();
    }

    @Override
    public void export(){
        try {
            CollectData collectData = collectDataForFactory.getCollectionDataFor();

            System.out.println(collectData);
            System.out.println(collectData.collect().getUserProfile().getUserName() + " Direct");
        } catch (Exception e) {
            directExportLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
            throw e;
        }
    }
}
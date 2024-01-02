package edu.najah.cap.data.data_exporting.export.export_types;

import edu.najah.cap.data.data_exporting.data_collection.user_types.CollectData;
import edu.najah.cap.data.data_exporting.data_collection.user_types.CollectDataForFactory;

//import edu.najah.cap.data.data_exporting.export.convert_data.Generate;
//import edu.najah.cap.data.data_exporting.export.convert_data.SplitData;

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
            System.out.println(collectData.collect().getUserProfile().getUserName());

//            SplitData splitData = new SplitData();
//            Generate generate = new Generate(splitData.split(collectData.collect()));

        } catch (Exception e) {
            directExportLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
        }
    }
}
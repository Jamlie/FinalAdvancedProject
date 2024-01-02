package edu.najah.cap.data.Data_Exporting.Export.ExportTypes;

import edu.najah.cap.data.Data_Exporting.DataCollection.UserData;
import edu.najah.cap.data.Data_Exporting.DataCollection.UserTypes.CollectData;
import edu.najah.cap.data.Data_Exporting.DataCollection.UserTypes.CollectDataForFactory;
import edu.najah.cap.data.Data_Exporting.DataCollection.UsersData;
import edu.najah.cap.data.Data_Exporting.Export.ConvertData.Generate;
import edu.najah.cap.data.Data_Exporting.Export.ConvertData.SplitData;

import java.util.ArrayList;
import java.util.List;
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

            SplitData splitData = new SplitData();
            Generate generate = new Generate(splitData.split(collectData.collect()));

        } catch (Exception e) {
            directExportLogger.log(Level.SEVERE, "An unexpected error occurred during export", e);
        }
    }
}
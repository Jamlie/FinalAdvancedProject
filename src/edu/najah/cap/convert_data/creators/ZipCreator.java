package edu.najah.cap.convert_data.creators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZipCreator {
    private static final Logger ZipCreatorLogger = Logger.getLogger(ZipCreator.class.getName());

    public static void createZipFolder(String folderName, List<String> fileNames) {
        try {
            byte[] buffer = new byte[1024];
            String homeDirectory = System.getProperty("user.home");
            Path downloadPath = Paths.get(homeDirectory, "Downloads");
            Path zipFilePath = Paths.get(downloadPath.toString(), folderName + ".zip");

            try (FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                for (String fileName : fileNames) {
                    Path filePath = Paths.get(downloadPath.toString(), fileName + ".pdf");
                    File file = filePath.toFile();

                    try (FileInputStream fis = new FileInputStream(file)) {
                        zos.putNextEntry(new ZipEntry(file.getName()));

                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }

                        zos.closeEntry();
                    } catch (IOException e) {
                        ZipCreatorLogger.log(Level.SEVERE, "Error adding file to zip: {0}, Error: {1}", new Object[]{file.getName(), e.getMessage()});
                    }
                }
            }

            ZipCreatorLogger.log(Level.INFO, "Zip file created successfully: {0}", zipFilePath);
        } catch (IOException e) {
            ZipCreatorLogger.log(Level.SEVERE, "Error creating zip file: {0}, Error: {1}", new Object[]{folderName, e.getMessage()});
        }
    }
}

package edu.najah.cap.ExportData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreator {
    public static void createZipFolder(String folderName , String... fileNames) {
        try {
            byte[] buffer = new byte[1024];
            String homeDirectory = System.getProperty("user.home");
            Path downloadPath = Paths.get(homeDirectory, "Downloads");
            FileOutputStream fos = new FileOutputStream(downloadPath + "\\" + folderName + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String fileName : fileNames) {
                File file = new File(downloadPath+"\\"+fileName);
                FileInputStream fis = new FileInputStream(file);
                zos.putNextEntry(new ZipEntry(file.getName()));

                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();
                fis.close();
            }

            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

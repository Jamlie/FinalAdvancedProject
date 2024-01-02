package edu.najah.cap.data.data_exporting.export.convert_data;

import java.util.List;

public interface IFile {
    void createFile(String filename , List<String> data);
    void deleteFile(String filename);
}

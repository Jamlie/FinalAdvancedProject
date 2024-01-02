Final project template 
======================
This is the template for the final project in the course. Your application should be run on [Application.java](src%2Fedu%2Fnajah%2Fcap%2Fdata%2FApplication.java)

**Do not change any existing code in the template. You can add new classes and methods as you see fit.**

## Project Description

### convert_data
a package contains the process to convert data and direct export

#### IFile interface
This interface defines a contract for classes that handle file creation and deletion operations within the `edu.najah.cap.convert_data` package. It ensures consistency and modularity in file-related tasks.

## **Methods**

- **createFile(String filename, List<String> data):**
    - Creates a file with the specified filename.
    - Takes a list of strings as the file content.
    - Implementation details are left to concrete classes.
- **deleteFile(String filename):**
    - Deletes an existing file with the given filename.
    - Implementation details are left to concrete classes.

## **Purpose**
- Promotes code reusability and maintainability by defining a common interface for file operations.
- Allows different file-handling implementations to be swapped without affecting other parts of the code.
- Facilitates testing by enabling mock file implementations for isolated testing.

## **Usage**

1. **Implement the interface:**
   ```
   public class PDFCreator implements IFile {
       // Implement createFile and deleteFile methods for PDFs
   }
   ```

2. **Use instances in other classes:**
   ```
   IFile fileHandler = new PDFCreator(); // Or any other implementation
   fileHandler.createFile("my_file", data);
   fileHandler.deleteFile("my_file");
   ```

## **Benefits**
- **Decoupling:** Separates file operations from other logic for better organization and flexibility.
- **Polymorphism:** Allows using different file handling implementations interchangeably.
- **Testability:** Enables mocking for controlled testing scenarios.


#### creators
a package contains the process to direct export like install zip folder has pdfs about users data

##### PDFCreator
This class provides functionality to create and delete PDF files using the iText library. It adheres to the IFile interface for consistent file operations.

## Key Features
Creates PDF files with specified filenames and content.
Saves generated PDFs to the user's Downloads directory.
Deletes existing PDF files from the Downloads directory.
Logs file operations for debugging and tracking.

## Usage

**Import the class:**
```
Java import edu.najah.cap.convert_data.creators.PDFCreator;
```
**Create an instance:**
```
JavaPDFCreator pdfCreator = new PDFCreator();
```
**Create a PDF file:**
``` 
Java List<String> data = Arrays.asList("Line 1", "Line 2", "Line 3");
pdfCreator.createFile("my_pdf_file", data);
```

**Delete a PDF file:**
```
Java pdfCreator.deleteFile("my_pdf_file");
```

## Dependencies
iText library for PDF generation: https://itextpdf.com/

## Logging
Uses a Logger named PDFCreatorLogger to log file operations.
Check log files for error messages or debugging information.

##### ZipCreator
This class provides functionality to create zip archives containing multiple PDF files. It automatically saves generated zip files to the user's Downloads directory and logs file operations for debugging and tracking.

## **Key Features**
- Creates zip archives with specified folder names and PDF files.
- Locates PDF files in the user's Downloads directory.
- Handles potential errors and logs them appropriately.

## **Usage**

 **Import the class:**
   ```
   import edu.najah.cap.convert_data.creators.ZipCreator;
   ```

 **Call the createZipFolder method:**

   ```
   List<String> fileNames = Arrays.asList("file1", "file2", "file3");  
   ZipCreator.createZipFolder("my_zip_archive", fileNames);
   ```

## **Assumptions**

- files to be added to the zip archive are located in the user's Downloads directory.
- Files have a ".pdf" extension.

## **Logging**

- Uses a `Logger` named `ZipCreatorLogger` to log file operations.
- Check log files for error messages or debugging information.

## **Additional Notes**
- Saves zip archives to the user's Downloads directory by default.

#### Generate class
This class orchestrates the generation of multiple PDF files from provided data and then compresses them into a zip archive for convenient distribution. It leverages the `PDFCreator` and `ZipCreator` classes for file creation and archiving, respectively.

## **Key Features**
- Generates multiple PDF files with specified names and content.
- Combines generated PDFs into a zip archive named "UserData.zip".
- Deletes individual PDF files after creating the zip archive.
- Uses logging for tracking file operations and debugging.

## **Usage**

1. **Instantiate the class:**

   ```
   List<List<String>> data = ... // long code to prepare your data in lists of strings
   Generate generator = new Generate(data);
   ```

2. **The constructor handles file generation and zipping automatically.**

## **Assumptions**
- Input data is organized as lists of strings, where each list represents a separate PDF file.
- PDFCreator and ZipCreator classes are accessible within the same package.

## **Logging**
- Uses a `Logger` named `generateLogger` to log file operations.
- Check log files for error messages or debugging information.

## **Additional Notes**
- Saves PDFs to the user's Downloads directory before zipping them.
- Deletes PDFs after zipping to conserve space.

#### SplitData 
This class acts as a data organizer, extracting and structuring relevant information from a `UserData` object into separate lists for profile data, posts, activities, and payments. It prepares data for downstream processing and file generation.

## **Key Features**

- Extracts user profile, posts, activities, and payment data into distinct lists.
- Conditionally extracts data based on availability in the `UserData` object.
- Logs extraction processes for debugging and tracking.

## **Usage**
1. **Instantiate the class:**
   ```
   SplitData splitter = new SplitData();
   ```

2. **Call the `split` method with a `UserData` object:**
   ```
   List<List<String>> organizedData = splitter.split(userData);
   ```

   - The `organizedData` will contain lists for profile, posts, activities, and/or payments, depending on available data.

## **Methods**
- **split(UserData userData):**
    - Extracts and organizes data into lists.
    - Returns a list of lists, where each inner list represents a data category.
- **UserProfileData(UserData userData):**
    - Extracts user profile data into a list.
- **ListOfPosts(UserData userData):**
    - Extracts post data into a list.
- **ListOfActivities(UserData userData):**
    - Extracts user activity data into a list.
- **ListOfPayments(UserData userData):**
    - Extracts payment data into a list.

## **Logging**
- Uses a `Logger` named `SplitDataLogger` to log extraction processes.
- Check log files for progress information or debugging.

## **Additional Notes**
- Operates on the assumption that the `UserData` object contains valid data.
- Consider adding error handling for unexpected data structures.
- Designed to prepare data for subsequent file generation or other processing tasks.

Final project template 
======================
This is the template for the final project in the course. Your application should be run on [Application.java](src%2Fedu%2Fnajah%2Fcap%2Fdata%2FApplication.java)

**Do not change any existing code in the template. You can add new classes and methods as you see fit.**

## Project Description

### convert_data
a package contains the process to convert data and direct export

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

Import the class:
```
Java import edu.najah.cap.convert_data.creators.PDFCreator;
```java
Create an instance:
```
JavaPDFCreator pdfCreator = new PDFCreator();
```
Create a PDF file:
``` 
Java List<String> data = Arrays.asList("Line 1", "Line 2", "Line 3");
pdfCreator.createFile("my_pdf_file", data);
```

Delete a PDF file:
```
Java pdfCreator.deleteFile("my_pdf_file");
```

## Dependencies

iText library for PDF generation: https://itextpdf.com/

## Logging
Uses a Logger named PDFCreatorLogger to log file operations.
Check log files for error messages or debugging information.

##### ZipCreator
#### Generate class
#### IFile interface
#### SplitData 

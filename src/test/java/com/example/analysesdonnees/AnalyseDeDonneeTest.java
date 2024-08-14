package com.example.analysesdonnees;

import com.example.analysesdonnees.service.AnalyseDeDonnee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.analysesdonnees.service.AnalyseDeDonnee.processExcelData;
import static org.junit.jupiter.api.Assertions.*;

public class AnalyseDeDonneeTest {

    @TempDir
    @Value("${app.file.path}")
    public Path tempDir;

//    @Test
//    public void testRechargeFichiersCsv() throws IOException {
//        // Create a test CSV file
//        String data = "col1,col2,col3\nval1,val2,val3";
//        Files.write(tempDir.resolve("test.csv"), data.getBytes());
//
//        // Call the rechargeFichiers method with the test file path
//        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
//        List<String[]> donnees = analyseDeDonnee.rechargeFichiers(tempDir.resolve("test.csv").toString());
//
//        // Assert that the returned data is correct
//        assertEquals(1, donnees.size());
//        assertArrayEquals(new String[]{"col1", "col2", "col3"}, donnees.get(0));
//    }

    @Value("${app.file.path}")
    String csvFilePath = "/Documents/tableau.csv";
    @Test
    public void readFile() {

        try {
            byte[] csvData = AnalyseDeDonnee.readCsvFile(csvFilePath);
            // Traiter le fichier CSV
            processCsvData(csvData);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }
    }
//    @Value("${app.file.path}")
//    @Value("${storage.path}")
//    String excelFilePath="/Documents/tableau.xlsx ";

//    @Value("${app.file.path}")
//    String excelFilePath;
    String excelFilePath="E:/Desktop/projet 4/tableau.xslx";
    @Test
    public void readFileExcel(){


        try {
            Workbook workbook = AnalyseDeDonnee.readExcelFile(excelFilePath);
            // Traiter le fichier Excel
            processExcelData(workbook);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier Excel : " + e.getMessage());
        }
    }

    private static void processCsvData(byte[] csvData) {
        // Logique de traitement du fichier CSV
        System.out.println("Données du fichier CSV : " + new String(csvData));
    }
    @Test
    public void testRechargeFichiersCsv() throws IOException {
        // Create a test CSV file
        String data = "col1,col2,col3\nval1,val2,val3";
        Files.write(tempDir.resolve("test.csv"), data.getBytes());

        // Call the rechargeFichiers method with the test file path
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
        List<String[]> donnees = analyseDeDonnee.rechargeFichiers(tempDir.resolve("test.csv").toString());

        // Assert that the returned data is correct
        assertEquals(2, donnees.size());
        assertArrayEquals(new String[]{"col1", "col2", "col3"}, donnees.get(0));
        assertArrayEquals(new String[]{"val1", "val2", "val3"}, donnees.get(1));
    }
    public List<String[]> rechargeFichiers(String chemin) throws IOException {
        List<String[]> donnees = new ArrayList<>();
        boolean firstRow = true; // Flag to skip first row (header)
        try (InputStream inputStream = Files.newInputStream(Paths.get(chemin));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser) {
                if (!firstRow) { // Skip the first row
                    donnees.add(csvRecord.toList().toArray(new String[0]));
                }
                firstRow = false; // Only skip the first iteration
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donnees;
    }

//    @Test
//    public void testRechargeFichiersCsv() throws IOException {
//        // ... (create test CSV file)
//
//        // Call the rechargeFichiers method
//        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
//        List<String[]> donnees = analyseDeDonnee.rechargeFichiers(tempDir.resolve("test.csv").toString());
//
//        // Assert that the returned data is correct (expect 2 elements - header and data)
//        assertEquals(2, donnees.size());
//        // You can further assert the content of the first element (header) and second element (data) if needed.
//    }


    @Test
    public void testRechargeFichiersEmptyCsv() throws IOException {
        // Create an empty test CSV file
        Files.write(tempDir.resolve("test.csv"), "".getBytes());

        // Call the rechargeFichiers method with the test file path
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
        List<String[]> donnees = analyseDeDonnee.rechargeFichiers(tempDir.resolve("test.csv").toString());

        // Assert that the returned list is empty
        assertTrue(donnees.isEmpty());
    }

    @Test
    public void testRechargeFichiersInvalidFile() {
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();

        // Try to read a non-existent file
        assertThrows(IOException.class, () -> analyseDeDonnee.rechargeFichiers("non-existent.csv"));
    }

    @Test
    public void testLireFichiersPdf() throws IOException {
        // Create a test PDF file with some text content
        String textContent = "This is some text content in a PDF file.";
        Files.write(tempDir.resolve("test.pdf"), textContent.getBytes());

        // Call the lireFichiersPdf method with the test file path
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
        String text = analyseDeDonnee.lireFichiersPdf(tempDir.resolve("test.pdf").toString());

        // Assert that the extracted text is correct
        assertEquals(textContent, text);
    }

    @Test
    public void testLireFichiersPdfEmptyFile() throws IOException {
        // Create an empty test PDF file
        Files.write(tempDir.resolve("tableau.pdf"), "".getBytes());

        // Call the lireFichiersPdf method with the test file path
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
        String text = analyseDeDonnee.lireFichiersPdf(tempDir.resolve("tableau.pdf").toString());

        // Assert that the extracted text is empty
        assertEquals("", text);
    }

    @Test
    public void testLireFichiersPdfInvalidFile() {
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();

        // Try to read a non-existent file
        assertThrows(IOException.class, () -> analyseDeDonnee.lireFichiersPdf("non-existent.pdf"));
    }

    @Test
    public void testLireFichierText() throws IOException {
        // Create a test text file with some content
        String content = "This is some text content in a text file.";
        Files.write(tempDir.resolve("test.txt"), content.getBytes());

        // Call the lireFichierText me thod with the test file path
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
        String text = analyseDeDonnee.lireFichierText(analyseDeDonnee.toString());
        assertEquals(content,text);

    }
}
/*
" ge@Test
public void testLireFichierText() throws IOException {
        // Create a test text file with some content
        String content = "This is some text content in a text file.";
        Files.write(tempDir.resolve("test.txt"), content.getBytes());

        // Call the lireFichierText method with the correct file path
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
        String text = analyseDeDonnee.lireFichierText(tempDir.resolve("test.txt").toString());

        // Assert that the extracted text is correct
        assertEquals(content, text);
        }
        "*//*
" poe@Test
public void testLireFichierText() throws IOException {
        // Create a test text file with some content
        String content = "This is some text content in a text file.";
        Path testFilePath = tempDir.resolve("test.txt");
        Files.write(testFilePath, content.getBytes());

        // Call the lireFichierText method with the test file path
        AnalyseDeDonnee analyseDeDonnee = new AnalyseDeDonnee();
        String text = analyseDeDonnee.lireFichierText(testFilePath.toString());

        // Assertions or other test logic
        // ...
        }"*/
/*import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class RechargeFichiersTest {

    @Test
    void testChargerFichierCSV() throws IOException {
        String chemin = "src/test/resources/test.csv";
        List<String[]> donnees = rechargeFichiers(chemin);

        Assertions.assertEquals(3, donnees.size());
        Assertions.assertArrayEquals(new String[]{"1", "Nom1", "Prénom1"}, donnees.get(0));
        Assertions.assertArrayEquals(new String[]{"2", "Nom2", "Prénom2"}, donnees.get(1));
        Assertions.assertArrayEquals(new String[]{"3", "Nom3", "Prénom3"}, donnees.get(2));
    }

    @Test
    void testChargerFichierTexte() throws IOException {
        String chemin = "src/test/resources/test.txt";
        List<String[]> donnees = rechargeFichiers(chemin);

        Assertions.assertEquals(3, donnees.size());
        Assertions.assertArrayEquals(new String[]{"Ligne 1"}, donnees.get(0));
        Assertions.assertArrayEquals(new String[]{"Ligne 2"}, donnees.get(1));
        Assertions.assertArrayEquals(new String[]{"Ligne 3"}, donnees.get(2));
    }

    @Test
    void testChargerFichierPDF() throws IOException {
        String chemin = "src/test/resources/test.pdf";
        List<String[]> donnees = rechargeFichiers(chemin);

        Assertions.assertEquals(3, donnees.size());
        Assertions.assertArrayEquals(new String[]{"Page 1 Ligne 1"}, donnees.get(0));
        Assertions.assertArrayEquals(new String[]{"Page 1 Ligne 2"}, donnees.get(1));
        Assertions.assertArrayEquals(new String[]{"Page 2 Ligne 1"}, donnees.get(2));
    }

    private List<String[]> rechargeFichiers(String chemin) {
        // Implémentation de la méthode rechargeFichiers()
        // (code précédemment fourni)
    }*/

/*import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ExcelUtilsTest {

    @Test
    void testReadExcelFile() {
        // Arrange
        String excelFilePath = "src/test/resources/test_file.xlsx";

        // Act
        try {
            Workbook workbook = ExcelUtils.readExcelFile(excelFilePath);

            // Assert
            Assertions.assertNotNull(workbook, "Workbook should not be null");
            Assertions.assertEquals(1, workbook.getNumberOfSheets(), "Number of sheets should be 1");
        } catch (IOException e) {
            Assertions.fail("Exception thrown: " + e.getMessage());
        }
    }
}*/

//excel
/* import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

    /**
     * Récupère un fichier Excel depuis le disque local.
     * @param filePath Le chemin d'accès complet du fichier Excel.
     * @return Le workbook du fichier Excel.
     * @throws IOException Si une erreur survient lors de la lecture du fichier.
     */
    /*
public static Workbook readExcelFile(String filePath) throws IOException {
    File file = new File(filePath);
    try (FileInputStream fis = new FileInputStream(file)) {
        return WorkbookFactory.create(fis);
    }
}
}
String excelFilePath = "/chemin/vers/mon/fichier.xlsx";

        try {
        Workbook workbook = ExcelUtils.readExcelFile(excelFilePath);
        // Traiter le fichier Excel
        processExcelData(workbook);
        } catch (IOException e) {
        System.err.println("Erreur lors de la lecture du fichier Excel : " + e.getMessage());
        }

private static void processExcelData(Workbook workbook) {
        // Logique de traitement du fichier Excel
        System.out.println("Nombre de feuilles dans le fichier Excel : " + workbook.getNumberOfSheets());
        }*/

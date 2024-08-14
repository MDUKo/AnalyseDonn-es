package com.example.analysesdonnees.service;

import com.example.analysesdonnees.entity.Utilisateur;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyseDeDonnee {

//    public List<String[]> rechargeFichiers(String chemin) {
//        List<String[]> donnees = new ArrayList<>();
//        try (InputStream inputStream = Files.newInputStream(Paths.get(chemin));
//
//             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
//            for (CSVRecord csvRecord : csvParser) {
//                donnees.add(csvRecord.toList().toArray(new String[0]));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return donnees;
//    }

        public List<String[]> rechargeFichiers(String chemin) throws IOException {
            List<String[]> donnees = new ArrayList<>();

            try (InputStream inputStream = Files.newInputStream(Path.of(chemin));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                // Lire la première ligne (éventuel en-tête)
                String ligne = reader.readLine();

                // Traiter les lignes suivantes
                while ((ligne = reader.readLine()) != null) {
                    String[] colonnes = ligne.split(",");
                    donnees.add(colonnes);
                }
            }

            return donnees;
        }
    public static byte[] readCsvFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
    public static Workbook readExcelFile(String filePath) throws IOException {
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            return WorkbookFactory.create(fis);
        }
    }
    public static void processExcelData(Workbook workbook) {
        // Logique de traitement du fichier Excel
        System.out.println("Nombre de feuilles dans le fichier Excel : " + workbook.getNumberOfSheets());
    }

    public String lireFichiersPdf(String chemin){
        String text="tableau.pdf";
        try(PDDocument document= PDDocument.load(Paths.get(chemin).toFile())){
            PDFTextStripper stripper=new PDFTextStripper();
            text=stripper.getText(document);
        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }


    public String  lireFichierText(String chemin){
        StringBuilder contenu=new StringBuilder();
        try(BufferedReader reader= Files.newBufferedReader(Paths.get(chemin))){
            String line;
            while((line=reader.readLine())!=null){
                contenu.append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return contenu.toString();
    }
    public void user (Utilisateur utilisateur){
            Utilisateur saveUtilisateur= new Utilisateur();
            saveUtilisateur.setNom(utilisateur.getNom());
            saveUtilisateur.setPrenom(utilisateur.getPrenom());
            saveUtilisateur.setEmail(utilisateur.getEmail());

            System.out.println("l'utilisateur a les identifiants"+saveUtilisateur);
    }

}

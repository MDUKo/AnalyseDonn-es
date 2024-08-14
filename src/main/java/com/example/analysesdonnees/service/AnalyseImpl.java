package com.example.analysesdonnees.service;


import com.example.analysesdonnees.entity.Donnees;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AnalyseImpl {

    private List<Donnees> stockage=new ArrayList<>();
    public List<Donnees> rechargeDonnees(String chemin)throws IOException,CsvValidationException{
        List<Donnees> donnees= new ArrayList<>();

          try(CSVReader csvReader =new CSVReader(new FileReader(chemin))) {
              String[] entete = csvReader.readNext();

              String[] ligne ;
              while((ligne=csvReader.readNext())!=null)
              {
                  Donnees donnees1 = new Donnees();
                  for (int i = 0; i < entete.length; i++) {
                      String valeur= entete[i];
                      donnees1.setValeur(entete[i]);
                  }
                  donnees.add(donnees1);
              }
          }catch(CsvValidationException e) {

              System.err.println("Erreur de validation CSV : " + e.getMessage());
              throw e;
          }
        return donnees;
          }



    public void saveDonnees(List<Donnees> donnees){
        stockage.addAll(donnees);
    }

    public List<Donnees> getAllDonnees(){
        return stockage;
    }

    public List<Donnees> transformationDonnees(List<Donnees> donnees){
        return  null;

    }
    public Donnees calculStastDonnees(List<Donnees> donnees){
        return null;

    }

    public void generateReport(String reportType, String cheminDeSortir){


//        List<Donnees> donnees= rechargeDonnees(cheminDeSortir);

//        List<Donnees> Transformedonnees= transformationDonnees(donnees);

        if(reportType.equals("pdf")){

        }



    }
    public List<String[]> rechargeFichiers(String chemin) {
    List<String[]> donnees = new ArrayList<>();
    try {
        // Détection du type de fichier en fonction de l'extension
        String extension = getFileExtension(chemin);
        switch (extension) {
            case "csv":
                donnees = chargerFichierCSV(chemin);
                break;
            case "txt":
                donnees = chargerFichierTexte(chemin);
                break;
            case "pdf":
                donnees = chargerFichierPDF(chemin);
                break;
            default:
                System.out.println("Format de fichier non pris en charge : " + extension);
                break;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return donnees;
}

    private List<String[]> chargerFichierCSV(String chemin) throws IOException {
        List<String[]> donnees = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(Paths.get(chemin));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser) {
                donnees.add(csvRecord.toList().toArray(new String[0]));
            }
        }
        return donnees;
    }

    private List<String[]> chargerFichierTexte(String chemin) throws IOException {
        List<String[]> donnees = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(Paths.get(chemin));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                donnees.add(new String[]{ligne});
            }
        }
        return donnees;
    }

    private List<String[]> chargerFichierPDF(String chemin) throws IOException {
        List<String[]> donnees = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(Paths.get(chemin));
             PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String texte = stripper.getText(document);
            String[] lignes = texte.split("\n");
            for (String ligne : lignes) {
                donnees.add(new String[]{ligne});
            }
        }
        return donnees;
    }

    private String getFileExtension(String chemin) {
        int indexDot = chemin.lastIndexOf(".");
        if (indexDot == -1) {
            return "";
        }
        return chemin.substring(indexDot + 1).toLowerCase();
    }

}

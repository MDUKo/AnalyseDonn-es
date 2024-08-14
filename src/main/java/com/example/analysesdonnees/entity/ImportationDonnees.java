package com.example.analysesdonnees.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportationDonnees {
    public static List<Donnees> importerDonnees(File file){
        List<Donnees> donnees=new ArrayList<>();
       try{
           Scanner scanner
                   =new Scanner(file);
           while (scanner.hasNext()) {
               String ligne = scanner.next();
//               Donnees donnee = new Donnees(ligne);
//               donnees.add(donnee);

           }
           scanner.close();
       }catch (IOException e){
           System.out.println("Les donnéés n'ont pas été importéé"+donnees);
       }
       return donnees;
    }


}

package com.example.analysesdonnees;

import com.example.analysesdonnees.entity.Donnees;
import com.example.analysesdonnees.entity.FiltrageDeDonnees;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AnalysesDonneesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalysesDonneesApplication.class, args);

        Donnees donnees= new Donnees(1,"1", "nom",new Date(),"essai","ca");

        FiltrageDeDonnees filtrageDeDonnees =new FiltrageDeDonnees(1,"1","1","1","1");

        filtrageDeDonnees.applyFilter(donnees);
       
        System.out.println("j'espère que ca va passer"+donnees);
    }

}

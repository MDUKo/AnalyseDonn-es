package com.example.analysesdonnees.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseDonnees {

    private List<Donnees> donnees;

    private String resultat_analyse;


    public void filterData(List<FiltrageDeDonnees> filtrageDeDonnees)
    {
        for (Donnees deDonnees:donnees)
        {
            for (FiltrageDeDonnees filtrageDeDonnees1: filtrageDeDonnees)
            {
                    if (!filtrageDeDonnees1.applyFilter(deDonnees) && !filtrageDeDonnees1.applyFilters(deDonnees))
                        {

                        }
                 else
                {
                     donnees.add(deDonnees);
                }
            }
        }

    }


    public static double calculerMoyenne(List<Donnees> donnees){
        double somme=0;
        for(Donnees donnee: donnees){
            somme+= donnee.getValeurNumerique();
        }
        return somme/ donnees.size();
    }
}

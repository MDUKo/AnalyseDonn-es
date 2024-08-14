package com.example.analysesdonnees.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Donnees {
    private double valeurNumerique;

    private String valeur;


    private String nom_donnees;

    private Date creation_date;

    private String description_donnees;

    private String category_doonees;

//    public Donnees(double valeurNumerique, String valeur, String nom_donnees, Date creation_date, String description_donnees, String category_doonees) {
//        this.valeurNumerique = valeurNumerique;
//        this.valeur = valeur;
//        this.nom_donnees = nom_donnees;
//        this.creation_date = creation_date;
//        this.description_donnees = description_donnees;
//        this.category_doonees = category_doonees;
//    }


    public double getValeurNumerique() {
        return valeurNumerique;
    }

    public void setValeurNumerique(double valeurNumerique) {
        this.valeurNumerique = valeurNumerique;
    }

    public String getValeur(String valeur) {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getNom_donnees() {
        return nom_donnees;
    }

    public void setNom_donnees(String nom_donnees) {
        this.nom_donnees = nom_donnees;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getDescription_donnees() {
        return description_donnees;
    }

    public void setDescription_donnees(String description_donnees) {
        this.description_donnees = description_donnees;
    }

    public String getCategory_doonees() {
        return category_doonees;
    }

    public void setCategory_doonees(String category_doonees) {
        this.category_doonees = category_doonees;
    }

    public void afficheDonnees(Donnees donnees){
        System.out.println("La données est:"+donnees);
    }
}

package com.example.analysesdonnees.entity;

import java.util.Collection;

public class LectureDeDonnees {

    private String chemin_fichier_donnees;

    private String type_fichier_donnees;

    private Collection<Donnees> donnees;

    public String getChemin_fichier_donnees() {
        return chemin_fichier_donnees;
    }

    public void setChemin_fichier_donnees(String chemin_fichier_donnees) {
        this.chemin_fichier_donnees = chemin_fichier_donnees;
    }

    public String getType_fichier_donnees() {
        return type_fichier_donnees;
    }

    public void setType_fichier_donnees(String type_fichier_donnees) {
        this.type_fichier_donnees = type_fichier_donnees;
    }

    public Collection<Donnees> getDonnees() {
        return donnees;
    }

    public void setDonnees(Collection<Donnees> donnees) {
        this.donnees = donnees;
    }
}

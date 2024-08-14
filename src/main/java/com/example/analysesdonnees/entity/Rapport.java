package com.example.analysesdonnees.entity;

public class Rapport {

    private int id;

    private AnalyseDonnees donnees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnalyseDonnees getDonnees() {
        return donnees;
    }

    public void setDonnees(AnalyseDonnees donnees) {
        this.donnees = donnees;
    }
}

package com.example.analysesdonnees.entity;

public class FiltrageDeDonnees{
    private int iddonnees;
    private String colonne_filtrage;

    private String ligne_filtrage;

    private  String operator;
    private String value;

    public FiltrageDeDonnees(int iddonnees, String colonne_filtrage, String ligne_filtrage, String value, String operator) {
        this.iddonnees = iddonnees;
        this.colonne_filtrage = colonne_filtrage;
        this.ligne_filtrage = ligne_filtrage;
        this.value=value;
        this.operator=operator;
    }



    public int getIddonnees() {
        return iddonnees;
    }

    public void setIddonnees(int iddonnees) {
        this.iddonnees = iddonnees;
    }

    public String getColonne_filtage() {
        return colonne_filtrage;
    }

    public void setColonne_filtage(String colonne_filtage) {
        this.colonne_filtrage = colonne_filtage;
    }

    public String getLigne_filtrage() {
        return ligne_filtrage;
    }

    public void setLigne_filtrage(String ligne_filtrage) {
        this.ligne_filtrage = ligne_filtrage;
    }

    public String  getValue() {
        return value;
    }

    public void setValue(String  value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Boolean applyFilter(Donnees donnees) {
        switch (operator){
            case"EQ": //egaliter
                return donnees.getValeur(colonne_filtrage).equals(value);
            case"NEQ":
                return donnees.getValeur(colonne_filtrage).equals(value);
            case"GT":
                return donnees.getValeur(colonne_filtrage).equals(value);
//            case"CONTAINS":
//                return  donnees.getValeur(colonne_filtage)<=parseDouble(value) ;
        }
        return false;
    }
    public Boolean applyFilters(Donnees donnees){
        switch (operator){
            case"EQ": //egaliter
                return donnees.getValeur(ligne_filtrage).equals(value);
            case"NEQ":
                return donnees.getValeur(colonne_filtrage).equals(value);
            case"GT":
                return donnees.getValeur(colonne_filtrage).equals(value);
//            case"CONTAINS":
//                return  donnees.getValeur(colonne_filtage)<=parseDouble(value) ;
        }
        return false;
    }
}

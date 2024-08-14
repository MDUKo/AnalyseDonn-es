package com.example.analysesdonnees;

import com.example.analysesdonnees.entity.Donnees;
import com.example.analysesdonnees.service.AnalyseImpl;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AnalyseDeDonneeTest1 {
    @Test
    public void testConstructorWithAllParameters() {
        Donnees donnees = new Donnees(3.14, "value", "name", new Date(), "description", "category");
        assertEquals(3.14, donnees.getValeurNumerique(), 0.01);
        assertEquals("valeur", donnees.getValeur("valeur"));
        assertEquals("name", donnees.getNom_donnees());
        assertNotNull(donnees.getCreation_date());
        assertEquals("description", donnees.getDescription_donnees());
        assertEquals("category", donnees.getCategory_doonees());
    }

    @Test
    public void testDefaultConstructor() {
        Donnees donnees = new Donnees();
        assertEquals(0.0, donnees.getValeurNumerique(), 0.01);
        assertNull(donnees.getValeur(null));
        assertNull(donnees.getNom_donnees());
        assertNull(donnees.getCreation_date());
        assertNull(donnees.getDescription_donnees());
        assertNull(donnees.getCategory_doonees());
    }

    @Test
    public void testGettersAndSetters() {
        Donnees donnees = new Donnees();
        donnees.setValeurNumerique(3.14);
        assertEquals(3.14, donnees.getValeurNumerique(), 0.01);

        donnees.setValeur("value");
        assertEquals("value", donnees.getValeur("value"));

        donnees.setNom_donnees("name");
        assertEquals("name", donnees.getNom_donnees());

        Date creationDate = new Date();
        donnees.setCreation_date(creationDate);
        assertEquals(creationDate, donnees.getCreation_date());

        donnees.setDescription_donnees("description");
        assertEquals("description", donnees.getDescription_donnees());

        donnees.setCategory_doonees("category");
        assertEquals("category", donnees.getCategory_doonees());
    }
    @Test
    public void testNullValues() {
        Donnees donnees = new Donnees();
        donnees.setValeur(null);
        assertNull(donnees.getValeur(null));

        donnees.setNom_donnees(null);
        assertNull(donnees.getNom_donnees());

        donnees.setDescription_donnees(null);
        assertNull(donnees.getDescription_donnees());

        donnees.setCategory_doonees(null);
        assertNull(donnees.getCategory_doonees());
    }



        private AnalyseImpl donneesUtils;

        @BeforeEach
        void setUp() {
            donneesUtils = new AnalyseImpl(new ArrayList<>());
        }

        @Test
        void testRechargeDonnees() throws IOException, CsvValidationException {
            // Création d'un fichier CSV temporaire
            File fichierCSV = File.createTempFile("test", ".csv");
            try (FileWriter writer = new FileWriter(fichierCSV)) {
                writer.write("colonne1,colonne2\n");
                writer.write("1.0,2.5\n");
            }

            // Appel de la méthode à tester
            List<Donnees> donnees = donneesUtils .rechargeDonnees(fichierCSV.getAbsolutePath());

            // Vérifications
            assertEquals(1, donnees.size(), "Le nombre de lignes n'est pas correct");
            Donnees donnees1 = donnees.get(1);
            assertEquals(1.0, donnees1.getValeurNumerique(), "La valeur numérique n'est pas correcte");

            // Suppression du fichier temporaire
            fichierCSV.delete();
        }


}

package com.example.analysesdonnees.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Utilisateur{

    private String nom;

    private String prenom;

    private String email;
}

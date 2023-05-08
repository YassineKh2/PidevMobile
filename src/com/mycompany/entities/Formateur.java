/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.entities;

/**
 *
 * @author MsiAs
 */
public class Formateur {
    private int id;

    
    private String NomFormateur,PrenomFormateur,SexeFormateur;
    private String EmailFormateur;
    private int NumTelFormateur;
    private String ImageFormateur;

    public Formateur() {
    }
    
    public Formateur(int id, String NomFormateur, String PrenomFormateur, String SexeFormateur, String EmailFormateur, int NumTelFormateur, String ImageFormateur) {
            this.id = id;
            this.NomFormateur = NomFormateur;
            this.PrenomFormateur = PrenomFormateur;
            this.SexeFormateur = SexeFormateur;
            this.EmailFormateur = EmailFormateur;
            this.NumTelFormateur = NumTelFormateur;
            this.ImageFormateur = ImageFormateur;
        }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFormateur() {
        return NomFormateur;
    }

    public void setNomFormateur(String NomFormateur) {
        this.NomFormateur = NomFormateur;
    }

    public String getPrenomFormateur() {
        return PrenomFormateur;
    }

    public void setPrenomFormateur(String PrenomFormateur) {
        this.PrenomFormateur = PrenomFormateur;
    }

    public String getSexeFormateur() {
        return SexeFormateur;
    }

    public void setSexeFormateur(String SexeFormateur) {
        this.SexeFormateur = SexeFormateur;
    }

    public String getEmailFormateur() {
        return EmailFormateur;
    }

    public void setEmailFormateur(String EmailFormateur) {
        this.EmailFormateur = EmailFormateur;
    }

    public int getNumTelFormateur() {
        return NumTelFormateur;
    }

    public void setNumTelFormateur(int NumTelFormateur) {
        this.NumTelFormateur = NumTelFormateur;
    }

    public String getImageFormateur() {
        return ImageFormateur;
    }

    public void setImageFormateur(String ImageFormateur) {
        this.ImageFormateur = ImageFormateur;
    }
    
    
}

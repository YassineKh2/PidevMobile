/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.entities;

import java.util.ArrayList;

/**
 *
 * @author MsiAs
 */
public class Formation {
    private int id;
    private String NomFormation;
    private int NiveauFormation;
    private String ImageFormation,DescriptionFormation;
    private ArrayList<Formateur> formateur;
    private int idFormateur;
    private ArrayList<ModuleFormation> module;

    public Formation() {
    }

    public Formation(String NomFormation, int NiveauFormation, String ImageFormation, String DescriptionFormation, int idFormateur) {
        this.NomFormation = NomFormation;
        this.NiveauFormation = NiveauFormation;
        this.ImageFormation = ImageFormation;
        this.DescriptionFormation = DescriptionFormation;
        this.idFormateur = idFormateur;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }


    public Formation(String NomFormation, int NiveauFormation, String ImageFormation, String DescriptionFormation, ArrayList<Formateur> formateur) {
        this.NomFormation = NomFormation;
        this.NiveauFormation = NiveauFormation;
        this.ImageFormation = ImageFormation;
        this.DescriptionFormation = DescriptionFormation;
        this.formateur = formateur;
    }

    public Formation(String NomFormation, int NiveauFormation, String ImageFormation, String DescriptionFormation) {
        this.NomFormation = NomFormation;
        this.NiveauFormation = NiveauFormation;
        this.ImageFormation = ImageFormation;
        this.DescriptionFormation = DescriptionFormation;
    }
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the NomFormation
     */
    public String getNomFormation() {
        return NomFormation;
    }

    /**
     * @param NomFormation the NomFormation to set
     */
    public void setNomFormation(String NomFormation) {
        this.NomFormation = NomFormation;
    }

    /**
     * @return the NiveauFormation
     */
    public int getNiveauFormation() {
        return NiveauFormation;
    }

    /**
     * @param NiveauFormation the NiveauFormation to set
     */
    public void setNiveauFormation(int NiveauFormation) {
        this.NiveauFormation = NiveauFormation;
    }

    /**
     * @return the ImageFormation
     */
    public String getImageFormation() {
        return ImageFormation;
    }

    /**
     * @param ImageFormation the ImageFormation to set
     */
    public void setImageFormation(String ImageFormation) {
        this.ImageFormation = ImageFormation;
    }

    /**
     * @return the DescriptionFormation
     */
    public String getDescriptionFormation() {
        return DescriptionFormation;
    }

    /**
     * @param DescriptionFormation the DescriptionFormation to set
     */
    public void setDescriptionFormation(String DescriptionFormation) {
        this.DescriptionFormation = DescriptionFormation;
    }

    /**
     * @return the idFormateur
     */
    public ArrayList<Formateur> getFormateur() {
        return this.formateur;
    }

    
    public void setFormateur(ArrayList<Formateur> formateur) {
        this.formateur = formateur;
    }

    public ArrayList<ModuleFormation> getModule() {
        return module;
    }

    public void setModule(ArrayList<ModuleFormation> module) {
        this.module = module;
    }
    
    

    

}

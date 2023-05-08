/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.ArrayList;

/**
 *
 * @author yassine
 */
public class Categorie {
    
    private int id;
    private String NomCategorie;
    private String ImageCategorie;
    private ArrayList<Article> Articles;
    
    public Categorie() {
    }

    
    
    public Categorie(int id, String NomCategorie, String ImageCategorie) {
        this.id = id;
        this.NomCategorie = NomCategorie;
        this.ImageCategorie = ImageCategorie;
    }

    public Categorie(String NomCategorie, String ImageCategorie) {
        this.NomCategorie = NomCategorie;
        this.ImageCategorie = ImageCategorie;
    }

    public Categorie(int id, String NomCategorie, String ImageCategorie, ArrayList<Article> Articles) {
        this.id = id;
        this.NomCategorie = NomCategorie;
        this.ImageCategorie = ImageCategorie;
        this.Articles = Articles;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    public String getImageCategorie() {
        return ImageCategorie;
    }

    public void setImageCategorie(String ImageCategorie) {
        this.ImageCategorie = ImageCategorie;
    }

    public ArrayList<Article> getArticles() {
        return Articles;
    }

    public void setArticles(ArrayList<Article> Articles) {
        this.Articles = Articles;
    }
    
    
    
}

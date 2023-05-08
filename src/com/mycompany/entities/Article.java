/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author yassine
 */
public class Article {
    
   private int id,QuantiteArticle,idCategorie;
   private float RemisePourcentageArticle,PrixArticle;
   private String NomArticle,ImageArticle,ArticleDiscription;

    public Article() {
    }

    public Article(int id, int QuantiteArticle, float RemisePourcentageArticle, String NomArticle, Float PrixArticle, String ImageArticle, String ArticleDiscription,int idCategorie) {
        this.id = id;
        this.QuantiteArticle = QuantiteArticle;
        this.RemisePourcentageArticle = RemisePourcentageArticle;
        this.NomArticle = NomArticle;
        this.PrixArticle = PrixArticle;
        this.ImageArticle = ImageArticle;
        this.ArticleDiscription = ArticleDiscription;
        this.idCategorie = idCategorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantiteArticle(int QuantiteArticle) {
        this.QuantiteArticle = QuantiteArticle;
    }

    public void setRemisePourcentageArticle(float RemisePourcentageArticle) {
        this.RemisePourcentageArticle = RemisePourcentageArticle;
    }

    public void setNomArticle(String NomArticle) {
        this.NomArticle = NomArticle;
    }

    public void setPrixArticle(float PrixArticle) {
        this.PrixArticle = PrixArticle;
    }

    public void setImageArticle(String ImageArticle) {
        this.ImageArticle = ImageArticle;
    }

    public void setArticleDiscription(String ArticleDiscription) {
        this.ArticleDiscription = ArticleDiscription;
    }

    public int getIdCategorie() {
        return idCategorie;
    }
    
    public int getId() {
        return id;
    }

    public int getQuantiteArticle() {
        return QuantiteArticle;
    }

    public float getRemisePourcentageArticle() {
        return RemisePourcentageArticle;
    }

    public String getNomArticle() {
        return NomArticle;
    }

    public Float getPrixArticle() {
        return PrixArticle;
    }

    public String getImageArticle() {
        return ImageArticle;
    }

    public String getArticleDiscription() {
        return ArticleDiscription;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
   
   
}

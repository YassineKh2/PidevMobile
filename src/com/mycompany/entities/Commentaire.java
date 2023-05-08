/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Anas
 */
public class Commentaire {
    private int id;
    private Publication publication;
    private String date_commantaire, contenu_commantaire;

    public Commentaire() {
    }

    public Commentaire(String contenu_commantaire) {
        this.contenu_commantaire = contenu_commantaire;
    }
    
    

    public Commentaire(int id,String contenu_commantaire) {
        this.id=id;
        this.contenu_commantaire = contenu_commantaire;
    }
    

    public Commentaire(Publication publication, String date_commantaire, String contenu_commantaire) {
        this.publication = publication;
        this.date_commantaire = date_commantaire;
        this.contenu_commantaire = contenu_commantaire;
    }

    public Commentaire(int id,String date_commantaire, String contenu_commantaire) {
        this.id = id;
        this.date_commantaire = date_commantaire;
        this.contenu_commantaire = contenu_commantaire;
    }

    public int getId() {
        return id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getDate_commantaire() {
        return date_commantaire;
    }

    public String getContenu_commantaire() {
        return contenu_commantaire;
    }

    public void setContenu_commantaire(String contenu_commantaire) {
        this.contenu_commantaire = contenu_commantaire;
    }

    public void setDate_commantaire(String date_commantaire) {
        this.date_commantaire = date_commantaire;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @Override
    public String toString() {
        return "Commantaire_publication{" + "id=" + id + ", publication=" + publication + ", date_commantaire=" + date_commantaire + ", contenu_commantaire=" + contenu_commantaire + '}';
    }
                   
    
}

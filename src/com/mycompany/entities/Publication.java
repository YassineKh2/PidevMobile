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
public class Publication {
    private int id;
    private String date_publiaction, contenu_publication;
    private boolean is_approved = false;
    
    public Publication() {
    }

    public Publication(int id, String date_publiaction, String contenu_publication) {
        this.id = id;
        this.date_publiaction = date_publiaction;
        this.contenu_publication = contenu_publication;
    }

    public Publication(int id, String date_publiaction, String contenu_publication, boolean is_approved) {
        this.id = id;
        this.date_publiaction = date_publiaction;
        this.contenu_publication = contenu_publication;
        this.is_approved = false;
    }

    public Publication(String contenu_publication) {
        this.contenu_publication = contenu_publication;
    }

    public String getContenu_publication() {
        return contenu_publication;
    }

    public void setContenu_publication(String contenu_publication) {
        this.contenu_publication = contenu_publication;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }
    
    

    
    

    public int getId() {
        return id;
    }

    public String getDate_publiaction() {
        return date_publiaction;
    }

    

    

    @Override
    public String toString() {
        return "publication{" + "id=" + id + ", date_publiaction=" + date_publiaction + ", contenu_publication=" + contenu_publication ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_publiaction(String date_publiaction) {
        this.date_publiaction = date_publiaction;
    }

    public boolean getIs_approved() {
        return is_approved;
    }

    
    
    
}

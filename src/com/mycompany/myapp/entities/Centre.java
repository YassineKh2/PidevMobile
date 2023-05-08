/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class Centre {
    private int id;
    private String NomCentre,CapaciteCentre,localisation;
    private String img;

    public Centre() {
    }

    public Centre(int id, String NomCentre, String CapaciteCentre, String localisation, String img) {
        this.id = id;
        this.NomCentre = NomCentre;
        this.CapaciteCentre = CapaciteCentre;
        this.localisation = localisation;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCentre() {
        return NomCentre;
    }

    public void setNomCentre(String NomCentre) {
        this.NomCentre = NomCentre;
    }

    public String getCapaciteCentre() {
        return CapaciteCentre;
    }

    public void setCapaciteCentre(String CapaciteCentre) {
        this.CapaciteCentre = CapaciteCentre;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Centre{" + "id=" + id + ", NomCentre=" + NomCentre + ", CapaciteCentre=" + CapaciteCentre + ", localisation=" + localisation + ", img=" + img + '}';
    }

    public String getlocalisation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getimg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

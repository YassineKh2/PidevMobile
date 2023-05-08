/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.entities;

/**
 *
 * @author MsiAs
 */
public class ModuleFormation {
    int id;
    private String NomModule,Prerequis,Contenue,Durée;
    private Formation formation;

    public ModuleFormation() {
    }

    public ModuleFormation(String NomModule, String Prerequis, String contenue, String Durée, Formation formation) {
        this.NomModule = NomModule;
        this.Prerequis = Prerequis;
        this.Contenue = contenue;
        this.Durée = Durée;
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getNomModule() {
        return NomModule;
    }

    public void setNomModule(String NomModule) {
        this.NomModule = NomModule;
    }

    public String getPrerequis() {
        return Prerequis;
    }

    public void setPrerequis(String Prerequis) {
        this.Prerequis = Prerequis;
    }

    public String getContenue() {
        return Contenue;
    }

    public void setContenue(String Contenue) {
        this.Contenue = Contenue;
    }

    

    public String getDurée() {
        return Durée;
    }

    public void setDurée(String Durée) {
        this.Durée = Durée;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    
    
}



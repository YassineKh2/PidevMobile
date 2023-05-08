/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Categorie;
import com.mycompany.services.ServicesCategorie;

/**
 *
 * @author yassine
 */
public class ModifyCategorieForm extends BaseForm{
    
    Form current;
    public ModifyCategorieForm(Resources res, Categorie c){
           super("Newsfeed", BoxLayout.y());
          
        Toolbar tb = new Toolbar(true);

        current = this;
        setToolbar(tb);

        getTitleArea().setUIID("Container");
        setTitle("Ajoute Categorie");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField NomCategorie = new TextField(c.getNomCategorie(),"Nom",20,TextField.ANY);
        TextField ImageCategorie = new TextField(c.getNomCategorie(),"Image",20,TextField.ANY);
                
        NomCategorie.setUIID("NewsTopLine");
        ImageCategorie.setUIID("NewsTopLine");
        
        NomCategorie.setSingleLineTextArea(true);
        ImageCategorie.setSingleLineTextArea(true);
                
        Button btnModifer = new Button("Modifer");
        btnModifer.setUIID("Button");
        
        btnModifer.addPointerPressedListener( l -> {
            
            c.setNomCategorie(NomCategorie.getText());
            c.setImageCategorie(ImageCategorie.getText());
       
        
        
        if(ServicesCategorie.getInstance().ModifyCategorie(c)){
            new ListCategorieForum(res).show();
        }
         });
        Button btnAnuller = new Button("Annuler");
        
        btnAnuller.addActionListener(l -> {
            new ListCategorieForum(res).show();
            
        });
        
        Label l1 = new Label();
        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");
        
        Container content = BoxLayout.encloseY(
                l1,l2,
                new FloatingHint(NomCategorie),
                createLineSeparator(),
                new FloatingHint(ImageCategorie),
                createLineSeparator(),
                btnModifer,
                btnAnuller
        
        );
                
        add(content);       
         show();
    }
}

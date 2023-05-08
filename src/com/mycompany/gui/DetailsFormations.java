/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Formateur;
import com.mycompany.entities.Formation;
import com.mycompany.entities.ModuleFormation;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.ListFormations;
import java.util.ArrayList;

/**
 *
 * @author MsiAs
 */
public class DetailsFormations extends BaseForm{
    Form current;
    
    public DetailsFormations(Resources res,Formation r){
        
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Details");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        
        
        Label nomTxt = new Label("Nom Formation : "+r.getNomFormation(),"NewsTopLine2");
        Label niveauTxt = new Label("Niveau : "+r.getNiveauFormation(),"NewsTopLine2");
        Label ImgTxt = new Label("Image : ","NewsTopLine2" );
        Label DesTxt = new Label("Description : "+r.getDescriptionFormation(),"NewsTopLine2" );
        
        
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        ComboBox fmCombo = new ComboBox();
        ArrayList<Formateur> listOffm =r.getFormateur();
        ArrayList<ModuleFormation> listOfm =r.getModule();
        System.out.println(r.getModule());
        Label formateur = new Label("Formateur : ","NewsTopLine2" );
        for(Formateur fm:listOffm){
        
        fmCombo.addItem(fm.getNomFormateur());
        }
        Label module = new Label("Modules : ","NewsTopLine2" );
        ComboBox mCombo = new ComboBox();
        for(ModuleFormation mf: listOfm){
            mCombo.addItem(mf.getNomModule());
        }
        
        
        
        
        
        
        
        
       
       //appel fonction modfier reclamation men service
       
       Button btnAnnuler = new Button("Retour");
       btnAnnuler.addActionListener(e -> {
           new ListFormations(res).show();
       });
       
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                BoxLayout.encloseX(nomTxt),
                createLineSeparator(),
                BoxLayout.encloseX(niveauTxt),
                createLineSeparator(),
                
                BoxLayout.encloseX(DesTxt),
                createLineSeparator(),
                BoxLayout.encloseX(formateur),
                BoxLayout.encloseX(fmCombo), 
                createLineSeparator(),
                BoxLayout.encloseX(module),
                BoxLayout.encloseX(mCombo), 
                createLineSeparator(),
                btnAnnuler//ligne de séparation
                
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Centre;
import com.mycompany.myapp.services.ServiceCentre;
import java.util.ArrayList;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class CentreViewForm extends Form{
    Form current;
    public CentreViewForm(){
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Afficher Centres");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<Centre>list = ServiceCentre.getInstance().AfficherCentre();
        
        for(Centre c : list ) {
            
            Label NomCentreTxt = new Label("Nom Centre : "+c.getNomCentre());
            Label CapaciteCentreTxt = new Label("Capacite Centre : "+c.getCapaciteCentre());
            Label localisationTxt = new Label("localisation : "+c.getlocalisation());
            Label imgTxt = new Label("img : "+c.getimg());
            
            Label separator = new Label("**************************");
            
            Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            cn.add(NomCentreTxt);
            cn.add(CapaciteCentreTxt);
            cn.add(localisationTxt);
            cn.add(imgTxt);
           
            
            
            Label cSupprimer = new Label(" ");
            cSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(cSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            cSupprimer.setIcon(suprrimerImage);
            cSupprimer.setTextPosition(RIGHT);

            //click delete icon
            cSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if(dig.show("Suppression","Vous voulez supprimer ce Centre ?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                     }
                    //n3ayto l suuprimer men service Reclamation
                    if(ServiceCentre.getInstance().SupprimerCentre(c.getId())) {
                        new CentreViewForm().show();
                    }

            });
            
            //Update icon 
            Label cModifier = new Label(" ");
            cModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(cModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            cModifier.setIcon(mFontImage);
            cModifier.setTextPosition(LEFT);


            cModifier.addPointerPressedListener(l -> {
                new CentreEditForm(c).show();
            });


            cn.add(cSupprimer);
            cn.add(cModifier);
            cn.add(separator);

            add(cn);
        }
        
        add(viewContainer);
        
        
        

        
        
        
        getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new CentreForm().showBack();
        });
        
        /*FIN VIEW*/
    }

}

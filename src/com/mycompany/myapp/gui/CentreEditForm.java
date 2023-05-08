/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Centre;
import com.mycompany.myapp.services.ServiceCentre;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class CentreEditForm extends Form{
    
     public CentreEditForm(Centre c){
        
        Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container CnBtn=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnModifier=new Button("Modifier");
        Button btnAnnuler = new Button("Annuler");
        
        TextField nom = new TextField(c.getNomCentre() , "Nom Centre" , 20 , TextField.ANY);
        TextField duree = new TextField(c.getCapaciteCentre() , "CapaciteCentre" , 20 , TextField.ANY);
        TextField tenue= new TextField(String.valueOf(c.getlocalisation()) , "localisation" , 20 , TextField.ANY);
       
        TextField image = new TextField(String.valueOf(c.getimg()) , "img" , 20 , TextField.ANY);
        
        
        
        cn.add(nom);
        cn.add(duree);
        cn.add(tenue);
        
        cn.add(image);
       
        CnBtn.add(btnModifier);
        CnBtn.add(btnAnnuler);
        
        btnModifier.addPointerPressedListener(l ->   { 
            c.setNomCentre(nom.getText());
            c.setCapaciteCentre(duree.getText());
            c.setLocalisation(tenue.getText());
          
            c.setImg(image.getText());
          
            if(ServiceCentre.getInstance().ModifierCentre(c)) { // if true
                new CentreViewForm().show();
            }
        });
        
       
       btnAnnuler.addActionListener(e -> {
           new CentreViewForm().show();
       });
       
       
       add(cn);
       add(CnBtn);
    }
}

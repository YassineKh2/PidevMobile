/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Publication;
import com.mycompany.services.PublicationService;

/**
 *
 * @author Anas
 */
public class EditPub extends Form {
    Form current,previous;
    Resources res;
    public EditPub(Publication pub,Resources res){
         setTitle("Modifier");
             
              //TextField tfid=new TextField(String.valueOf(pub.getId()),"");
              TextField tfContenuPub = new TextField(String.valueOf(pub.getContenu_publication()),"Contenu Publication");
              
        
       
        //tfid.setSingleLineTextArea(false);      
        tfContenuPub.setSingleLineTextArea(true);
       
        Button btnU = new Button("modifier");
        btnU.setUIID("Button");
        
        btnU.setMaterialIcon(FontImage.MATERIAL_UPDATE);
         btnU.addActionListener(l->{
         //event.setId((int)Integer.parseInt(tfid.getText().toString()));
         pub.setContenu_publication(tfContenuPub.getText().toString());
        

     
    
         if( PublicationService.getInstance().EditPublication(pub)){
         Dialog.show("Succes", "Publication modifiée", new Command("OK"));
        new ListPubFront(current,res).show();
         }
       });
         
         Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListPubFront(current,res).show();
       });
       
       setLayout(BoxLayout.y());
       add(tfContenuPub).add(btnU).add(btnAnnuler);
       
       
        
        
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new ListPubFront(current,res).show());

         //add(content);
        show();
       
        
       
       
    }
}

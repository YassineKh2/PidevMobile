/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Centre;
import com.mycompany.myapp.services.ServiceCentre;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class CentreForm extends Form{
    Form current;
    public CentreForm() {
                
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Centre");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        
        
        
    
        /*AJOUT*/
        
        Container addContainer = new Container(new FlowLayout(CENTER,CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField NomCentre=new TextField("","Nom Centre");
        TextField CapaciteCentre=new TextField("","Capacite Centre");
        TextField localisation=new TextField("","localisation");
        TextField img=new TextField("","img");
       
        
        addContainer.add(NomCentre);
        addContainer.add(CapaciteCentre);
        addContainer.add(localisation);
        addContainer.add(img);
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        btnContainer.setLayout(BoxLayout.y());
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(NomCentre.getText().equals("") || CapaciteCentre.getText().equals("") || localisation.getText().equals("") || img.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                        
                    //njibo iduser men session (current user)
                    Centre a = new Centre(
                            30,
                            String.valueOf(NomCentre.getText()),
                            String.valueOf(CapaciteCentre.getText()),
                            String.valueOf(localisation.getText()),
                            String.valueOf(img.getText())
                           
                                  );
                    
                    System.out.println("data Centre == "+a);
                    
                    
                    
                    ServiceCentre.getInstance().AjouterCentre(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    refreshTheme();//Actualisation
                    Dialog.show("Rehab Radar","Ajout avec Succés","Annuler","OK");
                }
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        });
        
        ListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CentreViewForm().show();
            }
        });
        
        add(addContainer);
        add(btnContainer);
        
        
        
        
        
        /*FIN AJOUT*/
        
        
        
        /*NAVBAR*/
        getToolbar()
                .addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
            }
        } );
        getToolbar()
                .addMaterialCommandToSideMenu("Activités",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CentreForm().show();
            }
        } );
        
        
    }
}

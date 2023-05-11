/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Centre;
import com.mycompany.services.ServiceCentre;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class CentreForm extends BaseFormBack{
    Form current;
    public CentreForm(Resources res) {
                
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Centre");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        
         Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();
        
     addTab(swipe, s1, res.getImage("Add tasks-bro.png"), ",", "", res);
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
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
                new CentreViewForm(res).show();
            }
        });
        
        add(addContainer);
        add(btnContainer);
        
        
        
        
        
        /*FIN AJOUT*/
        
        
        
        /*NAVBAR*/
        
        getToolbar()
                .addMaterialCommandToSideMenu("Activités",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CentreForm(res).show();
            }
        } );
        
        
    }
    
    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
           int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
           
           if(image.getHeight() < size){
               image = image.scaledHeight(size);
           }
           
           if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
               image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
           }
           
           ScaleImageLabel imageScale = new ScaleImageLabel(image);
           imageScale.setUIID("Container");
           imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
           
           Label overLay = new Label("","ImageOverlay");
           
           Container page1 = LayeredLayout.encloseIn(
           imageScale,overLay,BorderLayout.south(
                   BoxLayout.encloseY(
                   new SpanLabel(text,"LargeWhiteText"),
                                 spacer
            )
           )
          );
           
           swipe.addTab("", res.getImage("Add tasks-bro.png"),page1);
           
    }
}

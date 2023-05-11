/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Publication;
import com.mycompany.services.PublicationService;

/**
 *
 * @author Anas
 */
public class EditPub extends BaseForm {
    Form current,previous;
    Resources res;
    public EditPub(Publication pub,Resources res){
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
       
        
       
       super.addSideMenu(res);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Formateur;
import com.mycompany.entities.Formation;
import com.mycompany.entities.ModuleFormation;
import java.util.ArrayList;

/**
 *
 * @author MsiAs
 */
public class DetailsFormations extends BaseFormBack{
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
        tb.addSearchCommand(e -> {

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

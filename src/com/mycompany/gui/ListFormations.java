/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Formation;
import com.mycompany.gui.AjoutFormation;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceFormation;
import java.util.ArrayList;

/**
 *
 * @author MsiAs
 */
public class ListFormations extends BaseForm{
    Form current;
    public ListFormations(Resources res){
    super("NewsfeedForm", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Formation");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Formations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton ajout = RadioButton.createToggle("Ajouter", barGroup);
        ajout.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
            
            new ListFormations(res).show();
            refreshTheme();
        });
        ajout.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg = ip.showInifiniteBlocking();
        
            
         new AjoutFormation(res).show();
            refreshTheme();
        });
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, ajout),
                FlowLayout.encloseBottom(arrow)
        ));
        
        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(ajout, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
//


        ArrayList<Formation>list =(ArrayList)ServiceFormation.getInstance().affichageFormations();
        
        for(Formation rec : list ) {
             String urlImage ="back-logo.jpeg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,rec,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }

        

    

    

    }
    
    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        if(image.getHeight()< size){
            image = image.scaledHeight(size);
        }

        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");

            Container page1 =
                    LayeredLayout.encloseIn(imageScale,
                            overLay,
                            BorderLayout.south(
                                    BoxLayout.encloseY(
                            new SpanLabel(text,"LargeWhiteText"),
                                    spacer
                            )
                            )
                    );

            swipe.addTab("",res.getImage("news-tab-down-arrow.png"), page1);

            }
    private void addButton(Image img,Formation rec , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
        Label nomTxt = new Label("Nom Formation : "+rec.getNomFormation(),"NewsTopLine2");
        Label niveauTxt = new Label("Niveau : "+rec.getNiveauFormation(),"NewsTopLine2");
        Label ImgTxt = new Label("Image : "+rec.getImageFormation(),"NewsTopLine2" );
        Label DesTxt = new Label("Description : "+rec.getDescriptionFormation(),"NewsTopLine2" );
        
        
        createLineSeparator();
        
        
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cette Formation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceFormation.getInstance().deleteFormation(rec.getId())) {
                    new ListFormations(res).show();
                }
           
        });
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifyFormations(res,rec).show();
        });
        //show icon
        Label lShow = new Label(" ");
        lShow.setUIID("NewsTopLine");
        Style showStyle = new Style(lShow.getUnselectedStyle());
        showStyle.setFgColor(0xf7ad04);
        
        FontImage sFontImage = FontImage.createMaterial(FontImage.MATERIAL_LIST, showStyle);
        lShow.setIcon(sFontImage);
        lShow.setTextPosition(LEFT);
        
        
        lShow.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new DetailsFormations(res,rec).show();
        });
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                
                BoxLayout.encloseX(nomTxt),
                BoxLayout.encloseX(niveauTxt),
                BoxLayout.encloseX(ImgTxt),
                BoxLayout.encloseX(lModifier,lSupprimer,lShow)));
        
        
        
        add(cnt);
        
    }
    
   
    }


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
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
import com.mycompany.entities.Categorie;
import com.mycompany.services.ServicesCategorie;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yassine
 */
public class ListCategorieForum extends BaseFormBack {

    Form current;

    public ListCategorieForum(Resources res) {
          super("Newsfeed", BoxLayout.y());
          
        Toolbar tb = new Toolbar(true);

        current = this;
        setToolbar(tb);

        getTitleArea().setUIID("Container");
        setTitle("Ajoute Categorie");
        getContentPane().setScrollVisible(false);

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

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Categories", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        ArrayList<Categorie> List = ServicesCategorie.getInstance().ShowCategorie();

        for (Categorie categ : List) {
            String urlImage ="Add tasks-bro.png";
            
            Image plcaeHolder = Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(plcaeHolder, false);
            URLImage urlimg = URLImage.createToStorage(enc,urlImage, urlImage, URLImage.RESIZE_SCALE);
                
            addButton(urlimg,categ,res);;
            
            ScaleImageLabel image = new ScaleImageLabel(urlimg);
            
            Container ContainerImg = new Container();
            
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }

    }

    private void addStringValue(String name, Component comp) {
        add(BorderLayout.west(new Label(name, "PaddedLabel"))
                .add(BorderLayout.CENTER, comp));
        add(createLineSeparator(0xeeeeee));
    }



    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }

        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");

        Container page1 = LayeredLayout.encloseIn(
                imageScale, overLay, BorderLayout.south(
                        BoxLayout.encloseY(
                                new SpanLabel(text, "LargeWhiteText"),
                                spacer
                        )
                )
        );

        swipe.addTab("", res.getImage("Add tasks-bro.png"), page1);

    }

    public void bindButtonSelection(Button btn, Label l) {
        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });

    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    private void addButton(Image img,Categorie categ,Resources res) {

        
        // Show categorie
        
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);

        Label nomCategorieLabel = new Label(categ.getNomCategorie(),"NewsTopLine2");
        
        createLineSeparator();
        
       

       
        // Delete categorie
           Label lSuprimer = new Label(" ");
           lSuprimer.setUIID("NewsTopLine");
           Style supprimerStyle = new Style(lSuprimer.getUnselectedStyle());
           supprimerStyle.setFgColor(0xf21f1f);
           
           FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE,supprimerStyle);
           lSuprimer.setIcon(supprimerImage);
           lSuprimer.setTextPosition(RIGHT);
           
        // Event listenner  
           lSuprimer.addPointerPressedListener(l ->{
               
               Dialog dig = new Dialog("Suppression");
               
               if(Dialog.show("Suppression","Voullez vous supprimer cette categorie?","Anuller","Confirmer")){
                    dig.dispose();
               }
               else{
                   dig.dispose();
                   if(ServicesCategorie.getInstance().DeleteCateogie(categ.getId())){
                        
                       new ListCategorieForum(res).show();
                       
                   }
               }
               
           });
        
           
           
           // Update Categorie
           
           Label Lupdate = new Label(" ");
           Lupdate.setUIID("NewsTopLine");
           Style updateStyle = new Style(Lupdate.getUnselectedStyle());
           updateStyle.setFgColor(0xf7ad02);
           
           FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT,updateStyle);
           Lupdate.setIcon(mFontImage);
           Lupdate.setTextPosition(LEFT);
           
           Lupdate.addPointerPressedListener( l -> {
              new ModifyCategorieForm(res, categ).show();
               
           });
           
           
           
           
           
           //Show image
           
            EncodedImage enc = null;
           Image imgs;
           ImageViewer  imgv;
           String url = "http://127.0.0.1:8000/"+categ.getImageCategorie();
        try {
            enc = EncodedImage.create("/NoImageFound.png");
        } catch (IOException ex) {
            
        }
        System.out.println(url);
           imgs = URLImage.createToStorage(enc, url, url,URLImage.RESIZE_SCALE);
           imgv = new ImageViewer(imgs);
           
           
           
           
                     cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
            BoxLayout.encloseX(nomCategorieLabel,Lupdate,lSuprimer,imgv)));
        
        add(cnt);
    }
}

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
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Article;
import com.mycompany.entities.Categorie;
import com.mycompany.services.ServicesCategorie;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yassine
 */
public class ListArticle extends BaseForm{
    
   Form current;
    public ListArticle(Resources res,Categorie categ){
   super("Newsfeed", BoxLayout.y());
          
        Toolbar tb = new Toolbar(true);

        current = this;
        setToolbar(tb);

        getTitleArea().setUIID("Container");
        setTitle("Les Categorie");
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
        RadioButton liste = RadioButton.createToggle("Articles", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Article", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            new ListCategorie(res).show();
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
            updateArrowPosition(liste, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        ArrayList<Categorie> List = ServicesCategorie.getInstance().ShowCategorie();

        
            String urlImage ="Add tasks-bro.png";
            
            Image plcaeHolder = Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(plcaeHolder, false);
            URLImage urlimg = URLImage.createToStorage(enc,urlImage, urlImage, URLImage.RESIZE_SCALE);
                
            for(Article Arc : categ.getArticles())
            {
            addButtonArticle(urlimg,Arc,res);
            }
            ScaleImageLabel image = new ScaleImageLabel(urlimg);
            
            Container ContainerImg = new Container();
            
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
            super.addSideMenu(res);
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

    private void addButtonArticle(Image img,Article Arc,Resources res) {

        
        // Show categorie
        
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);

        Label nomArticleLabel = new Label(Arc.getNomArticle(),"NewsTopLine2");
        Label PrixArticleLabel = new Label(Arc.getPrixArticle().toString()+"DT","NewsTopLine2");
        
       
       
        createLineSeparator();
        
        //Show articles
        
           Label LShow = new Label(" ");
           LShow.setUIID("NewsTopLine");
           Style updateShow = new Style(LShow.getUnselectedStyle());
           updateShow.setFgColor(0xf7ad02);
          
           
           FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_INFO,updateShow);
           LShow.setIcon(mFontImage);
           LShow.setTextPosition(LEFT);
           
           LShow.addPointerPressedListener( l -> {
              new ShowArticle(res, Arc).show();
               
           });
       

           
           //Show image
           
            EncodedImage enc = null;
           Image imgs;
           ImageViewer  imgv;
           String url = "http://127.0.0.1:8000/"+Arc.getImageArticle();
        try {
            enc = EncodedImage.create("/NoImageFound.png");
        } catch (IOException ex) {
            
        }
       
           imgs = URLImage.createToStorage(enc, url, url,URLImage.RESIZE_SCALE);
           imgv = new ImageViewer(imgs);
           
           
           
           
                     cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                              BoxLayout.encloseX(nomArticleLabel,PrixArticleLabel),
                                BoxLayout.encloseX(imgv,LShow)
                     ));
                             
        
        add(cnt);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.ButtonGroup;
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
import java.util.ArrayList;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class CentreViewForm extends BaseFormBack {

    Form current;

    public CentreViewForm(Resources res) {
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);

        tb.setTitle("Afficher Centres");
        getContentPane().setScrollVisible(true);

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

        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Centre> list = ServiceCentre.getInstance().AfficherCentre();
         super.addSideMenu(res);
          getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt1) -> {
            new CentreForm(res).showBack();
        });
        for (Centre c : list) {

            Label NomCentreTxt = new Label("Nom Centre : " + c.getNomCentre());
            Label CapaciteCentreTxt = new Label("Num Centre : " + c.getCapaciteCentre());
            Label localisationTxt = new Label("localisation : " + c.getLocalisation());
            Label imgTxt = new Label("img : " + c.getImg());

            Label separator = new Label("**************************");

            Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

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

                if (dig.show("Suppression", "Vous voulez supprimer ce Centre ?", "Oui", "Annuler")) {
                    //n3ayto l suuprimer men service Reclamation
                if (ServiceCentre.getInstance().SupprimerCentre(c.getId())) {
                    new CentreViewForm(res).show();
                }
                    dig.dispose();
                } else {
                    dig.dispose();
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
                new CentreEditForm(c, res).show();
            });

            cn.add(cSupprimer);
            cn.add(cModifier);
            cn.add(separator);

            add(cn);
            
        }

        add(viewContainer);

      

        /*FIN VIEW*/
       
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
}

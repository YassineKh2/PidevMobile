/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Formateur;
import com.mycompany.entities.Formation;
import com.mycompany.services.ServiceFormateur;
import com.mycompany.services.ServiceFormation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MsiAs
 */
public class ModifyFormations extends BaseFormBack {

    Form current;
    int idf = 0;

    public ModifyFormations(Resources res, Formation r) {

        super("Newsfeed", BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier");
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

        
        
        TextField nomTxt = new TextField(r.getNomFormation(), "Nom Formation", 20, TextField.ANY);
        TextField description = new TextField(r.getDescriptionFormation(), "Description", 20, TextField.ANY);
        TextField imgTxt = new TextField(r.getImageFormation(), "Image", 20, TextField.ANY);
        TextField niveau = new TextField(String.valueOf(r.getNiveauFormation()), "Niveau", 20, TextField.ANY);

        ComboBox fmCombo = new ComboBox();

        ArrayList<Formateur> listOffm = (ArrayList) ServiceFormateur.getInstance().affichageFormateurs();
        for (Formateur fm : listOffm) {

            fmCombo.addItem(fm.getNomFormateur() +" "+ fm.getPrenomFormateur());
            if (fm.getId() == r.getFormateur().get(0).getId()) {
                fmCombo.setSelectedItem(fm.getNomFormateur() + " " + fm.getPrenomFormateur());
            }
        }

        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        nomTxt.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        imgTxt.setUIID("NewsTopLine");
        niveau.setUIID("NewsTopLine");

        nomTxt.setSingleLineTextArea(true);
        imgTxt.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        niveau.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {
            for (Formateur fm : listOffm) {
                if ((fm.getNomFormateur() + " " + fm.getPrenomFormateur()).equals(fmCombo.getSelectedItem())) {
                    idf = fm.getId();
                }
            }
            r.setNomFormation(nomTxt.getText());
            r.setDescriptionFormation(description.getText());
            r.setImageFormation(imgTxt.getText());
            r.setNiveauFormation(Integer.parseInt(niveau.getText()));
            r.setIdFormateur(idf);

            //appel fonction modfier reclamation men service
            if (ServiceFormation.getInstance().modifierFormation(r)) { // if true
                new ListFormations(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
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
                new FloatingHint(nomTxt),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(imgTxt),
                createLineSeparator(),//ligne de séparation
                new FloatingHint(niveau),
                createLineSeparator(),//ligne de séparation
                fmCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
        );

        add(content);
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

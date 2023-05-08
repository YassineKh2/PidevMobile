/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Formateur;
import com.mycompany.entities.Formation;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceFormateur;

import com.mycompany.services.ServiceFormation;
import java.util.ArrayList;

/**
 *
 * @author MsiAs
 */
public class AjoutFormation extends BaseForm {

    int idf = 0;
    Form current;

    public AjoutFormation(Resources res) {

        super("NewsfeedForm", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Formation");
        getContentPane().setScrollVisible(false);

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

        ajout.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(ajout, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(ajout, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
//

        TextField nom = new TextField("", "Entrer Nom Formation");
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);

        TextField niveau = new TextField("", "Entrer Niveau Formation");
        niveau.setUIID("TextFieldBlack");
        addStringValue("Niveau", niveau);

        TextField img = new TextField("", "ImageFormation");
        img.setUIID("TextFieldBlack");
        addStringValue("Image", img);

        TextField description = new TextField("", "Entrer Description Formation");
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);

        ComboBox fmCombo = new ComboBox();
        fmCombo.setUIID("ComboBox");
        addStringValue("Formateur", fmCombo);

        ArrayList<Formateur> listOffm = (ArrayList) ServiceFormateur.getInstance().affichageFormateurs();
        for (Formateur fm : listOffm) {
            fmCombo.addItem(fm.getNomFormateur() + " " + fm.getPrenomFormateur());
        }

        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        btnAjouter.addActionListener((e) -> {
            try {
                if (nom.getText() == "" || img.getText() == "" || description.getText() == "") {
                    Dialog.show("veiller confirmer les Données", "", "Annuler", "OK");

                } else {
                    for (Formateur fm : listOffm) {
                        if ((fm.getNomFormateur() + " " + fm.getPrenomFormateur()).equals(fmCombo.getSelectedItem())) {
                            idf = fm.getId();
                        }
                    }
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();

                    int Nv = Integer.parseInt(String.valueOf(niveau.getText()).toString());

                    Formation f = new Formation(String.valueOf(nom.getText()).toString(), Nv, String.valueOf(img.getText()).toString(), String.valueOf(description.getText()).toString(), idf);
                    ServiceFormation.getInstance().ajoutFormation(f);
                    iDialog.dispose();
                    refreshTheme();
                }
            } catch (Exception xe) {
                xe.printStackTrace();
            }
        });

    }

    private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
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

}

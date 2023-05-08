/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commentaire;
import com.mycompany.entities.Publication;
import com.mycompany.services.CommentaireServive;

/**
 *
 * @author Anas
 */
public class AddComm extends Form {

    public AddComm(Form previous,Resources res) {
         setTitle("Ajouter");
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Publication pub=new Publication();
        TextField tfContenuCom = new TextField("", "Contenu commentaire");

        Button btnValider = new Button("Ajouter");
        btnValider.setMaterialIcon(FontImage.MATERIAL_ADD_TASK);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((tfContenuCom.getText().length() == 0)) {
                    Dialog.show("Alert", "svp Remplir les champs", new Command("OK"));
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog idialog = ip.showInfiniteBlocking();
                    Commentaire c = new Commentaire(
                            String.valueOf(tfContenuCom.getText()));
                    System.err.println("data commm==" + c);
                    
                    CommentaireServive.getInstance().AddCommentaire(pub,c);
                    idialog.dispose();
                    Dialog.show("Succes", "Commentaire ajouté", new Command("OK"));
                    new ListPubFront(previous,res).show();
                }
            }

        });
        addAll(tfContenuCom,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListPubFront(previous,res).show());

    }

    }
    


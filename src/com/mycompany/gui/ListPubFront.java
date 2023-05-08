/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Publication;
import com.mycompany.services.PublicationService;
import java.util.ArrayList;

/**
 *
 * @author Anas
 */
public class ListPubFront extends BaseForm {

    ArrayList<Publication> data = new ArrayList<>();
    Form current;
    Resources res;

    public ListPubFront(Form previous,Resources res) {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());



        setTitle("List pubs");
        Picker datePicker = new Picker();

        data = PublicationService.getInstance().ShowPubs();
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container yy = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container sort = new Container(new BoxLayout(BoxLayout.X_AXIS));
        // Button SortName = new Button("Sort by name");
        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xxx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xxxx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container debut = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container fin = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Publication p = new Publication();

            p.setId(data.get(i).getId());
            p.setContenu_publication(data.get(i).getContenu_publication());
            p.setDate_publiaction(data.get(i).getDate_publiaction());

            Button EditPub = new Button("Modifier");
            Button DeletePub = new Button("Supprimer");
            
            Label separation = new Label("----------------------------");
            // Label IdPub=new Label(""+data.get(i).getId());
            Label Contenu = new Label("Contenue Publication: " + data.get(i).getContenu_publication());
            Label DatePub = new Label("Date de publication: " + data.get(i).getDate_publiaction().substring(0, 10));

            DeletePub.setMaterialIcon(FontImage.MATERIAL_DELETE);
            DeletePub.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println(p.toString());
                    PublicationService.getInstance().DeletePublication(p.getId());
                    Dialog.show("Succes", "Publication Supprimée", new Command("OK"));
                    new ListPubFront(current,res).show();
                }

            });
            EditPub.setMaterialIcon(FontImage.MATERIAL_UPDATE);
            EditPub.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("ahawa" + p);
                    new EditPub(p,res);
                }
            });

            

            x.addAll(Contenu);
            xx.addAll(DatePub);
            xxx.addAll(EditPub, DeletePub);
            
            y.addAll(separation, x, xx, xxx, xxxx);
            
        }

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> new AddPub(current,res).show());
        // SortName.addActionListener(e -> new SortEventByName(current).show());
        //.addAll(SortName);
        // yy.addAll(sort);
        //addAll(yy);
        addAll(y);

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_TO_HOME_SCREEN, e->new ProfileForm(res).show());
        addSideMenu(res);
    }
}

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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
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
        super.addSideMenu(res);
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
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

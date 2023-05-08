/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class HomeForm extends Form{
    
    public HomeForm(){
        
        Container homeContainer = new Container(new FlowLayout(CENTER,CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Button centre = new Button("Centre");
        setLayout(BoxLayout.y());
        setTitle("Rehab Radar");
        
        Label welcome = new Label("Welcome To Rehab Radar");
        
        
        centre.addActionListener(e-> new CentreForm().show());
        
        
        homeContainer.add(welcome);
        
        btnContainer.add(centre);
        
        
        
        add(homeContainer);
        add(btnContainer);
        
        
        
        
        
        getToolbar()
                .addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
            }
        } );
        getToolbar()
                .addMaterialCommandToSideMenu("Centres",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CentreForm().show();
            }
        } );
        
        
        
    }
}

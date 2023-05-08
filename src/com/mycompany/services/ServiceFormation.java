/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Formateur;
import com.mycompany.entities.Formation;
import com.mycompany.entities.ModuleFormation;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author MsiAs
 */
public class ServiceFormation {
    public static ServiceFormation instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceFormation getInstance() {
        if(instance == null )
            instance = new ServiceFormation();
        return instance ;
    }
    
    
    
    public ServiceFormation() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutFormation(Formation formation) {
        
        String url =Statics.BASE_URL+"/AddFormationMobile?NomFormation="+formation.getNomFormation()+"&NiveauFormation="+formation.getNiveauFormation()+"&ImageFormation="+formation.getImageFormation()+"&DescriptionFormation="+formation.getDescriptionFormation()+"&idFormateur="+formation.getIdFormateur(); // aa sorry n3adi getId lyheya mech ta3 user ta3 Formation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Formation>affichageFormations() {
        ArrayList<Formation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AllFormationMobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapFormations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapFormations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Formation re = new Formation();
                        ArrayList<Formateur> fr = new  ArrayList<Formateur>();
                        ArrayList<ModuleFormation> md = new  ArrayList<ModuleFormation>();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nomFormation = obj.get("NomFormation").toString();
                        float niveauFormation = Float.parseFloat(obj.get("NiveauFormation").toString());
                        String imageFormation = obj.get("ImageFormation").toString();
                        String description = obj.get("DescriptionFormation").toString();
                        List<Map<String,Object>> listOffm = (List<Map<String,Object>>) obj.get("Formateur");
                        List<Map<String,Object>> listOfm = (List<Map<String,Object>>) obj.get("modules");
                        
                        for(Map<String,Object> formateur: listOffm)
                        {
                            
                            Formateur fr1=new Formateur();
                            float idF = Float.parseFloat(formateur.get("id").toString());
                            
                            String nomFormateur = formateur.get("NomFormateur").toString();
                            String prenomFormateur = formateur.get("PrenomFormateur").toString();
                            String emailFormateur = formateur.get("EmailFormateur").toString();
                            float numTelFormateur = Float.parseFloat(formateur.get("NumTelFormateur").toString());
                            String imageFormateur = formateur.get("ImageFormateur").toString();
                            String sexeFormateur = formateur.get("SexeFormateur").toString();
                            

                            fr1.setId((int)idF);
                            fr1.setNomFormateur(nomFormateur);
                            fr1.setPrenomFormateur(prenomFormateur);
                            fr1.setEmailFormateur(emailFormateur);
                            fr1.setNumTelFormateur((int)numTelFormateur);
                            fr1.setImageFormateur(imageFormateur);
                            fr1.setSexeFormateur(sexeFormateur);
                            //System.out.println(fr1);

                            fr.add(fr1);

                        }
                        
                        for(Map<String,Object> module: listOfm)
                        {
                            
                            ModuleFormation m1=new ModuleFormation();
                            float idm = Float.parseFloat(module.get("id").toString());
                            
                            String nomModule = module.get("NomModule").toString();
                            String preRequis = module.get("PrerequisModule").toString();
                            String duree = module.get("DureeModule").toString();
                            
                            String contenue = module.get("ContenuModule").toString();
                            
                            

                            m1.setId((int)idm);
                            m1.setNomModule(nomModule);
                            m1.setPrerequis(preRequis);
                            m1.setDurée(duree);
                            
                            m1.setContenue(contenue);
                            
                            //System.out.println(fr1);

                            md.add(m1);

                        }
                        
                        
                            
                        
                   
                        
                        re.setId((int)id);
                        re.setNomFormation(nomFormation);
                        re.setNiveauFormation((int)niveauFormation);
                        re.setImageFormation(imageFormation);
                        
                        re.setDescriptionFormation(description);
                        re.setFormateur(fr);
                        re.setModule(md);
                        
                        
                        
                        
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
    //Detail Formation bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Formation DetailFormation( int id , Formation formation) {
        
        String url = Statics.BASE_URL+"/FindFormationMobile/"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                formation.setNomFormation(obj.get("NomFormation").toString());
                formation.setNiveauFormation(Integer.parseInt(obj.get("NiveauFormation").toString()));
                formation.setImageFormation(obj.get("ImageFormation").toString());
                formation.setDescriptionFormation(obj.get("DescriptionFormation").toString());
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return formation;
        
        
    }
    
    
    //Delete 
    public boolean deleteFormation(int id ) {
        String url = Statics.BASE_URL +"/DeleteFormationMobile?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierFormation(Formation formation) {
        String url = Statics.BASE_URL +"/ModifyFormationMobile/"+formation.getId()+"?NomFormation="+formation.getNomFormation()+"&NiveauFormation="+formation.getNiveauFormation()+"&ImageFormation="+formation.getImageFormation()+"&DescriptionFormation="+formation.getDescriptionFormation()+"&idFormateur="+formation.getIdFormateur();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    

    
}


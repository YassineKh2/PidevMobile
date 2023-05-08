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
import com.mycompany.entities.Formateur;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassine
 */
public class ServiceFormateur {
    public static ServiceFormateur instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceFormateur getInstance() {
        if(instance == null )
            instance = new ServiceFormateur();
        return instance ;
    }
    
    
    
    public ServiceFormateur() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutFormateur(Formateur Formateur) {
        
        String url =Statics.BASE_URL+"/AddFormateurMobile?NomFormateur="+Formateur.getNomFormateur()+"&PrenomFormateur="+Formateur.getPrenomFormateur()+"&ImageFormateur="+Formateur.getImageFormateur()+"&SexeFormateur="+Formateur.getSexeFormateur()+"&NumTelFormateur="+Formateur.getNumTelFormateur()+"&EmailFormateur="+Formateur.getEmailFormateur(); // aa sorry n3adi getId lyheya mech ta3 user ta3 Formateur
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Formateur>affichageFormateurs() {
        ArrayList<Formateur> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AllFormateurMobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapFormateurs = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapFormateurs.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Formateur re = new Formateur();
                        
                        
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nomFormateur = obj.get("NomFormateur").toString();
                      //  int numtel = Integer.parseInt(obj.get("NumTelFormateur").toString());
                        String imageFormateur = obj.get("ImageFormateur").toString();
                        String Sexe = obj.get("SexeFormateur").toString();
                        String prenom = obj.get("PrenomFormateur").toString();
                        String email = obj.get("EmailFormateur").toString();
                        
                        
                        
                        
                        
                        re.setId((int)id);
                        re.setNomFormateur(nomFormateur);
                       // re.setNumTelFormateur(numtel);
                        re.setImageFormateur(imageFormateur);
                        
                        re.setPrenomFormateur(prenom);
                        re.setEmailFormateur(email);
                        re.setSexeFormateur(Sexe);
                        
                        
                        
                        
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
    
    
    
    //Detail Formateur bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Formateur DetailFormateur( int id , Formateur Formateur) {
        
        String url = Statics.BASE_URL+"/FindFormateurMobile/"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                Formateur.setNomFormateur(obj.get("NomFormateur").toString());
                Formateur.setNumTelFormateur(Integer.parseInt(obj.get("NumTelFormateur").toString()));
                Formateur.setImageFormateur(obj.get("ImageFormateur").toString());
                Formateur.setSexeFormateur(obj.get("SexeFormateur").toString());
                Formateur.setEmailFormateur(obj.get("EmailFormateur").toString());
                Formateur.setPrenomFormateur(obj.get("PrenomFormateur").toString());
                
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return Formateur;
        
        
    }
    
    
    //Delete 
    public boolean deleteFormateur(int id ) {
        String url = Statics.BASE_URL +"/DeleteFormateurMobile?id="+id;
        
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
    public boolean modifierFormateur(Formateur Formateur) {
        String url = Statics.BASE_URL +"/ModifyFormateurMobile/"+Formateur.getId()+"?NomFormateur="+Formateur.getNomFormateur()+"&PrenomFormateur="+Formateur.getPrenomFormateur()+"&ImageFormateur="+Formateur.getImageFormateur()+"&SexeFormateur="+Formateur.getSexeFormateur()+"&EmailFormateur="+Formateur.getEmailFormateur()+"&NumTelFormateur="+Formateur.getNumTelFormateur();
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

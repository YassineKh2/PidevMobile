/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Centre;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MOHAMED MHAMDI
 */
public class ServiceCentre {
    //singleton
    public static ServiceCentre instance = null;
    
    private ConnectionRequest req;
    
    public static boolean resultOk = true;
    
    public static ServiceCentre getInstance(){
        if(instance == null)
            instance = new ServiceCentre();
        return instance;
    }
    
    public ServiceCentre(){
        req = new ConnectionRequest();
    }
    
    public void AjouterCentre(Centre centre){
        
        String url=Statics.BASE_URL+"/addcentreJSON/new?NomCentre="+centre.getNomCentre()+
                "&CapaciteCentre="+centre.getCapaciteCentre()+
                "&Localisation="+centre.getLocalisation()+
                "&Img="+centre.getImg();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    //Affichage des Activites
    public ArrayList<Centre>AfficherCentre(){
        ArrayList<Centre> result =new ArrayList<>();
        
        String url =Statics.BASE_URL+"/Allcentres";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                
                jsonp=new JSONParser();
                
                try{
                    Map<String,Object>mapCentres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapCentres.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps){
                        Centre a=new Centre();
                        
                        float id=Float.parseFloat(obj.get("id").toString());
                        
                        String nomCentre = obj.get("NomCentre").toString();
                        String capaciteCentre = obj.get("CapaciteCentre").toString();
                        String localisation = obj.get("localisation").toString();
                        String img = obj.get("img").toString();
                        
                        
                        a.setId((int)id);
                        a.setNomCentre(nomCentre);
                        a.setCapaciteCentre(capaciteCentre);
                        a.setLocalisation(localisation);
                        a.setImg(img);
                        
                        result.add(a);
                        
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
    }
    
   
    public boolean SupprimerCentre(int id ) {
        String url = Statics.BASE_URL +"/deletecentreJSON/"+id;
        
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
    
    
    //Modification Activite
    public boolean ModifierCentre(Centre centre) {
        String url = Statics.BASE_URL +"/updatecentreJSON/"+centre.getId()+"?NomCentre="+centre.getNomCentre()+
                "&CapaciteCentre="+centre.getCapaciteCentre()+
                "&Localisation="+centre.getLocalisation()+
                "&Img="+centre.getImg();

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

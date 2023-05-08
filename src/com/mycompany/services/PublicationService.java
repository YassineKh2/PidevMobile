/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Publication;
import java.util.List;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Anas
 */
public class PublicationService {

    public static PublicationService instance = null;
    private ConnectionRequest req;
    ArrayList<Publication> publist = new ArrayList<>();
    public static boolean resultOK;

    public static PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;

    }

    public PublicationService() {
        req = new ConnectionRequest();
    }

    public ArrayList<Publication> getList(String json) {

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> pubs = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(pubs);

            //List<Map<String, Object>> list = (List<Map<String, Object>>) (Map<String, Object>) (List<Map<String, Object>>) pubs.get("root");
            Object rootObj = pubs.get("root");

            List<Map<String, Object>> list = (List<Map<String, Object>>) rootObj;
            for (Map<String, Object> obj : list) {
                Publication pub = new Publication();

                float id = Float.parseFloat(obj.get("id").toString());
                String contenu_publication = obj.get("ContenuPublication").toString();
                System.out.println(contenu_publication);
                pub.setDate_publiaction(obj.get("DatePublication").toString());
                pub.setId((int) id);
                pub.setContenu_publication(contenu_publication);

                publist.add(pub);

            }

        } catch (IOException ex) {
        }

        return publist;

    }

    /*public ArrayList<Publication>ShowPubs(){
        ArrayList<Publication> result = new ArrayList<>();         
        String url = Statics.BASE_URL+"/forumjson/all";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
            JSONParser json ;
                json = new JSONParser();
            
            try{
            Map<String,Object>mapPubs = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));          
            List<Map<String,Object>> listofMaps = (List<Map<String,Object>>) mapPubs.get("root");
   
            
            for(Map<String,Object> obj : listofMaps){
                Publication pub = new Publication();
                
                float id= Float.parseFloat(obj.get("id").toString());
                        String contenu_publication=obj.get("contenu_publication").toString();
                        System.out.println(contenu_publication);
                        pub.setDate_publiaction(obj.get("date_publication").toString());
                        pub.setId((int)id );
                        pub.setContenu_publication(contenu_publication);
                
                result.add(pub);
            }
            }catch(Exception ex)
            {
                ex.printStackTrace();
            
            }}            
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;
    }*/

    public ArrayList<Publication> ShowPubs() {
        String url = Statics.BASE_URL + "/forumjson/all";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PublicationService ser = new PublicationService();
                publist = ser.getList(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publist;
    }

    public void AddPublication(Publication pub) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/forumjson/new?contenu_publication=" + pub.getContenu_publication() + "&is_approved=" + pub.getIs_approved();
        req.setUrl(url);
        req.addResponseListener(a -> {
            String str = new String(req.getResponseData());
            System.err.println("data==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean DeletePublication(int id) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/forumjson/delete/" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean EditPublication(Publication pub) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/forumjson/edit/" + pub.getId() + "?contenu_publication=" + pub.getContenu_publication();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public Publication ShowOnePub(Publication pub) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/forumjson/" + pub.getId();
        req.setUrl(url);
        Publication p = new Publication();
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> list = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                pub.setId((int) Integer.parseInt(list.get("id").toString()));
                pub.setContenu_publication((String) list.get("contenu_publication").toString());
                pub.setDate_publiaction(list.get("date_publiaction").toString());

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return p;

    }

}

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
import com.mycompany.entities.Commentaire;
import com.mycompany.entities.Publication;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anas
 */
public class CommentaireServive {

    public static boolean resultOK;
    ArrayList<Commentaire> commentlist = new ArrayList<>();
    public static CommentaireServive instance = null;
    private ConnectionRequest req;
       public static CommentaireServive getInstance() {
        if (instance == null) {
            instance = new CommentaireServive();
        }
        return instance;

    }

    public CommentaireServive(){
        req = new ConnectionRequest();
    }

    public ArrayList<Commentaire> getList(String json) {

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> Com = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("yallaaaa" + Com);

            //List<Map<String, Object>> list = (List<Map<String, Object>>) commentaires.get("root");
            Object rootObj = Com.get("root");

            List<Map<String, Object>> list = (List<Map<String, Object>>) rootObj;
            System.out.println(list);
            for (Map<String, Object> obj : list) {
               Commentaire com = new Commentaire();

               Map<String, Object> pubMap = (Map<String, Object>) obj.get("publication");
                float idP = Float.parseFloat(pubMap.get("id").toString());
                String contenu_publication = pubMap.get("ContenuPublication").toString();
                String date_publiaction = pubMap.get("DatePublication").toString();
                System.out.println(contenu_publication + date_publiaction);

                Publication p = new Publication((int) idP, date_publiaction, contenu_publication);
                System.out.println("pub mtaa commm hedha" + p.toString());

                float idCom = Float.parseFloat(obj.get("id").toString());
               // String DateCommantaire = obj.get("DateCommantaire").toString();
               // System.out.println(DateCommantaire);
                String ContenuCommantaire = obj.get("ContenuCommantaire").toString();
                System.out.println(ContenuCommantaire);
               // Commentaire c = new Commentaire((int) idCom, DateCommantaire, ContenuCommantaire);
                com.setPublication(p);

                com.setId((int) idCom);
                com.setDate_commantaire(obj.get("DateCommantaire").toString());
                com.setContenu_commantaire(ContenuCommantaire);

                commentlist.add(com);
               
            }
             System.out.println(commentlist);

        } catch (IOException ex) {
        }

        return commentlist;

    }
       public ArrayList<Commentaire> ShowComments(){
         //ConnectionRequest req = new ConnectionRequest();
        //SessionManager.getId();
        String url="http://127.0.0.1:8000/jsoncommantaire/publication/all" ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentaireServive ser = new CommentaireServive();
                commentlist = ser.getList(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentlist;
         
            }

    public void AddCommentaire(Publication pub,Commentaire com) {
        ConnectionRequest req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/jsoncommantaire/publication/new/"+pub.getId()+"?contenu_commantaire="+com.getContenu_commantaire();
        req.setUrl(url);
        req.addResponseListener(a -> {
            String str = new String(req.getResponseData());
            System.err.println("data==" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean DeleteCommentaire(int id) {
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

    public boolean EditCommentaire(Commentaire com) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/forumjson/edit/" + com.getId() + "?contenu_Commentaire=" + com.getContenu_commantaire();
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

   /* public Commentaire ShowOneComment(Commentaire com) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/jsoncommantaire/publication/" + com.getId();
        req.setUrl(url);
        Commentaire c = new Commentaire();
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> list = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                com.setId((int) Integer.parseInt(list.get("id").toString()));
                com.setContenu_commantaire((String) list.get("contenu_commantaire").toString());
                com.setDate_commantaire(list.get("date_commantaire").toString());

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return c;

    }*/
}

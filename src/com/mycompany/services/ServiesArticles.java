/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Article;
import com.mycompany.utils.Statics;

/**
 *
 * @author yassine
 */
public class ServiesArticles {
    public static ServiesArticles instance = null;
    
    public static boolean resultOk = true;
    
    private ConnectionRequest req;

    public static ServiesArticles getInstance() {
        if (instance == null) {
            instance = new ServiesArticles();
        }
        return instance;
    }

    public ServiesArticles() {
        req = new ConnectionRequest();
    }
    
     public void AddArticle(Article Article) {

        String url = Statics.BASE_URL + "/AddArticleJson?NomArticle=" + Article.getNomArticle() + "&PrixArticle=" + Article.getPrixArticle() +"&QuantiteArticle=" + Article.getQuantiteArticle() + "&ImageArticle=" + Article.getImageArticle() + "&ArticleDiscription=" + Article.getArticleDiscription() + "&RemisePourcentageArticle=" + Article.getRemisePourcentageArticle() + "&IdCategorie=" + Article.getIdCategorie();

        req.setUrl(url);

        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());
            System.out.println("Data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }  
     
       public boolean ModifyArticle(Article Article){
        String url = Statics.BASE_URL + "/ModifyArticleJson/"+ Article.getId() +"?NomArticle=" + Article.getNomArticle() + "&PrixArticle=" + Article.getPrixArticle() +"&QuantiteArticle=" + Article.getQuantiteArticle() + "&ImageArticle=" + Article.getImageArticle() + "&ArticleDiscription=" + Article.getArticleDiscription() + "&RemisePourcentageArticle=" + Article.getRemisePourcentageArticle() + "&IdCategorie=" + Article.getIdCategorie();
        req.setUrl(url);
        
       
        req.addResponseListener(new ActionListener<NetworkEvent>(){
        
            @Override
            public void actionPerformed(NetworkEvent evt){
                resultOk = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
        }
       
         
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
    }
           public boolean ModifyArticleQuantite(Article Article){
        String url = Statics.BASE_URL + "/ModifyArticleJsonQuantite/"+ Article.getId() +"?QuantiteArticle=" + Article.getQuantiteArticle();
        req.setUrl(url);
        
       
        req.addResponseListener(new ActionListener<NetworkEvent>(){
        
            @Override
            public void actionPerformed(NetworkEvent evt){
                resultOk = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
        }
       
         
    });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
    }
}

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
import com.mycompany.entities.Article;
import com.mycompany.entities.Categorie;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author yassine
 */
public class ServicesCategorie {

    public static ServicesCategorie instance = null;
    
    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServicesCategorie getInstance() {
        if (instance == null) {
            instance = new ServicesCategorie();
        }
        return instance;
    }

    public ServicesCategorie() {
        req = new ConnectionRequest();
    }

    public void AddCategorie(Categorie Categorie) {

        String url = Statics.BASE_URL + "/AddCategorieJson?NomCategorie=" + Categorie.getNomCategorie() + "&ImageCategorie=Back/images/CategorieImages/" + Categorie.getImageCategorie();

        req.setUrl(url);

        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());
            System.out.println("Data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Categorie> ShowCategorie() {

        ArrayList<Categorie> result = new ArrayList<>();
        
        String url = Statics.BASE_URL + "/AllCategorieJson";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser json ;
                json = new JSONParser();
            
            try{
                Map<String,Object>mapCategorie = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapCategorie.get("root");
                   //System.out.println(listOfMaps);
               
                for(Map<String,Object> categ: listOfMaps){
                    Categorie categorie = new Categorie();
                   
                     ArrayList<Article> Articles = new  ArrayList<Article>();
                     
                    float id = Float.parseFloat(categ.get("id").toString());
                    
                    String NomCategorie = categ.get("NomCategorie").toString();
                    
                    String ImageCategorie = categ.get("ImageCategorie").toString();
                    
                     List<Map<String,Object>> listOfArticles = (List<Map<String,Object>>) categ.get("Articles");
                    
                   
                    for(Map<String,Object> article: listOfArticles)
                    {
                         Article Arc = new Article();
                        float Remise;
                        float Quantite;
                        float Prix;
                        float idA =  Float.parseFloat(article.get("id").toString());
                        Arc.setId((int)idA);
                        Arc.setIdCategorie((int)id);
                        Arc.setImageArticle(article.get("ImageArticle").toString());
                        Arc.setNomArticle(article.get("NomArticle").toString());
                        
                        try{
                        Prix =  Float.parseFloat(article.get("PrixArticle").toString());
                        }catch(Exception ex){
                         Prix = 0;
                        }
                        Arc.setPrixArticle(Prix);
                        
                        try{
                        Quantite = Float.parseFloat(article.get("QuantiteArticle").toString());
                        }catch(Exception ex){
                         Quantite = 0;
                        }
                        Arc.setQuantiteArticle((int)Quantite);
                        
                        try {
                         Remise = Float.parseFloat(article.get("RemisePourcentageArticle").toString());
                        }catch(Exception ex){
                         Remise = 0;
                        }
                        Arc.setRemisePourcentageArticle(Remise);
                       
                            
                        Arc.setArticleDiscription(article.get("ArticleDiscription").toString());
                       
                      // System.out.println(Quantite);
                       Articles.add(Arc); 
                    }
                    
                    categorie.setId((int)id);
                    categorie.setNomCategorie(NomCategorie);
                    categorie.setImageCategorie(ImageCategorie);
                    categorie.setArticles(Articles);
                    
                    result.add(categorie);
                    
                }
            }catch(Exception ex) {
                    ex.printStackTrace();
                }
            
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    return result;
    
    }
    
    public Categorie GetSingleCategorie(int id, Categorie categorie ){
        
        String url = Statics.BASE_URL+"/FindCategorieJson/"+id;
        req.setUrl(url);
      
        String str = new String(req.getResponseData());
            req.addResponseListener( evt -> {
                
                JSONParser json = new JSONParser();
               
                           try{
                Map<String,Object>mapCategorie = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapCategorie.get("root");
                
               
                for(Map<String,Object> categ: listOfMaps){
                    
                  
                     ArrayList<Article> Articles = new  ArrayList<Article>();
                     
                    float idC =  Float.parseFloat(categ.get("id").toString()); 
                            
                    String NomCategorie = categ.get("NomCategorie").toString();
                    
                    String ImageCategorie = categ.get("ImageCategorie").toString();
                    
                     List<Map<String,Object>> listOfArticles = (List<Map<String,Object>>) categ.get("Articles");
                    
                    for(Map<String,Object> article: listOfArticles)
                    {
                         Article Arc = new Article();
                        float Remise;
                        float Quantite;
                        float Prix;
                        float idA =  Float.parseFloat(article.get("id").toString());
                        Arc.setId((int)idA);
                        Arc.setIdCategorie((int)id);
                        Arc.setImageArticle(article.get("ImageArticle").toString());
                        Arc.setNomArticle(article.get("NomArticle").toString());
                        
                        try{
                        Prix =  Float.parseFloat(article.get("PrixArticle").toString());
                        }catch(Exception ex){
                         Prix = 0;
                        }
                        Arc.setPrixArticle(Prix);
                        
                        try{
                        Quantite = Float.parseFloat(article.get("QuantiteArticle").toString());
                        }catch(Exception ex){
                         Quantite = 0;
                        }
                        Arc.setQuantiteArticle((int)Quantite);
                        
                        try {
                         Remise = Float.parseFloat(article.get("RemisePourcentageArticle").toString());
                        }catch(Exception ex){
                         Remise = 0;
                        }
                        Arc.setRemisePourcentageArticle(Remise);
                       
                            
                        Arc.setArticleDiscription(article.get("ArticleDiscription").toString());
                       
                      // System.out.println(Quantite);
                       Articles.add(Arc); 
                    }
                    
                    categorie.setId((int)idC);
                    categorie.setNomCategorie(NomCategorie);
                    categorie.setImageCategorie(ImageCategorie);
                    categorie.setArticles(Articles);
                    
                    if(categorie.getId() == id)
                        break;
                }
            }catch(Exception ex) {
                    ex.printStackTrace();
                }
            
            });
          return categorie;

    
 
    
    }   
    
    public Categorie GetSingleCategorieById (int id )
    {
        Categorie categorie = new Categorie();
       String url = Statics.BASE_URL+"/FindCategorieJson/"+id;
        req.setUrl(url); 
        
        String str = new String(req.getResponseData());
            req.addResponseListener( evt -> {
                
                JSONParser json = new JSONParser();
                System.out.println("test");
                try{
                       Map<String,Object>mapCategorie = json.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                         ArrayList<Article> Articles = new  ArrayList<Article>();
                      
                       
                       List<Map<String,Object>> listOfArticles = (List<Map<String,Object>>) mapCategorie.get("Articles");
                       for(Map<String,Object> article: listOfArticles)
                    {
                         Article Arc = new Article();
                        float Remise;
                        float Quantite;
                        float Prix;
                        float idA =  Float.parseFloat(article.get("id").toString());
                        Arc.setId((int)idA);
                        Arc.setIdCategorie((int)id);
                        Arc.setImageArticle(article.get("ImageArticle").toString());
                        Arc.setNomArticle(article.get("NomArticle").toString());
                        
                        try{
                        Prix =  Float.parseFloat(article.get("PrixArticle").toString());
                        }catch(Exception ex){
                         Prix = 0;
                        }
                        Arc.setPrixArticle(Prix);
                        
                        try{
                        Quantite = Float.parseFloat(article.get("QuantiteArticle").toString());
                        }catch(Exception ex){
                         Quantite = 0;
                        }
                        Arc.setQuantiteArticle((int)Quantite);
                        
                        try {
                         Remise = Float.parseFloat(article.get("RemisePourcentageArticle").toString());
                        }catch(Exception ex){
                         Remise = 0;
                        }
                        Arc.setRemisePourcentageArticle(Remise);
                       
                            
                        Arc.setArticleDiscription(article.get("ArticleDiscription").toString());
                       
                      // System.out.println(Quantite);
                       Articles.add(Arc); 
                    }
                      
                       categorie.setNomCategorie(mapCategorie.get("NomCategorie").toString());
                       categorie.setImageCategorie(mapCategorie.get("ImageCategorie").toString());
                       categorie.setArticles(Articles);
                       
                } catch (IOException ex) {
                    System.out.println("Erreur code :"+ ex.getMessage());
            }
                
          
                
            });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return categorie;
    }   
    
    public boolean DeleteCateogie(int id) {
        String url = Statics.BASE_URL+"/DeleteCategorieJson/"+id;
        
        req.setUrl(url);
        
        
            req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
      
        
        return resultOk;
    }
    
    public boolean ModifyCategorie(Categorie categorie){
        String url = Statics.BASE_URL +"/ModifyCategorieJson/" + categorie.getId() + "?NomCategorie="+ categorie.getImageCategorie() + "&ImageCategorie=" + categorie.getImageCategorie();
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

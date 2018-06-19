/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class CarteService {
    
     private CarteDAO cDao = new CarteDAO();
      private JoueurDAO jDao = new JoueurDAO();
       private PartieDAO pDAO = new PartieDAO();
     
   public Carte piocherCarte(Joueur joueur){

       
        Carte carte = new Carte();
                  Carte.typeCarte[] tabTypesCartes = Carte.typeCarte.values();
                  Random r = new Random();
                  int n = r.nextInt(tabTypesCartes.length);
                  carte.setIngredient(tabTypesCartes[n]);
                 carte.setJoueurProprietaire(joueur);
                 
                 cDao.addCarte(carte);
           return carte;
   }
   
   
   public Carte lancerSort (long  idCarte1, long idCarte2){
       Carte carte = new Carte();
       
       cDao.SelectionnerCarte(idCarte1);
       cDao.SelectionnerCarte(idCarte2);
       
       String compareCarte1 = carte.getIngredient().toString();
       String compareCarte2 = carte.getIngredient().toString();
       
       if(compareCarte1 == "BAVE_DE_CRAPAUD" && compareCarte2 =="CORNE_DE_LICORNE"  ){
       
   }
       
       
       
       
         return carte;
   }

    
}

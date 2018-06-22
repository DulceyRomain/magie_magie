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
import atos.magiemagie.entity.Partie;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class PartieService {
    
    private PartieDAO dao = new PartieDAO();
    private JoueurDAO jDao = new JoueurDAO();
    private CarteDAO cDao = new CarteDAO();
     
    CarteService cs = new CarteService();
    
    /**
     * 
     * Liste les parties dont aucun joueur n'est à l'etat à la main
     * ou GAGNE
     * 
     */
    public List<Partie> listerPartiesNonDemarrees(){
        return dao.listerPartiesNonDemarrees();
        
    }
    
    public Partie demarrerPartie(long idPartie){
        Partie p = dao.rechercheParID(idPartie); // rechercher Partie par id
        
         // Vérification nb de joueurs
         
        if(p.getJoueurs().size()< 2){
           throw new RuntimeException("Nombre de joueur insuffisant") ;
        }
        else{
          //MAJ Etat Joueur
          for ( Joueur j : p.getJoueurs()){
            if(j.getOrdre()== 1L){
                j.setEtatJoueur(Joueur.typeEtatJoueur.A_LA_MAIN);
                jDao.modifier(j);
            }
            for(int i=0;i<7;i++){
               //Distribution des cartes
                 cs.piocherCarte(j);
            }     
            }
        }     
        return p;
    }
    
    public Partie creerNouvellePartie(String nom){
      
        Partie p = new Partie();
        p.setNom(nom);
        dao.creerNouvellePartie(p);
        return p;
    }
    
   
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Joueur;
import static atos.magiemagie.entity.Joueur_.pseudo;
import atos.magiemagie.entity.Partie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Administrateur
 */
public class JoueurService {
    
    private JoueurDAO dao = new JoueurDAO();
    private PartieDAO partieDAO = new PartieDAO();
    private CarteDAO cDao = new CarteDAO();
    private CarteService cs = new CarteService();
    
    public Joueur rejoindrePartie(String pseudo,String avatar, long idPartie){
       
        // Recherche si le joueur existe déjà
        
        Joueur joueur = dao.rejoindreParPseudo(pseudo);
        if(joueur==null){
          //le joueur n'existe pas encire
          joueur = new Joueur();
          joueur.setPseudo(pseudo);
        }
        joueur.setAvatar(avatar);
        joueur.setEtatJoueur(Joueur.typeEtatJoueur.PAS_LA_MAIN);
        long ordre =dao.definirOrdre(idPartie);
        joueur.setOrdre(ordre);
        
        //Associe le joueur à la partie et vice-versa (JPA relations bidirectionellements)
        Partie p = partieDAO.rechercheParID(idPartie)  ;
        
        joueur.setpartie(p);
        List<Joueur> listeJoueurs= p.getJoueurs();
        listeJoueurs.add(joueur);
        
        if(joueur.getId()==null){
            dao.ajouter(joueur);
         }
        else{
            dao.modifier(joueur);
        }
        return joueur;   
    }
    
    
    
    
    public void passerSonTour(String pseudo, long idPartie){
        //appel du joueur 
        Partie p = partieDAO.rechercheParID(idPartie);
        Joueur j = j=dao.rejoindreParPseudo(pseudo);
        
       // pioche d'une carte
        cs.piocherCarte(j);
        this.passerAuJoueurSuivant(idPartie);
        
    
    
}
    
    
    
    public void passerAuJoueurSuivant(long idPartie){
        
        Joueur joueurALaMain = partieDAO.rechJoueurQuiALaMainId(idPartie);
        Partie p = partieDAO.rechercheParID(idPartie);
        
        //Determine si les autres joueurs ont perdu
        //et passe le joueur a l'état gagné
        // puis quitte la fonction
        if(dao.determinerSiResteUnJoueur(idPartie) ){
            joueurALaMain.setEtatJoueur(Joueur.typeEtatJoueur.GAGNE);
            dao.modifier(joueurALaMain);
            return ;
        }
        
        joueurALaMain.setEtatJoueur(Joueur.typeEtatJoueur.PAS_LA_MAIN);
        dao.modifier(joueurALaMain);
        Joueur jSuivant = new Joueur();
        long ordre = joueurALaMain.getOrdre()+ 1L; // Passer au  joueur suivant
        long max = p.getJoueurs().size();
        //Reviens à 0 quand le dernier joueur passe au joueur suivant
        if(ordre >max){
            jSuivant = dao.rechOrdre(1L, idPartie);
        }else{
            jSuivant = dao.rechOrdre(ordre, idPartie);
        }
        //Si l'état du joueur est différent de PAS_LA_MAIN
      
        
      
        while(jSuivant.getEtatJoueur() != Joueur.typeEtatJoueur.PAS_LA_MAIN){
                if(jSuivant.getEtatJoueur() == Joueur.typeEtatJoueur.SOMMEIL_PROFOND){
                    jSuivant.setEtatJoueur(Joueur.typeEtatJoueur.PAS_LA_MAIN);
                    dao.modifier(jSuivant);
                    ordre++;
                    if(ordre >max){
                    jSuivant = dao.rechOrdre(1L, idPartie);
                    }else{
                    jSuivant = dao.rechOrdre(ordre, idPartie);
                 }
                }else if(jSuivant.getEtatJoueur() == Joueur.typeEtatJoueur.PERDU){
                    ordre++;
                    if(ordre >max){
                    jSuivant = dao.rechOrdre(1L, idPartie);
                    }else{
                    jSuivant = dao.rechOrdre(ordre, idPartie);              
                    }
                 
                    
        }
}
       jSuivant.setEtatJoueur(Joueur.typeEtatJoueur.A_LA_MAIN);
      dao.modifier(jSuivant); 
}
}

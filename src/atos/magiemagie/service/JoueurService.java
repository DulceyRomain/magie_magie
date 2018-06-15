/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

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
    public void rejoindrePartie(String pseudo,String avatar, long idPartie){
       
        // Recherche si le joueur existe déjà
        
        Joueur joueur = dao.rejoindreParPseudo(pseudo);
        if(joueur==null){
          //le joueur n'existe pas encire
          joueur = new Joueur();
          joueur.setPseudo(pseudo);
        }
        joueur.setAvatar(avatar);
        joueur.setEtatJoueur(Joueur.typeEtatJoueur.PAS_LA_MAIN);
        long ordre =dao.recupOrdreMaxPlusUnJoueur(idPartie);
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
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class PartieService {
    
    private PartieDAO dao = new PartieDAO();
    
    
    public List<Partie> listerPartiesNonDemarrees(){
        return dao.listerPartiesNonDemarrees();
        
    }
    
    public Partie creerNouvellePartie(String nom){
      
        Partie p = new Partie();
        p.setNom(nom);
        dao.creerNouvellePartie(p);
        return p;
    }
    
   
    
    
}

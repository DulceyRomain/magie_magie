/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.PartieService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class PartieServiceTest {
    
     private  PartieService ps = new PartieService();
    
  //  @Test
    public void test() {
         
        // Créer Nouvelle Partie
        Partie p = ps.creerNouvellePartie("indinahui");
        assertNotNull(p.getId());
        
        
    }
    
   @Test
    public void demarrerPartieOK(){
        
      //  long id = ps.creerNouvellePartie("Partie 1").getId();
       
        ps.demarrerPartie(1L);
        
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.CarteService;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import java.util.Random;
import org.eclipse.persistence.jpa.jpql.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    
    private JoueurService joueurService = new JoueurService();
    private PartieService partieService = new PartieService();
    private CarteService carteService = new CarteService();
    //@Test
    public void rejoindrePartieOk(){
      
      //  Partie nouvellePartie = partieService.creerNouvellePartie("partie 1");
        joueurService.rejoindrePartie("romain", "Ang", 1);
        joueurService.rejoindrePartie("sdfsdfsdf", "Alkghgfjhng", 1);
        joueurService.rejoindrePartie("rohgghjhgghjghghjghjghjmain", "Asdfbdffgng", 1);
        joueurService.rejoindrePartie("main", "Asdf", 1);
    }
    
  //  @Test
    public void passerTourOk(){

       // service.passerSonTour("romain", 1L);
        //service.passerSonTour("sdfsdfsdf", 1L);
      //  service.passerSonTour("rohgghjhgghjghghjghjghjmain", 1L);
      //  service.passerSonTour("rohgghjhgghjghghjghjghjmain", 1L);
        joueurService.passerAuJoueurSuivant(1L);
    }
    
    @Test
    public void lancerSort(){
        //carteService.lancerSort(3, 4, 1, 17, 18);
        carteService.lancerSort(3,1, 19, 20);
    }
    
   // @Test
    public void ordreJoueurOk(){
        Partie nouvellePartie = partieService.creerNouvellePartie("ordreJoueurOK");
        joueurService.rejoindrePartie("A", "A", nouvellePartie.getId());
        joueurService.rejoindrePartie("B", "B", nouvellePartie.getId());
        Joueur j = joueurService.rejoindrePartie("C", "C", nouvellePartie.getId());
        
        assertEquals(3L,(long)j.getOrdre());
         
        }
    
    
}

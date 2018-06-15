/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import org.junit.Test;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    
    private JoueurService service = new JoueurService();
    private PartieService partieService = new PartieService();
    
    @Test
    public void rejoindrePartieOk(){
      
        Partie nouvellePartie = partieService.creerNouvellePartie("partie 1");
        service.rejoindrePartie("romain", "Ang", nouvellePartie.getId());
        service.rejoindrePartie("sdfsdfsdf", "Alkghgfjhng", nouvellePartie.getId());
        service.rejoindrePartie("rohgghjhgghjghghjghjghjmain", "Asdfbdffgng", nouvellePartie.getId());
    }
    
}

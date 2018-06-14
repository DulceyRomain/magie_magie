/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class magieTest {
    
    @Test
    public void demarreJPA() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
         /*em.getTransaction().begin();
         
         Partie p1 = new Partie();
         p1.setNom("Partie");
         em.persist(p1);
         
         Joueur j1 = new Joueur();
         j1.setPartieActuelle(p1);
         em.persist(j1);
         
         Joueur j2 = new Joueur();
         j1.setPartieActuelle(p1);
         em.persist(j2);
         
         Joueur j3 = new Joueur();
         j1.setPartieActuelle(p1);
         em.persist(j3);
         
         Joueur j4 = new Joueur();
         j1.setPartieActuelle(p1);
         em.persist(j4);
         
         Carte c1 = new Carte();
         c1.setJoueurProprietaire(j1);
         em.persist(c1);
         
         em.getTransaction().commit();*/
    }
    
}

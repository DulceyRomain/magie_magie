/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class PartieDAO {
    
    
    public List<Partie> listerPartiesNonDemarrees(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
     
     
     Query query = em.createQuery("SELECT p FROM Partie p EXCEPT SELECT p FROM Partie p JOIN p.joueurs j WHERE j.etatJoueur IN (:etat_gagne, :etat_alamain)");
        query.setParameter("etat_gagne", Joueur.typeEtatJoueur.GAGNE);
        query.setParameter("etat_alamain", Joueur.typeEtatJoueur.A_LA_MAIN);
        return query.getResultList();
    }
    
    public void creerNouvellePartie(Partie p){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();    
    }

    public Partie rechercheParID(long idPartie) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
       return em.find(Partie.class, idPartie);
    }
    
    public Joueur rechJoueurQuiALaMainId(long partieId){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
         Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partie p WHERE p.id =:idPartie AND j.etatJoueur=:etat");
        query.setParameter("idPartie",partieId);
        query.setParameter("etat",Joueur.typeEtatJoueur.A_LA_MAIN);
        return (Joueur) query.getSingleResult();
        
     
    }
    
    
}

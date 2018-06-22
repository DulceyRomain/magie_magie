/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    private JoueurDAO jDao = new JoueurDAO();
    private PartieDAO partieDAO = new PartieDAO();
    private Carte carte = new Carte();
    public void addCarte ( Carte carte){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.persist(carte);
        em.getTransaction().commit();
    }
    
    public Carte rechercherUneCarte (long idCarte,long idJoueurLanceur){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
         Query query = em.createQuery("SELECT c  FROM Carte c JOIN c.joueurProprietaire j WHERE c.id =:idCarte AND j.id=:idJoueurLanceur");
         query.setParameter("idCarte", idCarte);
         query.setParameter("idJoueurLanceur", idJoueurLanceur);
         Object res = query.getSingleResult();
    
        return  (Carte) res;        
    }
    
    public List<Carte> rechercherCartesParId (long idJoueur){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
         Query query = em.createQuery("SELECT c FROM Carte c JOIN c.joueurProprietaire j WHERE j.id=:idJoueur");
         query.setParameter("idJoueurLanceur", idJoueur);
         Object res = query.getSingleResult();
    
        List<Carte> cartes = query.getResultList();
        return cartes;
    }

    public List<Joueur> listeAutresJoueursParPartieId(long idJoueurAExclure, long idPartie) {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        Query query = em.createQuery("SELECT j FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie "
                                    + "EXCEPT "
                                    + "SELECT j FROM Joueur j JOIN j.partie p WHERE p.id=:idPartie AND j.id=:idJoueurAExclure");
        query.setParameter("idPartie", idPartie);
        query.setParameter("idJoueurAExclure", idJoueurAExclure);
        
        List<Joueur> joueurs = query.getResultList();
        
        return joueurs;
    }
    
    
    
    
      public void SupprCarte (Carte c){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin(); 
        em.remove(em.find(Carte.class, c.getId()));
        em.getTransaction().commit();
        
    }
      
      public void modifierC(Carte carte){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.merge(carte);
        em.getTransaction().commit();
      }
}

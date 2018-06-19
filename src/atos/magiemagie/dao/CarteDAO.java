/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Carte;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    public void addCarte ( Carte carte){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
        em.getTransaction().begin();
        em.persist(carte);
        em.getTransaction().commit();
    }
    
    public Carte SelectionnerCarte ( long idCarte){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        
         Query query = em.createQuery("SELECT c  FROM Carte c JOIN c.joueurProprietaire WHERE c.id =:idCarte");
         query.setParameter("idCarte", idCarte);
         
         Object res = query.getSingleResult();
    
        return  (Carte) res;        
    }
    
    
}

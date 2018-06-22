/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class CarteService {

    private CarteDAO cDao = new CarteDAO();
    private JoueurDAO jDao = new JoueurDAO();
    private PartieDAO pDaO = new PartieDAO();

    
    
    public void sortSommeil(long idJoueurCible){
         Joueur j = jDao.rechercheParID(idJoueurCible);
         j.setEtatJoueur(Joueur.typeEtatJoueur.SOMMEIL_PROFOND);
         jDao.modifier(j);
    }
    public void sortinvisibilite(long idJoueurAExclure, long idPartie){
        List<Joueur> joueurs = jDao.listeJoueur(idPartie);
        for(Joueur joueur : joueurs){

            List<Carte> cartes =  joueur.getCartes();
            int taille = cartes.size();
            Random r = new Random();
            int alea = r.nextInt(taille);
            Carte carteAPrendre = new Carte();
            joueur.getCartes().remove(alea);
            Joueur joueurALaMain = pDaO.rechJoueurQuiALaMainId(idPartie);
            joueurALaMain.getCartes().add(carteAPrendre);
            carteAPrendre.setJoueurProprietaire(joueurALaMain);
            cDao.modifierC(carteAPrendre);
        }
    }

    public void sortPhiltreAmour(long idJoueurCible) {
        Joueur j = jDao.rechercheParID(idJoueurCible);
    }

    public void sortHypnose(long idJoueurCible) {
       Joueur j = jDao.rechercheParID(idJoueurCible);
    }

    public List<Joueur> sortDivination(long idJoueurAExclure, long idPartie) {
        
        List<Joueur> joueurs = cDao.listeAutresJoueursParPartieId(idJoueurAExclure, idPartie); 
    
        return joueurs;
    
    }
    
    
    public Carte piocherCarte(Joueur joueur) {

        Carte carte = new Carte();
        Carte.typeCarte[] tabTypesCartes = Carte.typeCarte.values();
        Random r = new Random();
        int n = r.nextInt(tabTypesCartes.length);
        carte.setIngredient(tabTypesCartes[n]);
        carte.setJoueurProprietaire(joueur);

        cDao.addCarte(carte);
        return carte;
    }

    public Carte lancerSort(long idJoueurLanceur, Long idJoueurCible, long idPartie, long idCarte1, long idCarte2) {
        
        
        Joueur joueur = new Joueur();
        // Détermine le sort
        Carte carte1 = cDao.rechercherUneCarte(idCarte1,idJoueurLanceur);
        Carte carte2 = cDao.rechercherUneCarte(idCarte2,idJoueurLanceur);

        String compareCarte1 = carte1.getIngredient().toString();
        String compareCarte2 = carte2.getIngredient().toString();

        if (compareCarte1 == "BAVE_DE_CRAPAUD" && compareCarte2 == "CORNE_DE_LICORNE" || compareCarte1 == "CORNE_DE_LICORNE" && compareCarte2 == "BAVE_DE_CRAPAUD") {
            //Sort Invisibilité
            this.sortinvisibilite(idJoueurLanceur,idPartie);
        }
        if (compareCarte1 == "MANDRAGORE" && compareCarte2 == "CORNE_DE_LICORNE" || compareCarte1 == "CORNE_DE_LICORNE" && compareCarte2 == "MANDRAGORE") {
            this.sortPhiltreAmour(idJoueurCible);
        }
        if (compareCarte1 == "BAVE_DE_CRAPAUD" && compareCarte2 == "LAPIS_LAZULI" || compareCarte1 == "LAPIS_LAZULI" && compareCarte2 == "BAVE_DE_CRAPAUD") {
            this.sortHypnose(idJoueurCible);
        }
        if (compareCarte1 == "AILE_DE_CHAUVE_SOURIS" && compareCarte2 == "LAPIS_LAZULI" || compareCarte1 == "LAPIS_LAZULI" && compareCarte2 == "AILE_DE_CHAUVE_SOURIS") {
            this.sortDivination(idJoueurLanceur, idPartie);
        }
        if (compareCarte1 == "AILE_DE_CHAUVE_SOURIS" && compareCarte2 == "MANDRAGORE" || compareCarte1 == "MANDRAGORE" && compareCarte2 == "AILE_DE_CHAUVE_SOURIS") {
            this.sortSommeil(idJoueurCible);
           
        }

        // Supprime les 2 cartes de l'émetteur
        cDao.SupprCarte(carte1);
        cDao.SupprCarte(carte2);
        
        if(joueur.getCartes().isEmpty()){
            joueur.setEtatJoueur(Joueur.typeEtatJoueur.PERDU);
        
        }
        
        return carte1;
        
    }

}

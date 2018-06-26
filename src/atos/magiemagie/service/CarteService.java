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
import atos.magiemagie.entity.Partie;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class CarteService {

    private CarteDAO cDao = new CarteDAO();
    private JoueurDAO jDao = new JoueurDAO();
    private PartieDAO pDaO = new PartieDAO();
     

    
    
    public Carte donnerUneCarte (long idPartie){
        
        Joueur joueurALaMain = pDaO.rechJoueurQuiALaMainId(idPartie);
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        long  idJoueur = pDaO.rechJoueurQuiALaMainId(idPartie).getId();
        
        Scanner s = new Scanner(System.in);
        System.out.print("Sélectionner la carte à cibler : ");
        long carteADonner = s.nextLong();
        Carte carte = cDao.rechercherUneCarte(carteADonner, idJoueur);
        joueurALaMain.getCartes().remove(carte);
        j.getCartes().add(carte);
        carte.setJoueurProprietaire(j);
        cDao.modifierC(carte);
        
        
        return carte;
    }
    
    
    public void sortSommeil() {
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        j.setEtatJoueur(Joueur.typeEtatJoueur.SOMMEIL_PROFOND);
        jDao.modifier(j);
    }

    
    
    public void sortinvisibilite(long idPartie, long idJoueurAExclure ) {
        Partie p = new Partie();
        List<Joueur> joueurs = cDao.listeAutresJoueursParPartieId(idJoueurAExclure, idPartie);
        for (Joueur joueur : joueurs) {

            List<Carte> cartes = joueur.getCartes();
            int taille = cartes.size();
            Random r = new Random();
            int alea = r.nextInt(taille);
            Carte carteAPrendre = cartes.get(alea);
            joueur.getCartes().remove(alea);
            Joueur joueurALaMain = pDaO.rechJoueurQuiALaMainId(idPartie);
            joueurALaMain.getCartes().add(carteAPrendre);
            carteAPrendre.setJoueurProprietaire(joueurALaMain);
            cDao.modifierC(carteAPrendre);
        }
    }

    public void sortPhiltreAmour(long idPartie) {
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        List<Carte> cartes = j.getCartes();
        int taille = (cartes.size())/2;

        if(taille == 1){
            cartes.clear();
            j.setEtatJoueur(Joueur.typeEtatJoueur.PERDU);
        }
        
        for (int i = 0; i < taille ; i++) {
            Random r = new Random();
            int alea = r.nextInt(taille);
            Carte carteAPrendre =  cartes.get(alea);
            j.getCartes().remove(alea);
            Joueur joueurALaMain = pDaO.rechJoueurQuiALaMainId(idPartie);
            joueurALaMain.getCartes().add(carteAPrendre);
            carteAPrendre.setJoueurProprietaire(joueurALaMain);
            cDao.modifierC(carteAPrendre);
        }

    }

    public void sortHypnose(long idPartie) {
        JoueurService jService = new JoueurService();
        Joueur j = jService.ciblerUnJoueur();
        Joueur joueurALaMain = pDaO.rechJoueurQuiALaMainId(idPartie);
        List<Carte> cartes = j.getCartes();
        int taille = cartes.size();
        
        this.donnerUneCarte(idPartie);
        
        for (int i = 0; i < 3 ; i++) {
            Random r = new Random();
            int alea = r.nextInt(taille);
            Carte carteAPrendre =  cartes.get(alea);
            j.getCartes().remove(alea);
            joueurALaMain.getCartes().add(carteAPrendre);
            carteAPrendre.setJoueurProprietaire(joueurALaMain);
            cDao.modifierC(carteAPrendre);
        }
        
      
        //Carte carte = cDao.rechercherUneCarte(idCarte, joueurALaMain.getId());
        /*joueurALaMain.getCartes().remove(carte);
        j.getCartes().add(carte);
        carte.setJoueurProprietaire(j);
        cDao.modifierC(carte);*/
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

    public void lancerSort(long idJoueurLanceur, long idPartie, long idCarte1, long idCarte2) {

        Joueur joueur = new Joueur();
        // Détermine le sort
        Carte carte1 = cDao.rechercherUneCarte(idCarte1, idJoueurLanceur);
        Carte carte2 = cDao.rechercherUneCarte(idCarte2, idJoueurLanceur);

        String compareCarte1 = carte1.getIngredient().toString();
        String compareCarte2 = carte2.getIngredient().toString();

        if (compareCarte1 == "BAVE_DE_CRAPAUD" && compareCarte2 == "CORNE_DE_LICORNE" || compareCarte1 == "CORNE_DE_LICORNE" && compareCarte2 == "BAVE_DE_CRAPAUD") {
            //Sort Invisibilité
            this.sortinvisibilite(idPartie,idJoueurLanceur);
        }
        if (compareCarte1 == "MANDRAGORE" && compareCarte2 == "CORNE_DE_LICORNE" || compareCarte1 == "CORNE_DE_LICORNE" && compareCarte2 == "MANDRAGORE") {
            this.sortPhiltreAmour(idPartie);
        }
        if (compareCarte1 == "BAVE_DE_CRAPAUD" && compareCarte2 == "LAPIS_LAZULI" || compareCarte1 == "LAPIS_LAZULI" && compareCarte2 == "BAVE_DE_CRAPAUD") {
            this.sortHypnose(idPartie);
        }
        if (compareCarte1 == "AILE_DE_CHAUVE_SOURIS" && compareCarte2 == "LAPIS_LAZULI" || compareCarte1 == "LAPIS_LAZULI" && compareCarte2 == "AILE_DE_CHAUVE_SOURIS") {
            this.sortDivination(idJoueurLanceur, idPartie);
        }
        if (compareCarte1 == "AILE_DE_CHAUVE_SOURIS" && compareCarte2 == "MANDRAGORE" || compareCarte1 == "MANDRAGORE" && compareCarte2 == "AILE_DE_CHAUVE_SOURIS") {
            this.sortSommeil();

        }

        // Supprime les 2 cartes de l'émetteur
        cDao.SupprCarte(carte1);
        cDao.SupprCarte(carte2);

     /*   if (joueur.getCartes().isEmpty()) {
            joueur.setEtatJoueur(Joueur.typeEtatJoueur.PERDU);
//          jDao.modifier(joueur);
            System.out.println("LOSER");
        }*/
        JoueurService jService = new JoueurService();
        jService.passerAuJoueurSuivant(idPartie);
       

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.CarteService;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author Administrateur
 */
public class PointEntree {
    
    private PartieService partieService = new PartieService();
    private JoueurService joueurSerivce = new JoueurService();
    private CarteService carteService = new CarteService();
    private JoueurDAO jDao = new JoueurDAO();
    private PartieDAO pDao = new PartieDAO();
    private CarteDAO cDao = new CarteDAO();
    
    
    public void ecranJeu (long idPartie, long monId) throws InterruptedException{
        Scanner s = new Scanner(System.in);
        String choix = new String();
        
       
        
        while(true){
              long joueurQuiALaMain = pDao.rechJoueurQuiALaMainId(idPartie).getId();
              if(joueurQuiALaMain == monId){
                System.out.println("----------------------------------------------------------------- ");
                System.out.println("--------------       A TOI DE JOUER       ----------------------- ");
                System.out.println("------------------------------------------------------------------");
                System.out.println(" Menu Partie démarée :--------------------------------------------" );
                System.out.println("------------------------------------------------------------------");
                String joueurActif = pDao.rechJoueurQuiALaMainId(idPartie).getPseudo();
                System.out.println("Joueur actif : " + joueurActif);
                System.out.println("------------------       Liste Carte       ----------------------");
                long idJoueurLanceur = pDao.rechJoueurQuiALaMainId(idPartie).getId();
                List<Carte> cartes = cDao.rechercherCartesParId(idJoueurLanceur);
                int taille = cartes.size();
                System.out.println("Nombre de Carte : " + taille);
                System.out.println("----------------------------------------------------------------- ");
                for(Carte carte : cartes){
                    System.out.println(carte.getIngredient() + " : " +carte.getId());
                }
                
                System.out.println("------------------------------------------------------------------");
                System.out.println("1. Lancer un sort");
                System.out.println("2. Passer son tour");
                System.out.println("------------------------------------------------------------------");
                System.out.print("Votre choix > ");
                System.out.println("");
                System.out.println("");

                choix = s.nextLine();
                
                switch(choix){
                    case "1" :
                        System.out.println("--------------       TABLE DES SORTS       ------------");
                        System.out.println("INVISIBILITE : corne de licorne + bave de crapaud");
                        System.out.println("PHILTRE D_AMOUR : corne de licorne + mandragore");
                        System.out.println("HYPNOSE : bave de crapaud + lapis-lazuli");
                        System.out.println("DIVINATION : lapis-lazuli + aile-de chauve-souris");
                        System.out.println("SOMMEIL-PROFOND : mandragore +aile de chauve-souris");
                       
                        System.out.print("Sélectionner la 1 ère carte à utiliser : ");
                        long carte1 = s.nextLong();
                        System.out.print("Sélectionner la 2ème carte à utiliser : ");
                        long carte2 = s.nextLong();
                        carteService.lancerSort(idJoueurLanceur, idPartie, carte1, carte2);
                        break;
                        
                    case "2" :
                        joueurSerivce.passerSonTour(joueurActif, idPartie);
                        break;
                }
        }else{
            Thread.sleep(1000);
            
        }
        }
       
        
        
    }
    
    
  
    
    
    public void menuDemarrer(long idPartie,long monId) throws InterruptedException{
        Scanner s = new Scanner(System.in);
        String choix = new String();
      
        while(true){
     
                System.out.println("------------------------------------------------------------------");
                System.out.println(" Menu Lancement Partie :------------------------------------------");
                System.out.println("------------------------------------------------------------------");
                System.out.println("1. Démarrer la partie");
                System.out.println("------------------------------------------------------------------");
                System.out.print("Votre choix > ");


                choix = s.nextLine();
               
                
                if(choix.equals("1")){
                       partieService.demarrerPartie(idPartie);
                       ecranJeu(idPartie,monId);                    
                }else if ( pDao.rechJoueurQuiALaMainId(idPartie) != null){
                       ecranJeu(idPartie,monId); 
                }
        }
    }
    
    public void menuPrincipal() throws InterruptedException{
        
         Scanner s = new Scanner(System.in);
         String choix = new String();
        do{
                
                
                System.out.println("------------------------------------------------------------------");
                System.out.println("Menu Principal :--------------------------------------------------");
                System.out.println("------------------------------------------------------------------");
                System.out.println("1. Lister les parties non démarrés");
                System.out.println("2. Créer une partie");
                System.out.println("3. Rejoindre une partie");
                System.out.println("Q. Quitter");
                System.out.println("------------------------------------------------------------------");
                System.out.print("Votre choix > ");


                choix = s.nextLine();
        
                
                
                switch(choix){
                    case "1":
                        List<Partie> parties = partieService.listerPartiesNonDemarrees();
                        for(Partie p : parties){
                            System.out.println("Nom de Partie : "+ p.getId()+ " : "+p.getNom());
                            
                        }
                        if(parties.isEmpty()){
                                System.out.println("Et y'a plus rien !");
                            }
                        break;
                    case "2":
                        
                        System.out.print("Veuillez nommer votre partie : ");
                        String nomPartie = s.nextLine();
                        Partie partie = partieService.creerNouvellePartie(nomPartie);
                        System.out.println("Vous venez de créer la partie : " + partie.getNom());
                        
                        break;
                        
                    case "3":
                        List<Partie> partiesEnAttente = partieService.listerPartiesNonDemarrees();
                        for(Partie p : partiesEnAttente){
                            System.out.println("Nom de Partie : "+p.getNom()+ " - ID : "+ p.getId());
                            if(partiesEnAttente.isEmpty()){
                                System.out.println("Il n'y a aucune liste non démarées");
                            }
                        } 
                        System.out.print("Veuillez choisir votre pseudo : ");
                        String pseudo = s.nextLine();
                        System.out.print("Veuillez choisir votre avatar : ");
                        String avatar = s.nextLine();
                        System.out.print("Veuillez choisir la partie à rejoindre : ");
                        long idPartie = s.nextLong();
                        Joueur joueur = joueurSerivce.rejoindrePartie(pseudo, avatar, (long)idPartie);
                        menuDemarrer(idPartie,joueur.getId());
                     
                        
                        break;
                    case "Q":

                        break;
                    default :
                        System.out.println("Choix Inconnu");
                        break;
                }
        }while(!choix.equals("Q"));
    }
        
   
     public static void main(String[] args) throws InterruptedException {
        
         
        
        PointEntree m = new PointEntree();
        m.menuPrincipal();
        
        
       
    }

}
    
    
    
    /**
     * @param args the command line arguments
     */


   


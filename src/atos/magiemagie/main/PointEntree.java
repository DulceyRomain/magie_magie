/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

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
    
    public void ecranJeu (long idPAartie, String Pseudo){
        long monId = 1L;
        
        while(true){
        long joueurQuiALaMain = .joueurQuialamain(idpartie);
               
        
        if(joueurQuiALaMain == monId){
            System.out.println("");
            
            String choix = new Scanner (System.in).nextLine();
            switch (choix){
                case "1":
                   
                case "2":
                    
                default:    
                
            }
        }
    }
    }
    
    
    
    public void menuDemarrer(long idPartie){
        Scanner s = new Scanner(System.in);
        String choix = new String();
      
        
        do{
     
                System.out.println("------------------------------------------------------------------");
                System.out.println(" Menu Partie :" );
                System.out.println("------------------------------------------------------------------");
                System.out.println("1. Démarrer la partie");
                System.out.println("------------------------------------------------------------------");
                System.out.print("Votre choix > ");


                choix = s.nextLine();
                
                switch(choix){
                    case "1" :
                        partieService.demarrerPartie(idPartie);
                        break;                        
                }
        }while(! choix.equals("Q"));
    }
    
    public void menuPrincipal(){
        
         Scanner s = new Scanner(System.in);
         String choix = new String();
        do{
                
                
                System.out.println("------------------------------------------------------------------");
                System.out.println("Menu Principal :                                                  ");
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
                            System.out.println("Nom de Partie : "+p.getNom()+ " - ID : "+ p.getId());
                            if(parties.isEmpty()){
                                System.out.println("Il n'y a aucune liste non démarées");
                            }
                        }
                        break;
                    case "2":
                        
                        System.out.print("Veuillez nommer votre partie : ");
                        String nomPartie = s.nextLine();
                        Partie partie = partieService.creerNouvellePartie(nomPartie);
                        System.out.println("Vous venez de créer la partie : " + partie.getNom());
                        
                        break;
                        
                    case "3":
                        
                        
                        System.out.print("Veuillez choisir votre pseudo : ");
                        String pseudo = s.nextLine();
                        System.out.print("Veuillez choisir votre avatar : ");
                        String avatar = s.nextLine();
                        System.out.print("Veuillez choisir la partie à rejoindre : ");
                        long idPartie = s.nextLong();
                        Joueur joueur = joueurSerivce.rejoindrePartie(pseudo, avatar, (long)idPartie);
                        menuDemarrer(idPartie);
                        ecranJeu(idPartie,pseudo);
                        
                        break;
                    case "Q":

                        break;
                    default :
                        System.out.println("Choix Inconnu");
                        break;
                }
        }while(!choix.equals("Q"));
    }
        
   
     public static void main(String[] args) {
        
         
        
        PointEntree m = new PointEntree();
        m.menuPrincipal();
        
        
       
    }

}
    
    
    
    /**
     * @param args the command line arguments
     */


   


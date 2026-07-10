package game;

import characters.*;
import java.util.Scanner;
import save.SaveManager;
import battle.*;

public class GameManager {
    
    private Player player;
    private Scanner scanner;

    public GameManager() {

        scanner = new Scanner(System.in);

        System.out.println("TestRpg a game in developpement");

        //while loop for start menu
        while (player == null) {

            System.out.println("=====TestRpg=====");
            System.out.println("1. Continuer");
            System.out.println("2. Nouvelle partie");
            System.out.println("3. Quitter");
            System.out.println("=================");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    continuePlayer();
                    break;

                case 2:
                    createNewPlayer();
                    break;

                case 3:

                    System.exit(0);

                default:
                    
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    //if the player want to load a save
    private void continuePlayer() {

        player = SaveManager.load();

        if (player == null) {

            createNewPlayer();
            
            return;
        }

        System.out.println("C'est l'histoire de " + player.getName() + " qui continue");
    }

    //if the player want to start a new game
    private void createNewPlayer() {

        System.out.println("Entrez le nom de votre héro : ");

        String name = scanner.nextLine();

        if (name.isBlank()) {
            name = "Jit";
        }

        System.out.println("Veuillez choisir la classe de " + name + " :\n");

        PlayerClass[] classes = PlayerClass.values();

        for (int i =0; i < classes.length; i++) {
            displayClass(classes[i], i + 1);
        }

        PlayerClass playerClass = null;
        int choice;

        //manage the class selection in the character creation
        while (playerClass == null) {

            choice = scanner.nextInt();

            if (choice < 1 || choice > classes.length){

                System.out.println("Veuillez choisir une classe valide");

            } else {

                playerClass = classes[choice -1];
            }
        }
    
        player = new Player(name, playerClass);

        System.out.println();
        System.out.println("C'est le début de ton histoire " + player.getName() + " ! ");
    }

    //loop for the game to continue sending enemies while the player is alive
    public void startGame() {

        while (player.isAlive()){

            Enemy enemy = EnemyRandom.spawnRandomEnemy();

            Battle battle = new Battle(player, enemy);

            battle.startBattle();

            if(!player.isAlive()) {
                break;
            }

            System.out.println("\nAppuyez sur Entrée pour continuer...");

            scanner.nextLine();
            scanner.nextLine();
        }

        SaveManager.save(player);
        System.out.println(player.getName() + " c'est la fin de votre aventure...");
    }

    //method to display all class relevant stats
    private void displayClass(PlayerClass playerClass, int number) {

        System.out.println(number + "." + playerClass);
        System.out.println(" HP : " + playerClass.getHp());
        System.out.println(" Attack : " + playerClass.getAttack());
        System.out.println(" Initiative : " + playerClass.getInitiative());
        System.out.println(" Description : " + playerClass.getDescription());

        System.out.println();
    }
}

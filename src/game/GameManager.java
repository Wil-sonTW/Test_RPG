package game;

import characters.*;
import save.SaveManager;
import battle.*;

public class GameManager {
    
    private Player player;

    public GameManager() {

        player = null;
    }

    public void createPlayer(String name, PlayerClass playerClass) {

        if (name.isBlank()) {
            name = "Jit";
        }

        player = new Player(name, playerClass);
    }


    //if the player want to load a save 
    /*private void continuePlayer() {

        player = SaveManager.load();

        if (player == null) {

            createNewPlayer();
            
            return;
        }

        System.out.println("C'est l'histoire de " + player.getName() + " qui continue");
    }*/


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

        }

        SaveManager.save(player);
        System.out.println(player.getName() + " c'est la fin de votre aventure...");
    }

    //method to display all class relevant stats
    /*private void displayClass(PlayerClass playerClass, int number) {

        System.out.println(number + "." + playerClass);
        System.out.println(" HP : " + playerClass.getHp());
        System.out.println(" Attack : " + playerClass.getAttack());
        System.out.println(" Initiative : " + playerClass.getInitiative());
        System.out.println(" Description : " + playerClass.getDescription());

        System.out.println();
    }*/

    public Player getPlayer() {
        return player;
    }
}

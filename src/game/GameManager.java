package game;

import characters.*;
import save.SaveManager;
import battle.*;

public class GameManager {

    private Player player;
    private Battle currentBattle;

    public GameManager() {

        player = null;
    }

    public void createPlayer(String name, PlayerClass playerClass) {

        if (name.isBlank()) {
            name = "Jit";
        }

        player = new Player(name, playerClass);
    }

    public void createBattle() {
        Enemy enemy = EnemyRandom.spawnRandomEnemy();

        currentBattle = new Battle(player, enemy);
    }

    // if the player want to load a save
    /*
     * private void continuePlayer() {
     * 
     * player = SaveManager.load();
     * 
     * if (player == null) {
     * 
     * createNewPlayer();
     * 
     * return;
     * }
     * 
     * System.out.println("C'est l'histoire de " + player.getName() +
     * " qui continue");
     * }
     */

    // method to display all class relevant stats
    /*
     * private void displayClass(PlayerClass playerClass, int number) {
     * 
     * System.out.println(number + "." + playerClass);
     * System.out.println(" HP : " + playerClass.getHp());
     * System.out.println(" Attack : " + playerClass.getAttack());
     * System.out.println(" Initiative : " + playerClass.getInitiative());
     * System.out.println(" Description : " + playerClass.getDescription());
     * 
     * System.out.println();
     * }
     */

    public Player getPlayer() {
        return player;
    }

    public Battle getCurrentBattle() {
        return currentBattle;
    }
}

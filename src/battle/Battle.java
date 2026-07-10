package battle;

//Java imports
import java.util.Scanner;
import java.util.Random;

//Game imports
import characters.Enemy;
import characters.Player;
import save.SaveManager;
import characters.Character;
import skills.*;

public class Battle {

    private Player player;
    private Enemy enemy;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private static final double critMulti = 1.5;

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    /*  Method that manage the flow of a battle based on the initiative */
    public void startBattle() {

        System.out.println("Un " + enemy.getName() + " apparaît !");

        //Loop until the player or the enemy is dead
        while (player.isAlive() && enemy.isAlive()) {

            player.getSkillManager().reduceCooldowns();

            //Player's turn first
            if (player.getInitiative() >= enemy.getInitiative()) {

                playerTurn();

                if (!enemy.isAlive()) {
                    break;
                }

                enemyTurn();
            }
            //Enemy's turn first
            else {

                enemyTurn();

                if (!enemy.isAlive() || !player.isAlive()) {
                    break;
                }

                playerTurn();
            }

        }

        endBattle();
    }

    //Method that manage the player's turn
    private void playerTurn() {

        //It shows the combat status only when the player can do an action
        displayStatus();

        // while loop to manage a menu and return button
        while (true) {

            System.out.println("\n1. Attaquer");
            System.out.println("2. Compétences");
            System.out.println("3. Défendre");
            System.out.println("4. Inventaire");
            System.out.println("5. Sauvegarder");
            System.out.println("6. Quitter le jeu");
            

            int choice = scanner.nextInt();

            switch (choice){
                case 1:

                    attack();

                    return;
                
                case 2:

                    if (skillMenu()) {

                        return;
                    }

                    break;    

                case 3:

                    defend();

                    return;

                case 4:

                    if (openInventory()) {

                        return;
                    }

                    break;

                case 5:

                    SaveManager.save(player);

                    System.out.println("Sauvegarde réussie");
                    
                    break;

                case 6:

                    if (quitMenu()) {
                        return;
                    }

                default:
                    
                    System.out.println("Choix invalide");
            }
        }
    }

    //The enemy only has the attack action for now
    private void enemyTurn() {

        int damage = calculateDamage(enemy, player);

        if (player.isDefending()) {
            damage /= 2;
            player.stopDefending();
        }

        player.takeDamage(damage);

        System.out.println(
            enemy.getName() +
            " attaque et inflige " +
            damage +
            " dégâts !"
        );
    }

    //Show level, hp, and xp during combat
    private void displayStatus() {
        System.out.println("=============================================");

        System.out.println(
            player.getName() + " - Niveau " + player.getLevel() +
            "\nHP : " + player.getHp() + "/" + player.getMaxHp() +
            "\nXP : " + player.getXp() + "/" + player.getXpToNextLevel()
        );


        System.out.println(
            "\n" +
            enemy.getName() + 
            "\nHP : " + enemy.getHp() + "/" + enemy.getMaxHp()
        );
        System.out.println("=============================================");
    }

    //verify if the player is alive at the end of the combat
    private void endBattle() {

        if (player.isAlive()) {
            System.out.println("\nVictoire !");

            player.gainXp(enemy.getXpReward());
        }
        else {
            System.out.println("\nDéfaite...");
        }
    }

    //manage the attack action for the player
    private void attack() {

        int damage = calculateDamage(player, enemy);

        enemy.takeDamage(damage);

        System.out.println(
            player.getName() +
            " attaque et inflige " +
            damage +
            " dégâts"
        );
    }

    //manage the defend action
    private void defend() {

        player.setDefending(true);

        System.out.println(
            player.getName() + 
            "Se défend pour la prochaine attaque"
        );
    }

    //manage the openInventory action -- open the inventory
    private boolean openInventory() {
        while (true) {

            player.getInventory().displayItems();

            System.out.println("0. Retour");
            System.out.println("Choix : ");

            int choice = scanner.nextInt();

            //back to the menu
            if (choice == 0) {
                return false;
            }

            if (player.getInventory().useItem(choice -1, player)) {
                return true;
            }

            System.out.println("Choix invalide");
        }
    }

    //manage the Skill action -- shows the skillMenu
    private boolean skillMenu() {

        while (true) {

            player.getSkillManager().displaySkills();

            System.out.println("0. Retour");

            int choice = scanner.nextInt();

            if (choice == 0) {
                return false;
            }

            Skill skill = player.getSkillManager().getSkill(choice - 1);

            if (skill == null) {

                System.out.println("Choix de compétence invalide");
                continue;
            }

            //verify if the skill's cooldown
            if (!skill.isReady()) {

                System.out.println("Cette compétence est en recharge");
                continue;
            }

            //Use the skill and call the method for damage

            int damage = calculateDamage(
                player,
                enemy,
                skill.getDamageMultiplier()
            );

            enemy.takeDamage(damage);

            skill.startCooldown();

            System.out.println(
            player.getName() +
            " utilise " +
            skill.getName() +
            " et inflige " +
            damage +
            " dégâts !"
            );
            
            return true;
        }
    }

    //verify if the the damage is a crit -- random
    private boolean isCrit(Character attacker) {

        return random.nextDouble() < attacker.getCritChance();
    }

    //surchage de la méthode suivante
    private int calculateDamage(Character attacker, Character defender) {

        return calculateDamage(attacker, defender, 1.0);
    }
    
    //calculate damage based on the base attack, variance and if it's a critical hit
    private int calculateDamage(Character attacker, Character defender, double multiplier) {

        int damage = attacker.getAttack();

        //round the damage to keep it as an int
        damage = (int) Math.round(damage * multiplier);

        //variance before critical hit
        damage = applyVariance(damage);

        //critical multiplier at the end 
        if (isCrit(attacker) && !defender.isDefending()) {
            
            damage = applyCrit(damage);
        }


        return damage;
    }

    //method that apply Variance in damage to add a little dynamic
    private int applyVariance(int damage) {

        double multiplier = 0.85 + random.nextDouble() * 0.25;

        return (int)(damage * multiplier);
    }

    //method that add the critical multiplier to the damage
    private int applyCrit(int damage) {

        System.out.println("C'est un coup critique ! ");

        return (int) Math.round(damage * critMulti);
    }

    //the menu for the quit action -- choice to save or not -- or to cancel
    private boolean quitMenu() {

        System.out.println("=====Quitter=====");
        System.out.println("Voulez-vous sauvegarder avant de quitter ?");
        System.out.println("1. Oui");
        System.out.println("2. Non");
        System.out.println("3. Annuler");
        System.out.println("Choix :");
        
        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                
                SaveManager.save(player);

                System.out.println("Partie sauvegardée");

                System.exit(0);

                return true;

            case 2:

                System.out.println("Bon repos ! " + player.getName());

                System.exit(0);
                
                return true;
            
            case 3:

                return false;

            default:

                System.out.println("Choix invalide");
                
                return false;
        }
    }
}
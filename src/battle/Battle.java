package battle;

import java.util.Scanner;
import java.util.Random;

import characters.Enemy;
import characters.Player;
import save.SaveManager;
import characters.Character;
import skills.*;


public class Battle {

    private Player player;
    private Enemy enemy;
    private Scanner scanner;
    private Random random;
    private static final double critMulti = 1.5;

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = new Scanner(System.in);
        random = new Random();
    }

    public void startBattle() {

        System.out.println("Un " + enemy.getName() + " apparaît !");

        while (player.isAlive() && enemy.isAlive()) {

            player.getSkillManager().reduceCooldowns();

            if (player.getInitiative() >= enemy.getInitiative()) {


                displayStatus();

                playerTurn();

                if (!enemy.isAlive()) {
                    break;
                }

                enemyTurn();

                displayStatus();
            }
            else {

                displayStatus();

                enemyTurn();

                displayStatus();

                if (!enemy.isAlive() || !player.isAlive()) {
                    break;
                }

                playerTurn();
            }
        }

        endBattle();
    }

    private void playerTurn() {

        while (true) {

            System.out.println("\n1. Attaquer");
            System.out.println("2. Comptétences");
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

                    quitMenu();

                    break;

                default:
                    
                    System.out.println("Choix invalide");
            }
        }
    }

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

    private void endBattle() {

        if (player.isAlive()) {
            System.out.println("\nVictoire !");

            player.gainXp(enemy.getXpReward());
        }
        else {
            System.out.println("\nDéfaite...");
        }
    }

    private void attack() {

        int damage = calculateDamage(player, enemy);

        enemy.takeDamage(damage);

        System.out.println(
            player.getName() +
            " attaque et inflige " +
            damage +
            " dégats"
        );
    }

    private void defend() {

        player.setDefending(true);

        System.out.println(
            player.getName() + 
            "Se défend pour la prochaine attaque"
        );
    }

    private boolean openInventory() {
        while (true) {

            player.getInventory().displayItems();

            System.out.println("0. Retour");
            System.out.println("Choix : ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                return false;
            }

            if (player.getInventory().useItem(choice -1, player)) {
                return true;
            }

            System.out.println("Choix invalide");
        }
    }

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

            if (!skill.isReady()) {

                System.out.println("Cette compétence est en recharge");
                continue;
            }

            //utilisation de la compétence

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

    private boolean isCrit(Character attacker) {

        return random.nextDouble() < attacker.getCritChance();
    }

    //surchage de la méthode suivante
    private int calculateDamage(Character attacker, Character defender) {

        return calculateDamage(attacker, defender, 1.0);
    }
    
    private int calculateDamage(Character attacker, Character defender, double multiplier) {

        int damage = attacker.getAttack();

        damage = (int) Math.round(damage * multiplier);

        damage = applyVariance(damage);

        if (isCrit(attacker) && !defender.isDefending()) {
            
            damage = applyCrit(damage);
        }


        return damage;
    }

    private int applyVariance(int damage) {

        double multiplier = 0.85 + random.nextDouble() * 0.25;

        return (int)(damage * multiplier);
    }

    private int applyCrit(int damage) {

        System.out.println("C'est un coup critique ! ");

        return (int) Math.round(damage * critMulti);
    }

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
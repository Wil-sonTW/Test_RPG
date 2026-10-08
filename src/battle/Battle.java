package battle;

//Game imports
import characters.Enemy;
import characters.Player;
import skills.*;

public class Battle {

    private Player player;
    private Enemy enemy;
    private boolean playerTurn;

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        playerTurn = player.getInitiative() >= enemy.getInitiative();
    }

    public String gameTurn() {
        if (isOver()) {
            return "Fin du combat !";
        }
        if (!playerTurn) {
            return enemyTurn();
        }

        return "";
    }

    public String playerAction(Action action) {
        String message;

        switch (action) {
            case ATTACK:
                message = attack();
                break;

            case DEFEND:
                message = defend();
                break;

            default:
                return "Action invalide";
        }

        if (!isOver()) {
            playerTurn = false;
        }

        return message;
    }

    // The enemy only has the attack action for now
    public String enemyTurn() {

        Damage damage = Damage.calculateDamage(enemy, player, 1.0);

        int damageAmount = damage.getAmount();

        if (player.isDefending()) {
            damageAmount /= 2;
            player.stopDefending();
        }

        player.takeDamage(damageAmount);

        player.getSkillManager().reduceCooldowns();

        playerTurn = true;

        return enemy.getName() +
                " attaque et inflige " +
                damageAmount +
                " dégâts !";
    }

    // verify if the player is alive at the end of the combat
    private String endBattle() {

        if (player.isAlive()) {
            player.gainXp(enemy.getXpReward());

            return "Victoire ! ";
        } else {
            return "Défaite...";
        }
    }

    // manage the attack action for the player
    public String attack() {

        Damage damage = Damage.calculateDamage(player, enemy, 1.0);

        int damageAmount = damage.getAmount();

        String criticalMessage = "";

        if (damage.getIsCrit()) {
            criticalMessage = "Coup critique !\n";
        }

        enemy.takeDamage(damageAmount);

        return criticalMessage +
                player.getName() +
                " attaque et inflige " +
                damageAmount +
                " dégâts";
    }

    // manage the defend action
    private String defend() {

        player.setDefending(true);
        return player.getName() + " se défend pour la prochaine attaque";
    }

    public String useSkill(Skill skill) {
        Damage damage = Damage.calculateDamage(player, enemy, skill.getDamageMultiplier());

        enemy.takeDamage(damage.getAmount());
        skill.startCooldown();

        return player.getName() +
                " utilise " +
                skill.getName() +
                " et inflige " +
                damage.getAmount() +
                " dégâts !";
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isOver() {
        return !player.isAlive() || !enemy.isAlive();
    }
}
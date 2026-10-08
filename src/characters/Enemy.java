package characters;

public class Enemy extends Character {

    private int xpReward;

    //constructor
    public Enemy(
        String name, 
        int hp, 
        int attack, 
        int initiative,
        double critChance,
        int xpReward) {
        super(name, hp, attack, initiative, critChance);

        this.xpReward = xpReward;
    }

    //getters
    public int getXpReward(){
        return xpReward;
    }

    public String getHealthCondition() {
        double healthPercent = (double) hp / maxHp;

        if (healthPercent == 1.0) {
            return "Unharmed";
        }

        if (healthPercent >= 0.8 ) {
            return "Scratched";
        }

        if (healthPercent >= 0.65) {
            return "Slightly Wounded";
        }

        if (healthPercent >= 0.5) {
            return "Bloodied";
        }

        if (healthPercent >= 0.25) {
            return "Badly Wounded";
        }

        if (healthPercent >= 0.1) {
            return "Barely Standing";
        }

        if (healthPercent > 0) {
            return "Near Death";
        }
        
        return "Defeated";
    }
}
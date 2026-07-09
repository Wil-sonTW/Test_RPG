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
}
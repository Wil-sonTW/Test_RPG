package characters;

public enum PlayerClass {
    
    Guerrier(120, 
        18, 
        3,
        0.07,
        "Combattant spécialisé en corps à corps"),
    Ranger(100, 
        15, 
        7,
        0.13,
        "Combattant spécialisé aux attaques à distance"),
    Mage(80, 
        22, 
        5,
        0.05,
        "Maitrise la magie et compense sa faiblesse avec de gros dégàts");

    private final int hp;
    private final int attack;
    private final int initiative;
    private final String description;
    private final double critChance;

    PlayerClass(int hp, int attack, int initiative, double critChance, String description) {
        this.hp = hp;
        this.attack = attack;
        this.initiative = initiative;
        this.description = description;
        this.critChance = critChance;
    }


    //getters
    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getInitiative() {
        return initiative;
    }

    public String getDescription() {
        return description;
    }

    public double getCritChance() {
        return critChance;
    }
}

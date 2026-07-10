package characters;

public abstract class Character {

    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int initiative;
    protected double critChance;
    protected boolean defending;

    //Constructor
    public Character(String name, int hp, int attack, int initiative, double critChance) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.initiative = initiative;
        this.critChance = critChance;
        this.defending = false;
    }

    //manage taking damage
    public void takeDamage(int damage) {
        hp -= damage;

        //round the hp to 0 if below, for future implementation
        if (hp < 0) {
            hp = 0;
        }
    }

    //manage healing
    public void healHP(int heal) {
        hp += heal;

        //round the hp so it doesn´t go above the maxHp
        if(hp > maxHp) {
            hp = maxHp;
        }
    }

    //verify if the character is above 0HP
    public boolean isAlive() {
        return hp > 0;
    }


    //-----------------------getters-----------------------------
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getInitiative() {
        return initiative;
    }

    public double getCritChance() {
        return critChance;
    }

    public boolean isDefending() {
        return defending;
    }

    //---------------setters-------------
    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public void stopDefending() {
        defending = false;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
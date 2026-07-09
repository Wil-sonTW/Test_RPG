package characters;

public abstract class Character {

    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int initiative;
    protected double critChance;
    protected boolean defending;

    public Character(String name, int hp, int attack, int initiative, double critChance) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.initiative = initiative;
        this.critChance = critChance;
        this.defending = false;
    }

    public void takeDamage(int damage) {
        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
    }

    public void healHP(int heal) {
        hp += heal;

        if(hp > maxHp) {
            hp = maxHp;
        }
    }

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
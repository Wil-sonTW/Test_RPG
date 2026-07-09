package characters;

import items.*;
import skills.*;

public class Player extends Character {

    private int level;
    private int xp;
    private int xpToNextLevel;
    private Inventory inventory;
    private PlayerClass playerClass;
    private SkillManager skillManager;

    //constructor
    public Player(String name, PlayerClass playerClass) {
        super(
            name,
            playerClass.getHp(),
            playerClass.getAttack(),
            playerClass.getInitiative(),
            playerClass.getCritChance()
        );
        this.level = 1;
        this.xp = 0;
        this.xpToNextLevel = 100;
        this.inventory = new Inventory();
        this.playerClass = playerClass;
        this.skillManager = new SkillManager();
        inventory.addItem(new Potion(30));
        inventory.addItem(new Potion(30));
        inventory.addItem(new Potion(30));

        switch(playerClass) {

            case Guerrier:
                skillManager.addSkill(new BigStrike());
                break;
            
            case Ranger:
                skillManager.addSkill(new RapidShot());
                break;
            
            case Mage:
                skillManager.addSkill(new FireGrenade());
                break;
        }
    }

    //2e constructeur pour loader save
    public Player(
        String name,
        PlayerClass playerClass,
        boolean fromSave
    ) {
        super(
            name,
            playerClass.getHp(),
            playerClass.getAttack(),
            playerClass.getInitiative(),
            playerClass.getCritChance()
        );

        this.level = 1;
        this.xp = 0;
        this.xpToNextLevel = 100;
        this.inventory = new Inventory();
        this.playerClass = playerClass;
        this.skillManager = new SkillManager();
    }

    //method pour xp
    public void gainXp(int amount) {
        xp += amount;

        System.out.println(
            getName() + " gagne " + amount + " XP !" 
         );

         while (xp >= xpToNextLevel) {
            levelUp();
         }
    }

    //method lvl up
    private void levelUp(){

        level++;

        attack += 3;
        maxHp += 10;
        hp = maxHp;

        xp -= xpToNextLevel;

        xpToNextLevel = calculateXpToNextLevel();

        System.out.println(
            getName() + " passe au niveau " + level + " ! "
        );
    }

    //method pour calcul xpNextLevel
    private int calculateXpToNextLevel() {
        return (int) (100 * Math.pow(level, 1.5));
    }


    //getters
    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    //setters
    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setXpToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }
}

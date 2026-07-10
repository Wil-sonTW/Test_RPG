package skills;

public abstract class Skill {
    
    private String name;
    private String desc;
    private int cooldown;
    private int currentCooldown;

    //constructor
    public Skill(
        String name,
        String desc,
        int cooldown
    ) {
        this.name = name;
        this.desc = desc;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
    }

    //activate the cooldown
    public void startCooldown() {
        currentCooldown = cooldown;
    }

    //verify if the skill is on cooldown and reduce if needed
    public void reduceCooldown() {

        if (currentCooldown > 0) {
            currentCooldown --;
        }
    }

    //to verify if the skill is ready or on cooldown
    public boolean isReady() {
        return currentCooldown ==0;
    }

    //------getters------
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public abstract double getDamageMultiplier();

    //setters
    public void setCurrentCooldown(int cooldown) {
        currentCooldown = cooldown;
    }
}

package skills;

public abstract class Skill {
    
    private String name;
    private String desc;
    private int cooldown;
    private int currentCooldown;

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

    public abstract double getDamageMultiplier();

    public void startCooldown() {
        currentCooldown = cooldown;
    }

    public void reduceCooldown() {

        if (currentCooldown > 0) {
            currentCooldown --;
        }
    }

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

    //setters
    public void setCurrentCooldown(int cooldown) {
        currentCooldown = cooldown;
    }
}

package skills;

public class FireGrenade extends Skill {
    
    public FireGrenade() {

        super(
        "FireGrenade",
        "Une grenade de feu qui perce la défense",
        2
    );
    }

    @Override
    public double getDamageMultiplier() {
        return 1.8;
    }
}

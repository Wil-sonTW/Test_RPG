package skills;

public class RapidShot extends Skill {
    
    public RapidShot() {

        super(
        "RapidShot",
        "Deux flèches rapides",
        2
    );
    }

    //use the abstract method of skill to implement a multiplier on damage
    @Override
    public double getDamageMultiplier() {
        return 1.5;
    }
}

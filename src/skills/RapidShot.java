package skills;

public class RapidShot extends Skill {
    
    public RapidShot() {

        super(
        "RapidShot",
        "Deux flèches rapides",
        2
    );
    }

    @Override
    public double getDamageMultiplier() {
        return 1.5;
    }
}

package skills;

public class BigStrike extends Skill {
    
    public BigStrike() {

        super(
        "BigStrike",
        "Une attaque puissante",
        2
    );
    }

    @Override
    public double getDamageMultiplier() {
        return 1.5;
    }
    
}

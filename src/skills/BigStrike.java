package skills;

public class BigStrike extends Skill {
    
    public BigStrike() {

        super(
        "BigStrike",
        "Une attaque puissante",
        2
    );
    }

    //use the abstract method of skill to implement a multiplier on damage
    @Override
    public double getDamageMultiplier() {
        return 1.5;
    }
    
}

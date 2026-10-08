package battle;

import characters.Character;

public class Damage {

    private final int amount;
    private final boolean critical;

    public Damage(int amount, boolean critical) {
        this.amount = amount;
        this.critical = critical;
    }

    public static Damage calculateDamage(Character attacker,
            Character defender,
            double multiplier) {

        int damage = attacker.getAttack();

        damage = (int) Math.round(damage * multiplier);

        damage = applyVariance(damage);

        boolean critical = isCrit(attacker) && !defender.isDefending();

        if (critical) {
            damage = applyCrit(damage);
        }

        return new Damage(damage, critical);
    }

    private static int applyVariance(int damage) {
        double multiplier = 0.85 + Math.random() * 0.25;

        return (int) (damage * multiplier);
    }

    private static boolean isCrit(Character attacker) {
        return Math.random() < attacker.getCritChance();
    }

    private static int applyCrit(int damage) {
        return (int) Math.round(damage * 1.5);
    }

    public int getAmount() {
        return amount;
    }

    public boolean getIsCrit() {
        return critical;
    }
}

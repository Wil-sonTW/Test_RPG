package items;

import characters.Player;

public class Potion extends Item  {
    
    private int healAmount;

    public Potion(int healAmount) {
        super("Potion");
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player player) {

        player.healHP(healAmount);

        System.out.println(
            player.getName() + " utilise une " + getName() + " et récupère " + healAmount + " Pv !"
        );
    }
}

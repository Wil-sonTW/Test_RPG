package items;

//Game imports
import characters.Player;

public class Potion extends Item  {
    
    private int healAmount;

    //constructor for this item
    public Potion(int healAmount) {
        super("Potion");
        this.healAmount = healAmount;
    }

    //tell what the item should do based on the abstract in Item.java
    @Override
    public void use(Player player) {

        player.healHP(healAmount);

        System.out.println(
            player.getName() + " utilise une " + getName() + " et récupère " + healAmount + " Pv !"
        );
    }
}

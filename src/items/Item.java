package items;

//Game imports
import characters.Player;

public abstract class Item {
    
    protected String name;

    //constructor
    public Item(String name) {
        this.name = name;
    }

    //for the use of sublclasses 
    public abstract void use(Player player);

    //------getters----------
    public String getName() {
        return name;
    }
}

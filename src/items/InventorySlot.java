package items;

public class InventorySlot {
    
    private Item item;
    private int quantity;

    //constructor
    public InventorySlot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    //manage adding same items in the same slot
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    //manage removing same items in the same slot
    public void removeQuantity(int amount) {
        if (amount <= quantity) {
            this.quantity -= amount;
        } else {
            System.out.println("Not enough items in the slot to remove " + amount);
        }
    }

    //------------getters--------------
    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}

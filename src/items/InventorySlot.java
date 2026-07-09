package items;

public class InventorySlot {
    
    private Item item;
    private int quantity;

    public InventorySlot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    //getters
    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void removeQuantity(int amount) {
        if (amount <= quantity) {
            this.quantity -= amount;
        } else {
            System.out.println("Not enough items in the slot to remove " + amount);
        }
    }
}

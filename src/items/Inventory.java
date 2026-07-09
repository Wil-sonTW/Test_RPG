package items;

import java.util.ArrayList;
import characters.Player;

public class Inventory {
    private ArrayList<InventorySlot> slots;

    public Inventory() {
        this.slots = new ArrayList<>();
    }

public void addItem(Item item) {
    for (InventorySlot slot : slots) {
        if (slot.getItem().getName().equals(item.getName())) {
            slot.addQuantity(1);
            return;
        }

    }
    slots.add(new InventorySlot(item, 1));
}

public boolean useItem(int index, Player player) {
    if (index < 0 || index >= slots.size()){
        return false;
    }

    InventorySlot slot = slots.get(index);

    slot.getItem().use(player);

    slot.removeQuantity(1);

    if (slot.getQuantity() <= 0) {
        slots.remove(index);
    }

    return true;
}

public void displayItems() {

    System.out.println("========== Inventaire ==========");

    int index =1;

    for (InventorySlot slot : slots) {
        System.out.println(index + ". " + slot.getItem().getName() + "- x" + slot.getQuantity());
        index++;
    }

    System.out.println("================================");
}

public InventorySlot getSlot(int index) {
    if (index >= 0 && index < slots.size()) {
        return slots.get(index);
    } else {
        return null;
    }
}

public int getSize() {
    return slots.size();
}
}
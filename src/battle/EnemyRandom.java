package battle;

//Java imports
import java.util.Random;
import characters.Enemy;

public class EnemyRandom {
    
    private static Random random = new Random();

    public static Enemy spawnRandomEnemy() {

        int choice = random.nextInt(4);

        //with the random int -- randomly take one of these
        switch(choice) {
            
            case 0:
                return new Enemy("Goblin", 40, 8, 3, 0.05, 50);

            case 1:
                return new Enemy("Slime", 60, 5, 1,0.05, 30);
            
            case 2: 
                return new Enemy("Wolf", 50, 10, 7,0.1,  70);
            
            case 3:
                return new Enemy("Orc", 80, 15, 4,0.15,  120);

            default:
                return new Enemy("Goblin", 40, 8, 3,0.05, 50);
        }
    }
}

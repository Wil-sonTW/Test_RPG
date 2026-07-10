package save;

//Java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Game imports
import characters.Player;
import characters.PlayerClass;
import skills.BigStrike;
import skills.FireGrenade;
import skills.RapidShot;
import skills.Skill;
import items.*;

public class SaveManager {
    
    //Save all the information in a .txt file
    public static void save(Player player) {

        try {

            FileWriter writer = new FileWriter("save.txt");

            writer.write("Nom=" + player.getName() + "\n");
            writer.write("Classe=" + player.getPlayerClass() + "\n");
            writer.write("Niveau=" + player.getLevel() + "\n");
            writer.write("XP=" + player.getXp() + "\n");
            writer.write("XPnext=" + player.getXpToNextLevel() + "\n");
            writer.write("HP=" + player.getHp() + "\n");
            writer.write("HPmax=" + player.getMaxHp() + "\n");
            writer.write("Attaque=" + player.getAttack() + "\n");

            for (int i = 0; i < player.getInventory().getSize(); i++) {

                InventorySlot slot = player.getInventory().getSlot(i);

                writer.write(
                    "Item=" +
                    slot.getItem().getName() +
                    "-" + slot.getQuantity() + "\n"
                );
            }

            for (int i =0; i < player.getSkillManager().size(); i++) {

                Skill skill = player.getSkillManager().getSkill(i);

                writer.write(
                    "Skill=" +
                    skill.getName() +
                    "-" + skill.getCurrentCooldown() + "\n"
                );
            }

            writer.close();
        }

        catch (IOException e) {
            System.out.println("Erreur pendant la sauvegarde");
        }
    }

    //load the characters informations saved in a .txt file
    public static Player load() {

        try {

            Scanner file = new Scanner(new File("save.txt"));
            
            String name = "";
            PlayerClass playerClass = PlayerClass.Guerrier;
            int level = 1;
            int xp = 0;
            int xpToNextLevel = 100;
            int hp = 0;
            int maxHp = 0;
            int attack = 0;
            int potionCount =0;
            String skillName = "";
            int skillCooldown = 0;


            while (file.hasNextLine()) {

                String line = file.nextLine();

                if (line.startsWith("Nom=")) {
                    name = line.split("=")[1].trim();
                }

                if (line.startsWith("Classe")) {
                    String className = line.split("=")[1].trim();

                    playerClass = PlayerClass.valueOf(className);
                }

                if (line.startsWith("Niveau")) {
                    level = Integer.parseInt(line.split("=")[1].trim());
                }

                if (line.startsWith("XPnext")) {
                    xpToNextLevel = Integer.parseInt(line.split("=")[1].trim());
                }

                else if (line.startsWith("XP")) {
                    xp = Integer.parseInt(line.split("=")[1].trim());
                }

                if (line.startsWith("HPmax")) {
                    maxHp = Integer.parseInt(line.split("=")[1].trim());
                }

               else if (line.startsWith("HP")) {
                    hp = Integer.parseInt(line.split("=")[1].trim());
                }

                if (line.startsWith("Attaque")) {
                    attack = Integer.parseInt(line.split("=")[1].trim());
                }

                if (line.startsWith("Item=")) {

                    String data = line.split("=")[1];

                    String[] values = data.split("-");

                    String itemName = values[0];

                    int quantity = Integer.parseInt(values[1]);

                    if (itemName.equals("Potion")) {
                        potionCount = quantity;
                    }
                }

                if (line.startsWith("Skill=")){

                    String data = line.split("=")[1].trim();

                    String[] values = data.split("-");

                    skillName = values[0];

                    skillCooldown = Integer.parseInt(values[1]);
                }
            }
            file.close();

            Player player = new Player(name, playerClass, true);

            player.setLevel(level);
            player.setXp(xp);
            player.setXpToNextLevel(xpToNextLevel);
            player.setHp(hp);
            player.setMaxHp(maxHp);
            player.setAttack(attack);
            for (int i =0; i < potionCount; i++) {
                player.getInventory().addItem(new Potion(30));
            }

            Skill skill = null;

            if (skillName.equals("BigStrike")) {

                skill = new BigStrike();
            }

            else if (skillName.equals("RapidShot")) {

                skill = new RapidShot();
            }

            else if (skillName.equals("FireGrenade")) {

                skill = new FireGrenade();
            }

            if (skill != null) {

                skill.setCurrentCooldown(skillCooldown);

                player.getSkillManager().addSkill(skill);
            }

            return player;

        }

        catch (FileNotFoundException e) {

            System.out.println("Aucune sauvegarde trouvée");
            return null;
        }
    }
}

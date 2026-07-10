package skills;

import java.util.ArrayList;

public class SkillManager {
    
    private ArrayList<Skill> skills;

    public SkillManager() {
        skills = new ArrayList<>();
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    //Show the skill list
    public void displaySkills() {

        System.out.println("===Compétences===");

        for (int i = 0; i < skills.size(); i++) {

            Skill skill = skills.get(i);

            //Show the skill if it is available for use
            if (skill.getCurrentCooldown() == 0) {

                System.out.println(
                    (i + 1) + ". " +
                    skill.getName() +
                    " --- Disponible ---" +
                    "Description : " +
                    skill.getDesc()
                );
            }
            //Show the skill if it is on cooldown
            else {
                
                System.out.println(
                    (i + 1) + ". " +
                    skill.getName() +
                    " --- " + "Recharge : (" +
                    skill.getCurrentCooldown() + " tour.s) --- " +
                    "Description : " +
                    skill.getDesc()
                );
            }
        }
    }


    public void reduceCooldowns() {
        for (Skill skill : skills) {
            skill.reduceCooldown();
        }
    }

    //------getters------------
    public Skill getSkill(int index) {
        
        if (index < 0 || index >= skills.size()) {
            return null;
        }

        return skills.get(index);
    }

    public int size() {
        return skills.size();
    }

    public boolean isEmpty() {
        return skills.isEmpty();
    }
}

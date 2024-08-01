package me.goost.goostserver.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.*;

public class level {
    public static List<Double> experienceTemplate = new ArrayList<>();// template of experience 300 600 900 1200
    private static HashMap<UUID, Integer> lev = new HashMap<>();
    public static HashMap<UUID, Double> experience = new HashMap<>();

    public static Integer getLevel(UUID uuid){
        //calculatePlayerLevel(uuid);
        return lev.get(uuid);
    } // ex. 1 2 3 4 5...

    public static void setLevel(UUID uuid, Integer num) {
        lev.put(uuid, num);
    }

    public static double getExperience(UUID uuid){
        return experience.get(uuid);
    } // ex. 200 600 653 5981...

    public static void setExperience(UUID uuid,Double num){
        experience.put(uuid,num);
    }

    public static void addExperience(UUID uuid,Double num) {
        experience.put(uuid,(getExperience(uuid)+num));
    }

    public static void removeExperience(UUID uuid,Double num){
        experience.put(uuid,(getExperience(uuid)-num));
    }


    public static void calculateAllPlayerLevel(){
        // set level according to the experience amount
        for (Player player : Bukkit.getOnlinePlayers()) {
            // get current exp
            // loop through the template and check each one of them until we found a number that is higher than the exp
            // then our level = i-1

            int lvl = 0;
            double experienceValue = getExperience(player.getUniqueId());
            for(int i=0; i<100; i++){
                double template = experienceTemplate.get(i);
                if( template > experienceValue ){
                    // if goal is bigger
                    lvl = i-1;
                    break;
                }
            }
            setLevel(player.getUniqueId(),lvl);
        }
    }


    public static void calculatePlayerLevel(UUID uuid){
        int lvl = 0; // initialize inputting value
        Double experienceValue = level.getExperience(uuid); // get players current exp value
        for(int i=0; i<100; i++){ // loop through all the template
            if(experienceTemplate.get(i) > experienceValue){ // if a number that is higher than player's current exp
                lvl = i-1; // then the player's lvl is one lower than the
                break;
            }
        }
        setLevel(uuid,lvl);
    }

    public static double getPercentage(Player player){ // getting the percentage of player's current progress in level

        // minus current level's expLevel from current exp and next goal
        // example.
        // cur 500 = 1500(exp) - 1000 (previous goal to get to this level
        // goal 1000 = 2000(next goal) - 1000(previous
        // Percentage = (cur * 100) / goal = 50 (percent / %)
        calculatePlayerLevel(player.getUniqueId());
        UUID uuid = player.getUniqueId();
        double ret = 0;
        double goal = experienceTemplate.get(getLevel(uuid)+1);
        // goal = the 100%

        double current = getExperience(player.getUniqueId());
        // current = the player's current progress

        double previousGoal = experienceTemplate.get(getLevel(uuid));

        goal = goal - previousGoal; // total EXP needed in this current LVL
        current = current - previousGoal; // total EXP have in this current LVL
        ret = Math.round((current / goal)*1000);
        return ret/10;
    }

    public static void initialize(){

        for(int i=0; i<=10;i++){
            experienceTemplate.add(300.0*(i));
        }
        for(int i=1; i<=10;i++){ // 11 20
            experienceTemplate.add(500.0*(i));
        }
        for(int i=1; i<=5;i++){ // 21 25
            experienceTemplate.add(1000.0*(i));
        }
        for(int i=1; i<=5;i++){ // 26 30
            experienceTemplate.add(2000.0*(i));
        }
        for(int i=1; i<=5;i++){ // 31 35
            experienceTemplate.add(3000.0*(i));
        }
        for(int i=1; i<=5;i++){ // 36 40
            experienceTemplate.add(4000.0*(i));
        }
        for(int i=1; i<=60;i++){ // 41 100 , 100 level = 500,000 exp
            experienceTemplate.add(5000.0*(i));
        }
        Bukkit.getLogger().info("INITIALIZED TEMPLATE "+experienceTemplate.get(0));
    }




    // no bugs down here -2024.07.26

    public static void onplayerjoin(PlayerJoinEvent event){
        checkPlayerLevel(event.getPlayer());
    }

    public static void checkPlayerLevel(Player player){
        UUID uuid = player.getUniqueId();

        if(!player.hasPlayedBefore()){
            level.setLevel(uuid,0);
            level.setExperience(uuid, 0.0);
        }
        if(level.getLevel(player.getUniqueId()) == null){
            level.setLevel(uuid,0);
            level.setExperience(uuid, 0.0);
        }
    }

}



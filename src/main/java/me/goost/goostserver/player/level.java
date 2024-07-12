package me.goost.goostserver.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class level {
    private static HashMap<UUID, Integer> lev = new HashMap<>();

    public static Integer getLevel(UUID uuid){
        return lev.get(uuid);
    } // ex. 1 2 3 4 5

    public static void setLevel(UUID uuid, Integer num){
        lev.put(uuid,num);
    }

    public static HashMap<Integer, Double> experienceTemplate = new HashMap<>();
    // template of experience 300 600 900 1200
    public static HashMap<UUID, Double> experience = new HashMap<>();
    // ex. 200 600 653 5981

    public static double getExperience(UUID uuid){
        return experience.get(uuid);
    }

    public static void setExperience(UUID uuid,double num){
        experience.replace(uuid,num);
    }

    public static void addExperience(UUID uuid,double num){
        experience.replace(uuid,(getExperience(uuid)+num));
    }

    public static void removeExperience(UUID uuid,double num){
        experience.replace(uuid,(getExperience(uuid)-num));}



    public static void calculateLevel(){
        // set level according to the experience amount
        for (Player player : Bukkit.getOnlinePlayers()) {
            // get current exp
            // loop through the template and check each one of them until we found a number that is higher than the exp
            // then our level = i-1

            int lvl = 0;
            double experienceValue = level.getExperience(player.getUniqueId());

            for(int i=0; i<100; i++){
                if(experienceTemplate.get(i) > experienceValue){
                    // if goal is bigger
                    lvl = i-1;
                }
            }


            lev.put(player.getUniqueId(),lvl);
        }
    }



    public static double getPercentage(Player player){

        // minus current level's expLevel from current exp and next goal
        // example.
        // cur 500 = 1500(exp) - 1000 (previous goal to get to this level
        // goal 1000 = 2000(next goal) - 1000(previous
        // Percentage = (cur * 100) / goal = 50 (percent / %)
        /*
        UUID uuid = player.getUniqueId();
        double ret = 0;
        double goal = experienceTemplate.get(level.getLevel(uuid));
        double current = level.getExperience(player.getUniqueId());
        double previousGoal = experienceTemplate.get(getLevel(uuid));

        goal = goal - previousGoal;
        current = current - previousGoal;
        ret = (current * 100) / goal;
        */
        return 0;
    }

    public static void initialize(){

        for(int i=1; i<=10;i++){
            experienceTemplate.put(i,300.0*(i));
        }
        for(int i=1; i<=10;i++){ // 11 20
            experienceTemplate.put(10+i,experienceTemplate.get(10) + ( 500 * (i)));
        }
        for(int i=1; i<=5;i++){ // 21 25
            experienceTemplate.put(20+i,experienceTemplate.get(10) + ( 1000 * (i)));
        }
        for(int i=1; i<=5;i++){ // 26 30
            experienceTemplate.put(25+i,experienceTemplate.get(10) + ( 2000 * (i)));
        }
        for(int i=1; i<=5;i++){ // 31 35
            experienceTemplate.put(30+i,experienceTemplate.get(10) + ( 3000 * (i)));
        }
        for(int i=1; i<=5;i++){ // 36 40
            experienceTemplate.put(35+i,experienceTemplate.get(10) + ( 4000 * (i)));
        }
        for(int i=1; i<=60;i++){ // 41 100 , 100 level = 500,000 exp
            experienceTemplate.put(40+i,experienceTemplate.get(10) + ( 5000 * (i)));
        }
    }











    public static void onplayerjoin(PlayerJoinEvent event){
        checkPlayerLevel(event.getPlayer());
    }

    public static void checkPlayerLevel(Player player){
        UUID uuid = player.getUniqueId();
        if(!player.hasPlayedBefore()){
            level.setLevel(uuid,0);
            level.setExperience(uuid, 0);
        }
        if(level.getLevel(player.getUniqueId()) == null){
            level.setLevel(uuid,0);
            level.setExperience(uuid, 0);
        }
    }

}



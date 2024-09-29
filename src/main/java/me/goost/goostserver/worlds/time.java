package me.goost.goostserver.worlds;


import org.bukkit.Bukkit;

import java.util.HashMap;
public class time {
    public static HashMap<Integer, String> time = new HashMap<>();
    public static void main(){
        // 16 33 50 , 66 83 100
        int hour = 6, min = 0;
        int times = 0;
        for(int i=0; i<(23999);){ // 24000 tick per day in game
            if(min>=60){ // if minute is over 60 min = 0 and hour++
                hour++;
                min = 0;
            }
            if(times==24000){ // if times reaches 24000 which is 1 day it goes down to 0 ( reset
                times = 0;
            }
            if(hour == 24){ // if hour reaches 24 hour which is 1 day it resets
                hour = 0;
            }
            i = (int) Math.round((10000.0/60)*times);

            if(min == 0){
                time.put(i, hour + ":" + min + "0");
            }else{
                time.put(i, hour + ":" + min);
            }
            min += 10;
            times++;
        }
    }
}

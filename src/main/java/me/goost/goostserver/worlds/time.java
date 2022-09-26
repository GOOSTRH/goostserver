package me.goost.goostserver.worlds;


import java.util.HashMap;
public class time {
    public static HashMap<Integer, String> time = new HashMap<>();
    public static void main(){
        // 16 33 50 , 66 83 100
        int hour = 6, min = 0;
        int times = 0;
        for(int i=0; i<(23999);){
            if(min>=60){
                hour++;
                min = 0;
            }
            if(times==24000){
                times = 0;
            }
            if(hour == 24){
                hour = 0;
            }
            i = (int) Math.round((10000.0/60)*times);

            if(min == 0){
                time.put(i,String.valueOf(hour) + ":" + String.valueOf(min) + "0");
            }else{
                time.put(i,String.valueOf(hour) + ":" + String.valueOf(min));
            }
            min += 10;
            times++;
        }
    }
}

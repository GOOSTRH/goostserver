package me.goost.goostserver.server;

import me.goost.goostserver.player.level;
import me.goost.goostserver.player.money;
import me.goost.goostserver.worlds.time;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;


import java.util.Objects;
import java.util.UUID;

public class scoreboard implements Listener {


    static String show_time = "xx:xx";
    public static void show(){
        for (Player player : Bukkit.getOnlinePlayers()) {

            UUID uuid = player.getUniqueId();

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            assert manager != null;
            Scoreboard scoreboard = manager.getNewScoreboard();

            Objective objective = scoreboard.registerNewObjective("Sidebar_Info", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName( ChatColor.BLUE + "" + ChatColor.BOLD + "LCBRK");



            // get level percentage
            double levelPer = level.getPercentage(player);
            // return ex; 56% round up, 78% round up, 92% round down
            // make it 5 for 1 bar
            double bar = (double) levelPer / 10; // 5.6 , 7.8 , 9.2
            // make BLUE bar string and GRAY bar string
            StringBuilder BlueBar = new StringBuilder();
            StringBuilder GrayBar = new StringBuilder();
            // 20 bars , each bar = 5 percent


            while(bar > 0){
                if(bar >= 1){
                    BlueBar.append("||");
                    bar--;
                }else if(bar >= 0.5){
                    BlueBar.append("|");
                    bar-=0.5;
                }else {
                    bar = 0;
                }
            }


            for(int i = BlueBar.length(); i<20; i++){
                GrayBar.append("|");
            }

            Score s0 = objective.getScore(ChatColor.GOLD+""+ChatColor.BOLD+
                    "Bank: $" + ChatColor.GREEN + money.GetBank(uuid));
            // Gold Bold bank: , Green Bold Money

            Score s1 = objective.getScore(ChatColor.GOLD+""+ChatColor.BOLD+
                    "Cash: $" + ChatColor.GREEN + money.GetCash(uuid));
            // Gold Bold cash: , Green Bold Money

            Score s2 = objective.getScore(ChatColor.WHITE+""+ChatColor.BOLD+
                    "Online Player: " + Bukkit.getServer().getOnlinePlayers().size());
            // White Bold Player count:

            Score s3 = objective.getScore(ChatColor.WHITE+""+ ChatColor.BOLD+
                    "Time: "+ChatColor.YELLOW+show_time);
            // White Bold Time:

            Score s4 = objective.getScore(ChatColor.WHITE+""+ ChatColor.BOLD+
                    "Level: ["+level.getPercentage(player)+"%]"+ChatColor.RESET+"["+ChatColor.BLUE+BlueBar+ChatColor.GRAY+GrayBar+ChatColor.WHITE + "]");
            // White Bold current level: [??%],[Blue,BlueBar,Gray,Graybar]

            s0.setScore(6);
            s1.setScore(5);
            s2.setScore(4);
            s3.setScore(3);
            s4.setScore(2);


            player.setScoreboard(scoreboard);
        }
    }

    public static void alwaysCheck(){

        int tim = (int) Objects.requireNonNull(Bukkit.getWorld("world")).getTime();
        //get cur time


        if(time.time.get(tim) != null){//if cur time is in time list
            show_time = time.time.get(tim);//get cur time in xx:xx format and import it in show_time
            show();//show scoreboard
        }else if(time.time.get(tim+1) != null){
            show_time = time.time.get(tim+1);
            show();//show scoreboard
        }
        // check time and set time
    }


}


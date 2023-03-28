package me.goost.goostserver.SQLDB;


import me.goost.goostserver.GoostServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;
import java.util.Date;

public class dataBaseListener implements Listener {

    private static GoostServer plugin;
    public dataBaseListener(GoostServer plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        // If block broke by Player add 1 money in bank
        // 나중에 없애셈!!!!!
        Player player = e.getPlayer();
        try{
            PlayerStats stats = plugin.getDatabase().findPlayerStatsByUUID(player.getUniqueId().toString());
            //if somethings goes wrong here add this. before plugin
            if (stats == null){
                stats = new PlayerStats(player.getUniqueId().toString(), false,"",0,0,0.0,"",new Date(),new Date());
                plugin.getDatabase().SetPlayerStats(stats);

            }else{
                stats.setBank(stats.getBank() + 1);
                plugin.getDatabase().updatePlayerStats(stats);

            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }

    public static void updateDataBasePlayerClassAndPlayer_(Player player, String str){
        try{
            PlayerStats stats = plugin.getDatabase().findPlayerStatsByUUID(player.getUniqueId().toString());

            if (stats == null){
                // System.out.println("2/0"); debug
                stats = new PlayerStats(player.getUniqueId().toString(), true,str,0,0,0.0,"",new Date(),new Date());
                plugin.getDatabase().SetPlayerStats(stats);

            }else{
                // System.out.println("2/2"); debug
                stats = new PlayerStats(player.getUniqueId().toString(), true,str
                        ,stats.getBank()
                        ,stats.getCash()
                        ,stats.getLevel()
                        ,stats.getStoryLine()
                        ,stats.getLastLogin()
                        ,stats.getLastLogout());

                plugin.getDatabase().updatePlayerStats(stats);
                // System.out.println("2/2 done"); debug
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}

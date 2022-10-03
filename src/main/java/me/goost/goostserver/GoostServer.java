package me.goost.goostserver;

import me.goost.goostserver.player.*;
import me.goost.goostserver.player.commands.Job;
import me.goost.goostserver.player.choose_class;
import me.goost.goostserver.player.commands.level;
import me.goost.goostserver.player.commands.test;
import me.goost.goostserver.skill.Items;
import me.goost.goostserver.skill.check.check;
import me.goost.goostserver.skill.check.onground;
import me.goost.goostserver.worlds.time;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.Objects;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class GoostServer extends JavaPlugin{

    public static GoostServer plugin;

    public GoostServer getplugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        choose_class.check_online_player_job(); // check if online players in the server has a job or not
        Items.set_all_items(); // setting all the values for the skill items

        plugin = this;
        Plugin plugin = this;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {//repeat
            public void run() {
                check.check(); // always check if player is going to use skill or whatsoever
                onground.ground_check(); // check if player has been on the ground at least once since last jump
                show_stat.showstattick(); // show stat all the time
                Job.repeat(); // reminds player to choose class
                choose_class.repeat(); // BLANK
                scoreboard.always_check(); // checking cur time and gete the string and import
                mana.repeat(); // mana repeat // increase mana every few sec
                health.check_player_health_always(); // check player's health always
            }
        }, 2, 2);

        time.main();
        //register commands
        registerCommands();

        getLogger().info("'Goostserver' pluginUpdated");

        Bukkit.getPluginManager().registerEvents(new health(), this);
        Bukkit.getPluginManager().registerEvents(new choose_class(), this);
        Bukkit.getPluginManager().registerEvents(new player(), this);
        Bukkit.getPluginManager().registerEvents(new remove_join_message(), this);
        Bukkit.getPluginManager().registerEvents(new show_stat(), this);
        Bukkit.getPluginManager().registerEvents(new scoreboard(), this);
        Bukkit.getPluginManager().registerEvents(new check(), this);


    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("Job")).setExecutor(new Job());
        Objects.requireNonNull(getCommand("level")).setExecutor(new level());
        Objects.requireNonNull(getCommand("test")).setExecutor(new test());
    }



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(!player.hasPlayedBefore()){
            money.setmoneyforfirsttimer(player.getUniqueId());
        }
        if(money.get_bank(player.getUniqueId()) == null || money.get_cash(player.getUniqueId()) == null){
            //money.setmoneyforfirsttimer(player.getUniqueId());
        }
    }

    @Override
    public void onDisable() {

        System.out.println("GoostServer plugin shutting down");

    }
}



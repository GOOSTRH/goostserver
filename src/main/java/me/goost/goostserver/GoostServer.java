package me.goost.goostserver;

import me.goost.goostserver.player.*;
import me.goost.goostserver.player.commands.Job;
import me.goost.goostserver.player.choose_class;
import me.goost.goostserver.player.commands.comoney;
import me.goost.goostserver.player.commands.level;
import me.goost.goostserver.player.commands.test;
import me.goost.goostserver.skill.Items;
import me.goost.goostserver.skill.check.check;
import me.goost.goostserver.skill.check.onground;
import me.goost.goostserver.worlds.time;
import org.bukkit.Bukkit;
import java.util.Objects;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class GoostServer extends JavaPlugin{

    public static GoostServer plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        choose_class.check_online_player_job(); // check if online players in the server has a job or not
        Items.set_all_items(); // setting all the values for the skill items

        plugin = this;
        Plugin plugin = this;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {//loop
            public void run() {
                check.check(); // always check if player is going to use skill or whatsoever
                onground.ground_check(); // check if player has been on the ground at least once since last jump
                show_stat.showstattick(); // show stat all the time
                Job.repeat(); // reminds player to choose class
                choose_class.repeat(); // BLANK
                scoreboard.always_check(); // checking cur time and gete the string and import
                health.check_player_health_always(); // check player's health always
                mana.mana_on_tick(); // check if player's mana is overflowed , if so set it to max
            }
        }, 2, 2);

        mana.repeat(); // mana repeat // increase mana every few sec

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
        Bukkit.getPluginManager().registerEvents(new money(), this);

    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("com")).setExecutor(new comoney());
        Objects.requireNonNull(getCommand("Job")).setExecutor(new Job());
        Objects.requireNonNull(getCommand("level")).setExecutor(new level());
        Objects.requireNonNull(getCommand("test")).setExecutor(new test());
    }


    @Override
    public void onDisable() {

        System.out.println("GoostServer plugin shutting down");

    }
}



package me.goost.goostserver;

import me.goost.goostserver.SQLDB.Database;
import me.goost.goostserver.SQLDB.dataBaseListener;
import me.goost.goostserver.Server.*;
import me.goost.goostserver.player.*;
import me.goost.goostserver.player.commands.*;
import me.goost.goostserver.skill.Items;
import me.goost.goostserver.skill.check.check;
import me.goost.goostserver.skill.check.onground;
import me.goost.goostserver.worlds.time;
import org.bukkit.Bukkit;

import java.sql.SQLException;
import java.util.Objects;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;



public class GoostServer extends JavaPlugin{

    public static GoostServer plugin;
    public static Plugin getPlugin() {return plugin;}
    private Database database;
    public Database getDatabase() {return database;}

    @Override
    public void onEnable() {

        plugin = this;
        Plugin plugin = this;
        DataBaseSettings();

        check_and_set();
        time.main();

        CustomRecipe.createRecipe();

        registerCommands(); // registerCommands ex. job
        registerEvents(); // registerEvents ex. joinEvent
        repeat();
        getLogger().info("");
        getLogger().info("{G O O S T   P R O D U C E}"); // watermark
    }



    private void DataBaseSettings(){ // Database settings
        try{
            this.database = new Database();
            database.initializeDatabase();
            System.out.println("DB initialize started 1");
            database.loadDataBase();
            System.out.println("DB loading started 2");
        }catch (SQLException ex) {

            System.out.println("unable to connect to the database check whats wrong");

            ex.printStackTrace();
        }
    }




    private void repeat(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {//loop
            public void run() {
                check.check(); // always check if Player is going to use skill or whatsoever
                onground.ground_check(); // check if Player has been on the ground at least once since last jump
                show_stat.showstattick(); // show stat all the time
                Job.repeat(); // reminds Player to choose class
                scoreboard.always_check(); // checking cur time and gete the string and import
                health.check_player_health_always(); // check Player's health always
                mana.mana_on_tick(); // check if Player's mana is overflowed , if so set it to max
            }
        }, 2, 2);
        mana.repeat(); // mana repeat // increase mana every few sec
    }

    private void check_and_set(){
        Items.set_all_items(); // setting all the values for the skill items
        check_all_current_online_players(); // check if all the current online players for something
    }


    private void registerCommands() {
        Objects.requireNonNull(getCommand("coitem")).setExecutor(new coitem());
        Objects.requireNonNull(getCommand("com")).setExecutor(new comoney());
        Objects.requireNonNull(getCommand("job")).setExecutor(new Job());
        Objects.requireNonNull(getCommand("col")).setExecutor(new colevel());
        Objects.requireNonNull(getCommand("test")).setExecutor(new test());
        Objects.requireNonNull(getCommand("staff")).setExecutor(new staffCommands());
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new health(), this);
        Bukkit.getPluginManager().registerEvents(new ChooseJob(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamage(), this);
        Bukkit.getPluginManager().registerEvents(new remove_join_message(), this);
        Bukkit.getPluginManager().registerEvents(new show_stat(), this);
        Bukkit.getPluginManager().registerEvents(new scoreboard(), this);
        Bukkit.getPluginManager().registerEvents(new check(), this);
        Bukkit.getPluginManager().registerEvents(new money(), this);
        Bukkit.getPluginManager().registerEvents(new coitem(), this);
        Bukkit.getPluginManager().registerEvents(new on_player_join(), this);
        Bukkit.getPluginManager().registerEvents(new dataBaseListener(this), this);
    }



    public void  check_all_current_online_players(){ // check if all the current online players for something
        for (Player player : Bukkit.getOnlinePlayers()) {
            health.check_player(player);
            money.CheckMoney(player);
            ChooseJob.check_player_(player);
            level.check_player_level(player);
            scoreboard.show();
        }
    }

}
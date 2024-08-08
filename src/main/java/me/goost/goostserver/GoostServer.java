package me.goost.goostserver;

import me.goost.goostserver.SQLiteDB.Database;
import me.goost.goostserver.SQLiteDB.dataBaseListener;
import me.goost.goostserver.server.*;
import me.goost.goostserver.player.*;
import me.goost.goostserver.player.commands.*;
import me.goost.goostserver.server.NPCs.GUI.NPCWoodBuyGUI;
import me.goost.goostserver.server.NPCs.NPCInteraction;
import me.goost.goostserver.server.NPCs.SpawnNPCCmd;
import me.goost.goostserver.skill.Items;
import me.goost.goostserver.skill.check.check;
import me.goost.goostserver.skill.check.onground;
import me.goost.goostserver.worldKillDesert.worldBorder;
import me.goost.goostserver.worldKillDesert.worldEscape;
import me.goost.goostserver.worldKillDesert.worldTp;
import me.goost.goostserver.worlds.time;
import me.goost.goostserver.SQLiteDB.writeBackDB;

import me.goost.goostserver.worlds.updateMap;
import org.bukkit.Bukkit;
import java.io.File;
import java.sql.SQLException;
import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class GoostServer extends JavaPlugin{

    public static GoostServer plugin;
    public static Plugin getPlugin() {return plugin;}
    private Database database;
    public Database getDatabase() {return database;}

    @Override
    public void onEnable() {
        plugin = this;
        Plugin plugin = this;
        initialize(); // initialize settings
        writeBackDB.onEnableWriteBackDB(); // starts writebackDB
        getLogger().info("");
        getLogger().info("{G O O S T   P R O D U C E} Enabling"); // watermark
    }

    @Override
    public void onDisable(){
        writeBackDB.onDisableWriteBackDB(); // writebackDB
        getLogger().info("");
        getLogger().info("{G O O S T   P R O D U C E} Disabling"); // watermark
    }

    private void initialize(){
        level.initialize(); // initialize level template
        checkAndSet(); // checking and setting of things
        DataBaseSettings(); // database settings initializing
        time.main(); // time template settings initializing
        CustomRecipe.createRecipe(); // custom recipe initializing
        registerCommands(); // registerCommands ex. job
        registerEvents(); // registerEvents ex. joinEvent
        repeat();
    }



    private void DataBaseSettings() { // Database settings
        try {
            // Create or get the database file in the plugin's data folder
            File dataFolder = getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Ensure the data folder exists
            }

            File databaseFile = new File(dataFolder, "database.db");
            this.database = new Database(databaseFile);
            database.initializeDatabase();
            System.out.println("DB initialization started");
            database.loadDataBase();
            System.out.println("DB loading started");
        } catch (SQLException ex) {
            System.out.println("Unable to connect to the database. Check what's wrong.");
            ex.printStackTrace();
        }
    }





    private void checkAndSet(){
        Items.set_all_items(); // setting all the values for the skill items
        checkAllCurrentOnlinePlayers(); // check if all the current online players for things
    }


    private void registerCommands() {
        Objects.requireNonNull(getCommand("coitem")).setExecutor(new coitem());
        Objects.requireNonNull(getCommand("com")).setExecutor(new comoney());
        Objects.requireNonNull(getCommand("job")).setExecutor(new Job());
        Objects.requireNonNull(getCommand("col")).setExecutor(new colevel());
        Objects.requireNonNull(getCommand("test")).setExecutor(new test());
        Objects.requireNonNull(getCommand("staff")).setExecutor(new staffCommands());
        Objects.requireNonNull(getCommand("showjob")).setExecutor(new showjob());
        Objects.requireNonNull(getCommand("coentity")).setExecutor(new coentity());
        Objects.requireNonNull(getCommand("worldtp")).setExecutor(new worldTp());
        Objects.requireNonNull(getCommand("sethome")).setExecutor(new sethome());
        Objects.requireNonNull(getCommand("home")).setExecutor(new home());
        Objects.requireNonNull(getCommand("npc")).setExecutor(new SpawnNPCCmd());
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new dataBaseListener(), this);

        Bukkit.getPluginManager().registerEvents(new onPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerQuit(), this);

        Bukkit.getPluginManager().registerEvents(new health(), this);
        Bukkit.getPluginManager().registerEvents(new ChooseJob(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamage(), this);
        Bukkit.getPluginManager().registerEvents(new removeJoinMessage(), this);
        Bukkit.getPluginManager().registerEvents(new check(), this);
        Bukkit.getPluginManager().registerEvents(new money(), this);
        Bukkit.getPluginManager().registerEvents(new coitem(), this);
        Bukkit.getPluginManager().registerEvents(new onEntityDeath(), this);

        Bukkit.getPluginManager().registerEvents(new scoreboard(), this);
        Bukkit.getPluginManager().registerEvents(new ShowStats(), this);
        Bukkit.getPluginManager().registerEvents(new spawnProtection(), this);
        Bukkit.getPluginManager().registerEvents(new worldLoadListener(), this);
        Bukkit.getPluginManager().registerEvents(new NPCInteraction(), this);
        Bukkit.getPluginManager().registerEvents(new NPCWoodBuyGUI(), this);
    }

    private void repeat(){

        new BukkitRunnable() {
            @Override

            public void run() {
                updateMap.checkTime(); // always check if the time is 18000L == 0:00 so it can replace the old map w new one
                worldBorder.checkTime(); // always check if the time is
                bannedItems.checkBannedItems(); //
            }
        }.runTaskTimer(this, 0L, 1L);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {//loop
            public void run() {
                check.check(); // always check if Player is going to use skill or whatsoever
                onground.ground_check(); // check if Player has been on the ground at least once since last jump
                ShowStats.showstattick(); // show stat all the time
                Job.repeat(); // reminds Player to choose class
                scoreboard.alwaysCheck(); // checking cur time and gete the string and import
                health.checkPlayerHealthAlways(); // check Player's health always
                mana.manaOnTick(); // check if Player's mana is overflowed , if so set it to max
                worldEscape.startLocationCheck(); // check if player is at escape point in worldKill
            }
        }, 2, 2);
        mana.repeat(); // mana repeat // increase mana every few sec
    }

    public void checkAllCurrentOnlinePlayers(){
        // check if all the current online players for things
        for (Player player : Bukkit.getOnlinePlayers()) {
            checkPlayer_.checkPlayer_(player);
            money.CheckMoney(player);
            ChooseJob.check_player_(player);
            level.checkPlayerLevel(player);
            scoreboard.show();
        }
    }

}
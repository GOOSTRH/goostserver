package me.goost.goostserver.SQLDB;

import me.goost.goostserver.server.staffCommands;
import me.goost.goostserver.player.ChooseJob;
import me.goost.goostserver.player.commands.Job;
import me.goost.goostserver.player.level;
import me.goost.goostserver.player.money;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class Database {

    private static Connection connection;

    public static Connection getConnection() throws SQLException{

        if(connection != null){
            return connection;
        }


        String url = "jdbc:mysql://localhost:3306/minecraft_server";
        String user = "root";
        String password = "";

        connection = DriverManager.getConnection(url,user,password);
        System.out.println("[DB] Connected to database");
        System.out.println("");

        return connection;

    }

    public static void loadDataBasePlayer(Player player){
        UUID uuid = player.getUniqueId();
        PlayerStats playerstats ;

        try{

            System.out.println("Database loading started!");
            playerstats = findPlayerStatsByUUID(String.valueOf(uuid));
            updateIngameWithDatabase(playerstats);
            System.out.println("Successfully Loaded Player data from Database!");
        }catch (SQLException ex) {
            System.out.println("unable to loadDataBase, something might be wrong during loading");
            ex.printStackTrace();
        }
    }

    public void loadDataBase()  {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();
            PlayerStats playerstats ;

            try{

                System.out.println("Database loading started!");
                playerstats = findPlayerStatsByUUID(String.valueOf(uuid));
                updateIngameWithDatabase(playerstats);
                System.out.println("Successfully Loaded Player data from Database!");
            }catch (SQLException ex) {
                System.out.println("unable to loadDataBase, something might be wrong during loading");
                ex.printStackTrace();
            }



        }
    }

    public void initializeDatabase() throws SQLException{

        Statement statement = getConnection().createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS player_stats(" +
                "uuid varchar(36) primary key, " +
                "Player Boolean," +
                "class_ varchar(20), " +
                "bank int, " +
                "cash int, " +
                "level int, " +
                "experience double," +
                "storyLine varchar(50), " +
                "lastLogin DATE, " +
                "lastLogout DATE)";
        statement.execute(sql);

        statement.close();

        System.out.println("[DB] created the table in the database");
        System.out.println("");

    }

    public static PlayerStats findPlayerStatsByUUID(String uuid) throws SQLException {


        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_stats WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet results = statement.executeQuery();

        PlayerStats playerStats;

        if (results.next()) {

            Boolean player = results.getBoolean("Player");
            String class_ = results.getString("class_");
            int bank = results.getInt("bank");
            int cash = results.getInt("cash");
            int level = results.getInt("level");
            double experience = results.getDouble("experience");
            String storyLine = results.getString("storyLine");
            Date lastLogin = results.getDate("lastLogin");
            Date lastLogout = results.getDate("lastLogout");


            playerStats = new PlayerStats(uuid, player, class_, bank, cash, level, experience, storyLine, lastLogin, lastLogout);

            statement.close();

            return playerStats;
        }

        statement.close();
        return null;
    }

    public void SetPlayerStats(PlayerStats stats) throws SQLException{

        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO player_stats(uuid, Player, class_, bank, cash, level, experience, storyLine, lastLogin, lastLogout) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setString(1, stats.getUuid());
        statement.setBoolean(2, stats.getPlayer());
        statement.setString(3, stats.getClass_());
        statement.setInt(4, stats.getBank());
        statement.setInt(5, stats.getCash());
        statement.setInt(6, stats.getLevel());
        statement.setDouble(7, stats.getExperience());
        statement.setString(8, stats.getStoryLine());
        statement.setDate(9, new Date(stats.getLastLogin().getTime()));
        statement.setDate(10, new Date(stats.getLastLogout().getTime()));

        statement.executeUpdate();

        statement.close();

    }

    public void updatePlayerStats(PlayerStats stats) throws SQLException{

        PreparedStatement statement = getConnection()
                .prepareStatement("UPDATE player_stats SET Player = ? , class_ = ?, bank = ?, cash = ?, level = ?, experience = ?, storyLine = ?, lastLogin = ?, lastLogout = ?");

        statement.setBoolean(1, stats.getPlayer());
        statement.setString(2, stats.getClass_());
        statement.setInt(3, stats.getBank());
        statement.setInt(4, stats.getCash());
        statement.setDouble(5, stats.getLevel());
        statement.setDouble(6, stats.getExperience());
        statement.setString(7, stats.getStoryLine());
        statement.setDate(8, new Date(stats.getLastLogin().getTime()));
        statement.setDate(9, new Date(stats.getLastLogout().getTime()));

        statement.executeUpdate();

        statement.close();

        updateIngameWithDatabase(stats);

    }

    public static void updateIngameWithDatabase(PlayerStats stats){

        UUID uuid = UUID.fromString(stats.getUuid());

        if(stats.getPlayer()){
            Job.setJob(uuid,stats.getClass_());
            ChooseJob.setPlayer_(uuid, stats.getPlayer());
        }else{
            ChooseJob.setPlayer_(uuid, stats.getPlayer());
        }

        money.SetBank(uuid, stats.getBank());
        money.SetCash(uuid, stats.getCash());
        level.setLevel(uuid, stats.getLevel());

        staffCommands.SetStoryLine(uuid, stats.getStoryLine());
        staffCommands.SetlastLoginDate(uuid, stats.getLastLogin());
        staffCommands.SetlastLogoutDate(uuid, stats.getLastLogout());

    }




    }

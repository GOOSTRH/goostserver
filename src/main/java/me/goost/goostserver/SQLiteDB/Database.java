package me.goost.goostserver.SQLiteDB;

import me.goost.goostserver.server.staffCommands;
import me.goost.goostserver.player.ChooseJob;
import me.goost.goostserver.player.commands.Job;
import me.goost.goostserver.player.level;
import me.goost.goostserver.player.money;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.*;
import java.util.UUID;

public class Database {

    private static Connection connection;
    private static File databaseFile;

    public Database(File databaseFile) {
        Database.databaseFile = databaseFile;
    }

    public static Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }
        String url = "jdbc:sqlite:" + databaseFile.getAbsolutePath();
        connection = DriverManager.getConnection(url);
        System.out.println("[DB] Connected to SQLite database at " + databaseFile.getAbsolutePath());
        return connection;
    }


    public static void loadDataBasePlayer(Player player) {
        UUID uuid = player.getUniqueId();
        PlayerStats playerStats;

        try {
            System.out.println("Database loading started!");
            playerStats = findPlayerStatsByUUID(String.valueOf(uuid));
            updateIngameWithDatabase(playerStats);
            System.out.println("Successfully Loaded Player data from Database!");
        } catch (SQLException ex) {
            System.out.println("Unable to load database, something went wrong during loading.");
            ex.printStackTrace();
        }
    }
    public void loadDataBase() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            loadDataBasePlayer(player);
        }
    }

    public static void initializeDatabase() throws SQLException {
        Statement statement = getConnection().createStatement();

        // Adjusted SQL for SQLite syntax
        String sql = "CREATE TABLE IF NOT EXISTS player_stats (" +
                "uuid TEXT PRIMARY KEY, " +
                "Player INTEGER, " + // SQLite uses INTEGER for boolean values (0 = false, 1 = true)
                "class_ TEXT, " +
                "bank INTEGER, " +
                "cash INTEGER, " +
                "level INTEGER, " +
                "experience REAL, " +
                "lastLogin DATE, " +
                "lastLogout DATE)";
        statement.execute(sql);
        statement.close();

        System.out.println("[DB] Created the table in the database");
    }

    public static PlayerStats findPlayerStatsByUUID(String uuid) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_stats WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet results = statement.executeQuery();

        PlayerStats playerStats = null;

        if (results.next()) {
            Boolean player = results.getBoolean("Player");
            String class_ = results.getString("class_");
            Integer bank = results.getInt("bank");
            Integer cash = results.getInt("cash");
            Integer level = results.getInt("level");
            Double experience = results.getDouble("experience");
            Date lastLogin = results.getDate("lastLogin");
            Date lastLogout = results.getDate("lastLogout");

            playerStats = new PlayerStats(uuid, player, class_, bank, cash, level, experience, lastLogin, lastLogout);
        }

        statement.close();
        return playerStats;
    }

    public static void setPlayerStats(PlayerStats stats) throws SQLException {
        String sql = "INSERT INTO player_stats (uuid, Player, class_, bank, cash, level, experience, lastLogin, lastLogout) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT(uuid) DO UPDATE SET " +
                "Player = excluded.Player, " +
                "class_ = excluded.class_, " +
                "bank = excluded.bank, " +
                "cash = excluded.cash, " +
                "level = excluded.level, " +
                "experience = excluded.experience, " +
                "lastLogin = excluded.lastLogin, " +
                "lastLogout = excluded.lastLogout";

        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, stats.getUuid());
        statement.setInt(2, stats.getPlayer() ? 1 : 0); // SQLite uses 0/1 for boolean
        statement.setString(3, stats.getClass_());
        statement.setInt(4, stats.getBank());
        statement.setInt(5, stats.getCash());
        statement.setInt(6, stats.getLevel());
        statement.setDouble(7, stats.getExperience());
        statement.setDate(8, new Date(stats.getLastLogin().getTime()));
        statement.setDate(9, new Date(stats.getLastLogout().getTime()));
        statement.executeUpdate();
        statement.close();
    }

    public static void updatePlayerStats(PlayerStats stats) throws SQLException {
        String sql = "UPDATE player_stats SET Player = ?," +
                " class_ = ?," +
                " bank = ?," +
                " cash = ?," +
                " level = ?," +
                " experience = ?," +
                " lastLogin = ?," +
                " lastLogout = ? " +
                "WHERE uuid = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1, stats.getPlayer() ? 1 : 0); // SQLite uses 0/1 for boolean
        statement.setString(2, stats.getClass_());
        statement.setInt(3, stats.getBank());
        statement.setInt(4, stats.getCash());
        statement.setInt(5, stats.getLevel());
        statement.setDouble(6, stats.getExperience());
        statement.setDate(7, new Date(stats.getLastLogin().getTime()));
        statement.setDate(8, new Date(stats.getLastLogout().getTime()));
        statement.setString(9, stats.getUuid());
        statement.executeUpdate();
        statement.close();

        updateIngameWithDatabase(stats);
    }

    public static void updateIngameWithDatabase(PlayerStats stats) {
        if (stats == null) {
            return; // Do nothing if stats are null
        }

        UUID uuid = UUID.fromString(stats.getUuid());

        if (stats.getPlayer()) {
            Job.setJob(uuid, stats.getClass_());
            ChooseJob.setPlayer_(uuid, stats.getPlayer());
        } else {
            ChooseJob.setPlayer_(uuid, stats.getPlayer());
        }

        money.SetBank(uuid, stats.getBank());
        money.SetCash(uuid, stats.getCash());
        level.setLevel(uuid, stats.getLevel());
        level.setExperience(uuid, stats.getExperience());

        staffCommands.SetlastLoginDate(uuid, stats.getLastLogin());
        staffCommands.SetlastLogoutDate(uuid, stats.getLastLogout());
    }

}

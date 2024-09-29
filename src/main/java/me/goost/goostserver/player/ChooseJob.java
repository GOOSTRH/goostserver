package me.goost.goostserver.player;

import me.goost.goostserver.player.commands.Job;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;



public class ChooseJob implements Listener {

    public static HashMap<UUID, Boolean> choosingJob = new HashMap<>();
    public static HashMap<UUID, Boolean> player_ = new HashMap<>();


    public static void setPlayer_(UUID uuid, Boolean player_){
        ChooseJob.player_.put(uuid, player_);
    }


    public static void onplayerjoin(PlayerJoinEvent event){
        check_player_(event.getPlayer());
    }

    public static void check_player_(Player player){
        if (player_.get(player.getUniqueId()) == null || !player_.get(player.getUniqueId()) ){
            // if Player joins server check if Player is a 'Player' or not
            // if Player is not a 'Player' (백수
            choose_class(player);
        }
    }



    public static void choose_class(Player player){
        // set choosing job state to true
        choosingJob.put(player.getUniqueId(),Boolean.TRUE);



        // magic
        TextComponent magic = new TextComponent("[ARCANIST]");
        magic.setColor(ChatColor.BLUE);
        magic.setBold(Boolean.TRUE);
        magic.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.BLUE + "" + org.bukkit.ChatColor.BOLD + "[ARCANIST]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "A Class that is optimized to cast spells\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "Main Weapon: Cast Book"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\nDMG +4\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\nHP +600\nARMOR +400\n\nMANA +800\nMOVEMENT SPD +17"
        ).create()));
        magic.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job magic"));

        // assassin
        TextComponent assassin = new TextComponent("[ASSASSIN]");
        assassin.setColor(ChatColor.GRAY);
        assassin.setBold(Boolean.TRUE);
        assassin.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.GRAY + "" + org.bukkit.ChatColor.BOLD + "[ASSASSIN]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "Focused on infiltration, deception, and mobility\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "Main Weapon: Short Knife"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\nDMG +6\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\nHP +200\nARMOR +150\nMANA +250\nMOVEMENT SPD +30"
        ).create()));
        assassin.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job assassin"));

        // sword
        TextComponent sword = new TextComponent("[BLADE MASTER]");
        sword.setColor(ChatColor.RED);
        sword.setBold(Boolean.TRUE);
        sword.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.RED + "" + org.bukkit.ChatColor.BOLD + "[BLADE MASTER]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "very powerful and versatile\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "Main Weapon: Sword"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\nDMG +8\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\nHP +250\nARMOR +150\nMANA +150\nMVOEMENT SPD +25"
        ).create()));
        sword.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job blademaster"));

        // demon
        TextComponent tank = new TextComponent("[PALADIN]");
        tank.setColor(ChatColor.DARK_GRAY);
        tank.setBold(Boolean.TRUE);
        tank.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.DARK_GRAY + "" + org.bukkit.ChatColor.BOLD + "[PALADIN]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "formidable fighter who possesses a potent armor that absorbs considerable damage \n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "Main Weapon: Axe"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\nDMG +8\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\nHP +500\nARMOR +350\nMANA +500\nMOVEMENT SPD +15"
        ).create()));
        tank.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job tank"));


        TextComponent space = new TextComponent(" ");

        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"Please select your Class!");

        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"Hover over the class to check the "+ChatColor.GOLD+ChatColor.BOLD+"class description");
        player.sendMessage(ChatColor.YELLOW+""+ChatColor.BOLD+"⚠Once you choose a class, you will never be able to change it, choose with caution⚠");
        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"If this window is gone please "+ ChatColor.DARK_RED+ChatColor.BOLD+"Reconnect");

        player.sendMessage("");

        player.spigot().sendMessage(magic,space,assassin);
        player.spigot().sendMessage(sword,space,tank);
    }






}





















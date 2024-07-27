package me.goost.goostserver.player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;




public class ShowStats implements Listener {


    public static void showstattick() {
        for (Player player : Bukkit.getOnlinePlayers()) { // loop through all players
            showstat(player); // show stat
        }
    }

    public static void showstat(Player player){
        String message = """
                    ChatColor.RED +
                    String.valueOf(Math.round(health.health.get(player.getUniqueId()))) +
                    "/" +
                    Math.round((health.healthy.get(player.getUniqueId())))+
                    " [HP]"+"             "+

                    ChatColor.BLUE+
                    mana.getMana(player.getUniqueId())+"/"+mana.getManam(player.getUniqueId())+" [MP]\"
                    """;


        TextComponent HPComponent = new TextComponent(
                Math.round(health.health.get(player.getUniqueId()))
                        + "/"
                        + Math.round((health.healthy.get(player.getUniqueId())))
                        +" [HP]"+"             ");

        HPComponent.setColor(ChatColor.RED);

        TextComponent ARComponent = new TextComponent(
                def.get_def(player.getUniqueId())
                +"[AR]          ");

        ARComponent.setColor(ChatColor.GREEN);

        TextComponent MPComponent = new TextComponent(
                mana.getMana(player.getUniqueId())
                +"/"
                +mana.getManam(player.getUniqueId())
                +" [MP]");
        MPComponent.setColor(ChatColor.BLUE);

        TextComponent FullComponent = new TextComponent();
        FullComponent.addExtra(HPComponent);
        FullComponent.addExtra(ARComponent);
        FullComponent.addExtra(MPComponent);

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, FullComponent);


/* Armor
        ChatColor.GREEN+
                def.get_def(Player.getUniqueId()) +"[AR]          "+
  */
    }
}

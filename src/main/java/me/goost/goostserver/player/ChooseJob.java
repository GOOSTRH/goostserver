package me.goost.goostserver.player;

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
            //if Player is not a 'Player' (백수
            choose_class(player);
        }else{
            player.sendMessage("");
            player.sendMessage("닉네임: "+player.getName());
            player.sendMessage("");
        }
    }



    public static void choose_class(Player player){
        // set choosing job state to true
        choosingJob.put(player.getUniqueId(),Boolean.TRUE);

        // archer
        TextComponent archer = new TextComponent("[ARCHER/아처]");
        archer.setColor(ChatColor.GOLD);
        archer.setBold(Boolean.TRUE);
        archer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "[ARCHER/아처]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "활을쓰는 직업\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 활"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +3\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +100\n방어력 +100\n크리티컬 확률 +10%\n크리티컬 피해 +80%\n힘 +150\n마나 +200\n이동속도 +20"
        ).create()));
        archer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job archer"));

        // magic
        TextComponent magic = new TextComponent("[CASTER/캐스터]");
        magic.setColor(ChatColor.BLUE);
        magic.setBold(Boolean.TRUE);
        magic.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.BLUE + "" + org.bukkit.ChatColor.BOLD + "[CASTER/캐스터]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "마법속성과 잘맞는 직업이다, 마법을 쓸기엔 딱좋은직업이다\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 마석의 파편"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +4\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +600\n방어력 +400\n크리티컬 확률 +10%\n크리티컬 피해 +220%\n힘 +200\n마나 +800\n이동속도 +17"
        ).create()));
        magic.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job magic"));

        // assassin
        TextComponent assassin = new TextComponent("[ASSASSIN/어쌔신]");
        assassin.setColor(ChatColor.GRAY);
        assassin.setBold(Boolean.TRUE);
        assassin.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.GRAY + "" + org.bukkit.ChatColor.BOLD + "[ASSASSIN/어쌔신]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "다크엘프 , 약간 암살자같은 직업이군\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +6\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +200\n방어력 +150\n크리티컬 확률 +10%\n크리티컬 피해 +100%\n힘 +250\n마나 +250\n이동속도 +30"
        ).create()));
        assassin.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job assassin"));

        // sword
        TextComponent sword = new TextComponent("[SABER/세이버]");
        sword.setColor(ChatColor.RED);
        sword.setBold(Boolean.TRUE);
        sword.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.RED + "" + org.bukkit.ChatColor.BOLD + "[SABER/세이버]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "검사 , 칼을쓰는 직업이다\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 대검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +8\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +250\n방어력 +150\n크리티컬 확률 +10%\n크리티컬 피해 +100%\n힘 +400\n마나 +150\n이동속도 +25"
        ).create()));
        sword.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job sword"));

        // demon
        TextComponent night = new TextComponent("[DEMON/마족]");
        night.setColor(ChatColor.DARK_GRAY);
        night.setBold(Boolean.TRUE);
        night.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.DARK_GRAY + "" + org.bukkit.ChatColor.BOLD + "[DEMON/마족]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "마족 , 마족의 신분으로 환생할수도있다고?\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 낫"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +8\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +500\n방어력 +350\n크리티컬 확률 +10%\n크리티컬 피해 +200%\n힘 +500\n마나 +500\n이동속도 +30"
        ).create()));
        night.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job night"));


        TextComponent space = new TextComponent(" ");

        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"직업을 선택 해주세요!");

        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"마우스을 직업위에 두면 "+ChatColor.GOLD+ChatColor.BOLD+"직업설명"+ ChatColor.WHITE+ChatColor.BOLD+"을 보실수있습니다");
        player.sendMessage(ChatColor.YELLOW+""+ChatColor.BOLD+"⚠선택을하시면 변경은 할수없으니 신중하게 선택 하주세요⚠");
        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"직업창이 사라지면 "+ ChatColor.DARK_RED+ChatColor.BOLD+"재접속"+ChatColor.WHITE+ChatColor.BOLD+"을 해주세요");

        player.sendMessage("");

        player.spigot().sendMessage(archer,space,magic);
        player.spigot().sendMessage(assassin,space,sword);
        player.spigot().sendMessage(night);
    }






}





















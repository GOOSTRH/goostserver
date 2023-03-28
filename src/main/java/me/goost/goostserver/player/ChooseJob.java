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

    public static HashMap<UUID, Boolean> choosing_job = new HashMap<>();
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
        choosing_job.put(player.getUniqueId(),Boolean.TRUE);
        //archer
        TextComponent archer = new TextComponent("[ARCHER/궁수]");
        archer.setColor(ChatColor.GOLD);
        archer.setBold(Boolean.TRUE);
        archer.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.GOLD + "" + org.bukkit.ChatColor.BOLD + "[ARCHER/궁수]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "활을쓰는 직업이다, 사냥할땐좋을꺼같군\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 활"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +3\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +100\n방어력 +100\n크리티컬 확률 +10%\n크리티컬 피해 +80%\n힘 +150\n마나 +200\n이동속도 +20"
        ).create()));
        archer.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job A"));

        //dragon
        TextComponent dragon = new TextComponent("[DRAGON/드래곤]");
        dragon.setColor(ChatColor.BLUE);
        dragon.setBold(Boolean.TRUE);
        dragon.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.BLUE + "" + org.bukkit.ChatColor.BOLD + "[DRAGON/드래곤]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "마법속성과 잘맞는 직업이다, 마법을 쓸기엔 딱좋은직업이다\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 마석의 파편"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +4\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +600\n방어력 +400\n크리티컬 확률 +10%\n크리티컬 피해 +220%\n힘 +200\n마나 +800\n이동속도 +17"
        ).create()));
        dragon.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job DRA"));

        //dark_elf
        TextComponent dark_elf = new TextComponent("[DARK ELF/다크엘프]");
        dark_elf.setColor(ChatColor.GRAY);
        dark_elf.setBold(Boolean.TRUE);
        dark_elf.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.GRAY + "" + org.bukkit.ChatColor.BOLD + "[DARK ELF/다크엘프]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "다크엘프 , 약간 암살자같은 직업이군\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +6\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +200\n방어력 +150\n크리티컬 확률 +10%\n크리티컬 피해 +100%\n힘 +250\n마나 +250\n이동속도 +30"
        ).create()));
        dark_elf.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job D"));

        //sword_man
        TextComponent sword_man = new TextComponent("[SWORD MAN/검사]");
        sword_man.setColor(ChatColor.RED);
        sword_man.setBold(Boolean.TRUE);
        sword_man.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.RED + "" + org.bukkit.ChatColor.BOLD + "[SWORD MAN/검사]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "검사 , 칼을쓰는 직업이다\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 대검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +8\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +250\n방어력 +150\n크리티컬 확률 +10%\n크리티컬 피해 +100%\n힘 +400\n마나 +150\n이동속도 +25"
        ).create()));
        sword_man.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job S"));

        //demon
        TextComponent demon = new TextComponent("[DEMON/마족]");
        demon.setColor(ChatColor.DARK_GRAY);
        demon.setBold(Boolean.TRUE);
        demon.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                org.bukkit.ChatColor.DARK_GRAY + "" + org.bukkit.ChatColor.BOLD + "[DEMON/마족]\n" +
                        org.bukkit.ChatColor.WHITE + org.bukkit.ChatColor.BOLD + "마족 , 마족의 신분으로 환생할수도있다고?\n\n" +
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "주무기: 낫"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n부무기: 단검"+
                        org.bukkit.ChatColor.RED + org.bukkit.ChatColor.BOLD + "\n공격력 +8\n"+
                        org.bukkit.ChatColor.GREEN + org.bukkit.ChatColor.BOLD + "\n생명력 +500\n방어력 +350\n크리티컬 확률 +10%\n크리티컬 피해 +200%\n힘 +500\n마나 +500\n이동속도 +30"
        ).create()));
        demon.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/job DE"));

        TextComponent space = new TextComponent(" ");


        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"직업을 선택 해주세요!");

        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"마우스을 직업위에 두면 "+ChatColor.GOLD+ChatColor.BOLD+"직업설명"+ ChatColor.WHITE+ChatColor.BOLD+"을 보실수있습니다");
        player.sendMessage(ChatColor.YELLOW+""+ChatColor.BOLD+"⚠선택을하시면 변경은 할수없으니 신중하게 선택 하주세요⚠");
        player.sendMessage(ChatColor.WHITE+""+ChatColor.BOLD+"직업창이 사라지면 "+ ChatColor.DARK_RED+ChatColor.BOLD+"재접속"+ChatColor.WHITE+ChatColor.BOLD+"을 해주세요");
        player.sendMessage("");
        player.spigot().sendMessage(archer,space,dragon);
        player.spigot().sendMessage(dark_elf,space,sword_man);
        player.spigot().sendMessage(demon);
    }






}





















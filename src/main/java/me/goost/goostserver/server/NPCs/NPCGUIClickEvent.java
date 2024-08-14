package me.goost.goostserver.server.NPCs;

import me.goost.goostserver.server.NPCs.GUI.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class NPCGUIClickEvent implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        ItemStack currentItem = e.getCurrentItem();

        if (currentItem == null || currentItem.getType() == Material.AIR) { // if nothing, return
            return;
        }

        Material item = currentItem.getType();

        //C heck to see if its the GUI menu
        if( e.getView().getTitle().equalsIgnoreCase("Wood Vendor")){
            e.setCancelled(true);
            switch (item) {
                case OAK_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.oakLogPrice, e.getClick());
                case SPRUCE_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.spruceLogPrice, e.getClick());
                case BIRCH_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.birchLogPrice, e.getClick());
                case JUNGLE_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.jungleLogPrice, e.getClick());
                case ACACIA_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.acaciaLogPrice, e.getClick());
                case DARK_OAK_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.darkOakLogPrice, e.getClick());
                case MANGROVE_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.mangroveLogPrice, e.getClick());
                case CHERRY_LOG -> itemTrade.buyItem(player, item, NPCWoodSellGUI.cherryLogPrice, e.getClick());
                case CRIMSON_STEM -> itemTrade.buyItem(player, item, NPCWoodSellGUI.crimsonLogPrice, e.getClick());
                case WARPED_STEM -> itemTrade.buyItem(player, item, NPCWoodSellGUI.warpedLogPrice, e.getClick());
                case OAK_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.oakPlankPrice, e.getClick());
                case SPRUCE_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.sprucePlankPrice, e.getClick());
                case BIRCH_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.birchPlankPrice, e.getClick());
                case JUNGLE_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.junglePlankPrice, e.getClick());
                case ACACIA_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.acaciaPlankPrice, e.getClick());
                case DARK_OAK_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.darkOakPlankPrice, e.getClick());
                case MANGROVE_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.mangrovePlankPrice, e.getClick());
                case CHERRY_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.cherryPlankPrice, e.getClick());
                case CRIMSON_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.crimsonPlankPrice, e.getClick());
                case WARPED_PLANKS -> itemTrade.buyItem(player, item, NPCWoodSellGUI.warpedPlankPrice, e.getClick());
                default -> {
                }
            }

        }else if( e.getView().getTitle().equalsIgnoreCase("Wood Purchaser")){
            e.setCancelled(true);
            switch (item) {
                case OAK_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.oakLogPrice, e.getClick());
                case SPRUCE_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.spruceLogPrice, e.getClick());
                case BIRCH_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.birchLogPrice, e.getClick());
                case JUNGLE_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.jungleLogPrice, e.getClick());
                case ACACIA_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.acaciaLogPrice, e.getClick());
                case DARK_OAK_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.darkOakLogPrice, e.getClick());
                case MANGROVE_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.mangroveLogPrice, e.getClick());
                case CHERRY_LOG -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.cherryLogPrice, e.getClick());
                case CRIMSON_STEM -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.crimsonLogPrice, e.getClick());
                case WARPED_STEM -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.warpedLogPrice, e.getClick());
                case OAK_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.oakPlankPrice, e.getClick());
                case SPRUCE_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.sprucePlankPrice, e.getClick());
                case BIRCH_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.birchPlankPrice, e.getClick());
                case JUNGLE_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.junglePlankPrice, e.getClick());
                case ACACIA_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.acaciaPlankPrice, e.getClick());
                case DARK_OAK_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.darkOakPlankPrice, e.getClick());
                case MANGROVE_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.mangrovePlankPrice, e.getClick());
                case CHERRY_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.cherryPlankPrice, e.getClick());
                case CRIMSON_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.crimsonPlankPrice, e.getClick());
                case WARPED_PLANKS -> itemTrade.sellItem(player, item, NPCWoodBuyGUI.warpedPlankPrice, e.getClick());
                default -> {
                }
            }
        }else if( e.getView().getTitle().equalsIgnoreCase("Ore Vendor")){
            e.setCancelled(true);
            switch (item) {
                case RAW_IRON ->        itemTrade.buyItem(player, item, NPCOreSellGUI.rawIronPrice, e.getClick());
                case IRON_INGOT ->      itemTrade.buyItem(player, item, NPCOreSellGUI.ironIngotPrice, e.getClick());
                case RAW_COPPER ->      itemTrade.buyItem(player, item, NPCOreSellGUI.rawCopperPrice, e.getClick());
                case COPPER_INGOT ->    itemTrade.buyItem(player, item, NPCOreSellGUI.copperIngotPrice, e.getClick());
                case RAW_GOLD ->        itemTrade.buyItem(player, item, NPCOreSellGUI.rawGoldPrice, e.getClick());
                case GOLD_INGOT ->      itemTrade.buyItem(player, item, NPCOreSellGUI.goldIngotPrice, e.getClick());
                case LAPIS_LAZULI ->    itemTrade.buyItem(player, item, NPCOreSellGUI.lapisPrice, e.getClick());
                case EMERALD ->         itemTrade.buyItem(player, item, NPCOreSellGUI.emeraldPrice, e.getClick());
                case DIAMOND ->         itemTrade.buyItem(player, item, NPCOreSellGUI.diamondPrice, e.getClick());
                case CHARCOAL ->        itemTrade.buyItem(player, item, NPCOreSellGUI.charcoalPrice, e.getClick());
                case COAL ->            itemTrade.buyItem(player, item, NPCOreSellGUI.coalPrice, e.getClick());
                case QUARTZ ->          itemTrade.buyItem(player, item, NPCOreSellGUI.quartzPrice, e.getClick());
                case AMETHYST_SHARD ->  itemTrade.buyItem(player, item, NPCOreSellGUI.amethystPrice, e.getClick());
                default -> {
                }
            }
        }else if( e.getView().getTitle().equalsIgnoreCase("Ore Purchaser")){
            e.setCancelled(true);
            switch (item) {
                case RAW_IRON ->        itemTrade.sellItem(player, item, NPCOreBuyGUI.rawIronPrice, e.getClick());
                case IRON_INGOT ->      itemTrade.sellItem(player, item, NPCOreBuyGUI.ironIngotPrice, e.getClick());
                case RAW_COPPER ->      itemTrade.sellItem(player, item, NPCOreBuyGUI.rawCopperPrice, e.getClick());
                case COPPER_INGOT ->    itemTrade.sellItem(player, item, NPCOreBuyGUI.copperIngotPrice, e.getClick());
                case RAW_GOLD ->        itemTrade.sellItem(player, item, NPCOreBuyGUI.rawGoldPrice, e.getClick());
                case GOLD_INGOT ->      itemTrade.sellItem(player, item, NPCOreBuyGUI.goldIngotPrice, e.getClick());
                case LAPIS_LAZULI ->    itemTrade.sellItem(player, item, NPCOreBuyGUI.lapisPrice, e.getClick());
                case EMERALD ->         itemTrade.sellItem(player, item, NPCOreBuyGUI.emeraldPrice, e.getClick());
                case DIAMOND ->         itemTrade.sellItem(player, item, NPCOreBuyGUI.diamondPrice, e.getClick());
                case CHARCOAL ->        itemTrade.sellItem(player, item, NPCOreBuyGUI.charcoalPrice, e.getClick());
                case COAL ->            itemTrade.sellItem(player, item, NPCOreBuyGUI.coalPrice, e.getClick());
                case QUARTZ ->          itemTrade.sellItem(player, item, NPCOreBuyGUI.quartzPrice, e.getClick());
                case AMETHYST_SHARD ->  itemTrade.sellItem(player, item, NPCOreBuyGUI.amethystPrice, e.getClick());

                case ANCIENT_DEBRIS ->  itemTrade.sellItem(player, item, NPCOreBuyGUI.ancientDebrisPrice, e.getClick());
                case NETHERITE_SCRAP -> itemTrade.sellItem(player, item, NPCOreBuyGUI.netheriteScrapPrice, e.getClick());
                case NETHERITE_INGOT -> itemTrade.sellItem(player, item, NPCOreBuyGUI.netheriteIngotPrice, e.getClick());
                default -> {
                }
            }
        }else if( e.getView().getTitle().equalsIgnoreCase("Mob Drop Purchaser")){
            e.setCancelled(true);
            switch (item) {
                case ROTTEN_FLESH ->        itemTrade.sellItem(player, item, NPCDropBuyGUI.fleshPrice, e.getClick());
                case BONE ->               itemTrade.sellItem(player, item, NPCDropBuyGUI.bonePrice, e.getClick());
                case STRING ->             itemTrade.sellItem(player, item, NPCDropBuyGUI.stringPrice, e.getClick());
                case SPIDER_EYE ->         itemTrade.sellItem(player, item, NPCDropBuyGUI.spiderEyePrice, e.getClick());
                case GUNPOWDER ->          itemTrade.sellItem(player, item, NPCDropBuyGUI.gunPowderPrice, e.getClick());
                case SLIME_BALL ->         itemTrade.sellItem(player, item, NPCDropBuyGUI.slimeBallPrice, e.getClick());
                case PRISMARINE_SHARD ->   itemTrade.sellItem(player, item, NPCDropBuyGUI.prismarineShardPrice, e.getClick());
                case BLAZE_ROD ->          itemTrade.sellItem(player, item, NPCDropBuyGUI.blazeRodPrice, e.getClick());
                case PHANTOM_MEMBRANE ->   itemTrade.sellItem(player, item, NPCDropBuyGUI.phantomMembranePrice, e.getClick());
                case ENDER_PEARL ->        itemTrade.sellItem(player, item, NPCDropBuyGUI.enderPearlPrice, e.getClick());
                case SHULKER_SHELL ->      itemTrade.sellItem(player, item, NPCDropBuyGUI.shulkerShellPrice, e.getClick());
                case NETHER_STAR ->        itemTrade.sellItem(player, item, NPCDropBuyGUI.netherStarPrice, e.getClick());
                case RABBIT_HIDE ->        itemTrade.sellItem(player, item, NPCDropBuyGUI.rabbitHidePrice, e.getClick());
                case RABBIT_FOOT ->        itemTrade.sellItem(player, item, NPCDropBuyGUI.rabbitFootPrice, e.getClick());
                case LEATHER ->            itemTrade.sellItem(player, item, NPCDropBuyGUI.leatherPrice, e.getClick());
                case SCUTE ->              itemTrade.sellItem(player, item, NPCDropBuyGUI.scutePrice, e.getClick());
                case INK_SAC ->            itemTrade.sellItem(player, item, NPCDropBuyGUI.inkSacPrice, e.getClick());
                case GLOW_INK_SAC ->    itemTrade.sellItem(player, item,    NPCDropBuyGUI.glowingInkSacPrice, e.getClick());
                case FEATHER ->            itemTrade.sellItem(player, item, NPCDropBuyGUI.featherPrice, e.getClick());

                default -> {
                }
            }
        }else if( e.getView().getTitle().equalsIgnoreCase("Mob Drop Vendor")){
            e.setCancelled(true);
            switch (item) {
                case ROTTEN_FLESH ->       itemTrade.buyItem(player, item,NPCDropSellGUI.fleshPrice, e.getClick());
                case BONE ->               itemTrade.buyItem(player, item, NPCDropSellGUI.bonePrice, e.getClick());
                case STRING ->             itemTrade.buyItem(player, item, NPCDropSellGUI.stringPrice, e.getClick());
                case SPIDER_EYE ->         itemTrade.buyItem(player, item, NPCDropSellGUI.spiderEyePrice, e.getClick());
                case GUNPOWDER ->          itemTrade.buyItem(player, item, NPCDropSellGUI.gunPowderPrice, e.getClick());
                case SLIME_BALL ->         itemTrade.buyItem(player, item, NPCDropSellGUI.slimeBallPrice, e.getClick());
                case PRISMARINE_SHARD ->   itemTrade.buyItem(player, item, NPCDropSellGUI.prismarineShardPrice, e.getClick());
                case BLAZE_ROD ->          itemTrade.buyItem(player, item, NPCDropSellGUI.blazeRodPrice, e.getClick());
                case PHANTOM_MEMBRANE ->   itemTrade.buyItem(player, item, NPCDropSellGUI.phantomMembranePrice, e.getClick());
                case ENDER_PEARL ->        itemTrade.buyItem(player, item, NPCDropSellGUI.enderPearlPrice, e.getClick());
                case SHULKER_SHELL ->      itemTrade.buyItem(player, item, NPCDropSellGUI.shulkerShellPrice, e.getClick());
                case RABBIT_HIDE ->        itemTrade.buyItem(player, item, NPCDropSellGUI.rabbitHidePrice, e.getClick());
                case RABBIT_FOOT ->        itemTrade.buyItem(player, item, NPCDropSellGUI.rabbitFootPrice, e.getClick());
                case LEATHER ->            itemTrade.buyItem(player, item, NPCDropSellGUI.leatherPrice, e.getClick());
                case SCUTE ->              itemTrade.buyItem(player, item, NPCDropSellGUI.scutePrice, e.getClick());
                case INK_SAC ->            itemTrade.buyItem(player, item, NPCDropSellGUI.inkSacPrice, e.getClick());
                case GLOW_INK_SAC ->       itemTrade.buyItem(player, item,    NPCDropSellGUI.glowingInkSacPrice, e.getClick());
                case FEATHER ->            itemTrade.buyItem(player, item, NPCDropSellGUI.featherPrice, e.getClick());

                default -> {
                }
            }
        }else if( e.getView().getTitle().equalsIgnoreCase("Food Vendor")){
            e.setCancelled(true);
            switch (item) {
                case APPLE ->             itemTrade.buyItem(player, item, NPCFoodSellGUI.applePrice, e.getClick());
                case POTATO ->            itemTrade.buyItem(player, item, NPCFoodSellGUI.potatoPrice, e.getClick());
                case CARROT ->            itemTrade.buyItem(player, item, NPCFoodSellGUI.carrotPrice, e.getClick());
                case MELON_SLICE ->       itemTrade.buyItem(player, item, NPCFoodSellGUI.watermelonPrice, e.getClick());
                case BEETROOT ->          itemTrade.buyItem(player, item, NPCFoodSellGUI.beetrootPrice, e.getClick());
                case NETHER_WART ->       itemTrade.buyItem(player, item, NPCFoodSellGUI.netherwartPrice, e.getClick());
                case BEEF ->          itemTrade.buyItem(player, item, NPCFoodSellGUI.beefPrice, e.getClick());
                case PORKCHOP ->   itemTrade.buyItem(player, item, NPCFoodSellGUI.porkchopPrice, e.getClick());
                case CHICKEN ->   itemTrade.buyItem(player, item, NPCFoodSellGUI.chickenPrice, e.getClick());
                case RABBIT ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.rabbitPrice, e.getClick());
                case DRIED_KELP ->       itemTrade.buyItem(player, item, NPCFoodSellGUI.driedKelpPrice, e.getClick());
                case KELP ->             itemTrade.buyItem(player, item, NPCFoodSellGUI.kelpPrice, e.getClick());
                case CHORUS_FRUIT ->     itemTrade.buyItem(player, item, NPCFoodSellGUI.chorusFruitPrice, e.getClick());
                case COD ->              itemTrade.buyItem(player, item, NPCFoodSellGUI.codPrice, e.getClick());
                case SALMON ->           itemTrade.buyItem(player, item, NPCFoodSellGUI.salmonPrice, e.getClick());
                case TROPICAL_FISH ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.tropicalFishPrice, e.getClick());
                case MUTTON ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.muttonPrice, e.getClick());
                case GLOW_BERRIES ->     itemTrade.buyItem(player, item, NPCFoodSellGUI.glowBerriesPrice, e.getClick());
                case SWEET_BERRIES ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.sweetBerriesPrice, e.getClick());
                case MELON ->      itemTrade.buyItem(player, item, NPCFoodSellGUI.melonPrice, e.getClick());
                case PUMPKIN ->          itemTrade.buyItem(player, item, NPCFoodSellGUI.pumpkinPrice, e.getClick());
                case COOKIE ->           itemTrade.buyItem(player, item, NPCFoodSellGUI.cookiePrice, e.getClick());
                case PUMPKIN_PIE ->      itemTrade.buyItem(player, item, NPCFoodSellGUI.pumpkinPiePrice, e.getClick());
                case GOLDEN_CARROT ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.goldenCarrotPrice, e.getClick());
                case BAKED_POTATO ->     itemTrade.buyItem(player, item, NPCFoodSellGUI.cookedPotatoPrice, e.getClick());
                case BREAD ->            itemTrade.buyItem(player, item, NPCFoodSellGUI.breadPrice, e.getClick());
                case COOKED_BEEF ->      itemTrade.buyItem(player, item, NPCFoodSellGUI.steakPrice, e.getClick());
                case COOKED_PORKCHOP ->  itemTrade.buyItem(player, item, NPCFoodSellGUI.cookedPorkchopPrice, e.getClick());
                case COOKED_CHICKEN ->   itemTrade.buyItem(player, item, NPCFoodSellGUI.cookedChickenPrice, e.getClick());
                case COOKED_RABBIT ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.cookedRabbitPrice, e.getClick());
                case COOKED_MUTTON ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.cookedMuttonPrice, e.getClick());
                case COOKED_COD ->       itemTrade.buyItem(player, item, NPCFoodSellGUI.cookedCodPrice, e.getClick());
                case COOKED_SALMON ->    itemTrade.buyItem(player, item, NPCFoodSellGUI.cookedSalmonPrice, e.getClick());

                default -> {
                }
            }
        }else if( e.getView().getTitle().equalsIgnoreCase("Food Purchaser")){
            e.setCancelled(true);
            switch (item) {
                case APPLE ->             itemTrade.sellItem(player, item, NPCFoodBuyGUI.applePrice, e.getClick());
                case POTATO ->            itemTrade.sellItem(player, item, NPCFoodBuyGUI.potatoPrice, e.getClick());
                case CARROT ->            itemTrade.sellItem(player, item, NPCFoodBuyGUI.carrotPrice, e.getClick());
                case MELON_SLICE ->       itemTrade.sellItem(player, item, NPCFoodBuyGUI.watermelonPrice, e.getClick());
                case BEETROOT ->          itemTrade.sellItem(player, item, NPCFoodBuyGUI.beetrootPrice, e.getClick());
                case NETHER_WART ->       itemTrade.sellItem(player, item, NPCFoodBuyGUI.netherwartPrice, e.getClick());
                case EGG ->               itemTrade.sellItem(player, item, NPCFoodBuyGUI.eggPrice, e.getClick());
                case BEEF ->          itemTrade.sellItem(player, item, NPCFoodBuyGUI.beefPrice, e.getClick());
                case COOKED_PORKCHOP ->   itemTrade.sellItem(player, item, NPCFoodBuyGUI.porkchopPrice, e.getClick());
                case COOKED_CHICKEN ->   itemTrade.sellItem(player, item, NPCFoodBuyGUI.chickenPrice, e.getClick());
                case COOKED_RABBIT ->    itemTrade.sellItem(player, item, NPCFoodBuyGUI.rabbitPrice, e.getClick());
                case DRIED_KELP ->       itemTrade.sellItem(player, item, NPCFoodBuyGUI.driedKelpPrice, e.getClick());
                case KELP ->             itemTrade.sellItem(player, item, NPCFoodBuyGUI.kelpPrice, e.getClick());
                case CHORUS_FRUIT ->     itemTrade.sellItem(player, item, NPCFoodBuyGUI.chorusFruitPrice, e.getClick());
                case COD ->              itemTrade.sellItem(player, item, NPCFoodBuyGUI.codPrice, e.getClick());
                case SALMON ->           itemTrade.sellItem(player, item, NPCFoodBuyGUI.salmonPrice, e.getClick());
                case TROPICAL_FISH ->    itemTrade.sellItem(player, item, NPCFoodBuyGUI.tropicalFishPrice, e.getClick());
                case COOKED_MUTTON ->    itemTrade.sellItem(player, item, NPCFoodBuyGUI.muttonPrice, e.getClick());
                case GLOW_BERRIES ->     itemTrade.sellItem(player, item, NPCFoodBuyGUI.glowBerriesPrice, e.getClick());
                case SWEET_BERRIES ->    itemTrade.sellItem(player, item, NPCFoodBuyGUI.sweetBerriesPrice, e.getClick());
                case MELON ->      itemTrade.sellItem(player, item, NPCFoodBuyGUI.melonPrice, e.getClick());
                case PUMPKIN ->          itemTrade.sellItem(player, item, NPCFoodBuyGUI.pumpkinPrice, e.getClick());
                default -> {
                }
            }
        }else if( e.getView().getTitle().equalsIgnoreCase("Boost Vendor")){
            e.setCancelled(true);
            switch (item) {
                case GOLDEN_APPLE ->             itemTrade.buyItem(player, item, NPCBoostSellGUI.gapplePrice, e.getClick());
                case ENCHANTED_GOLDEN_APPLE ->   itemTrade.buyItem(player, item, NPCBoostSellGUI.egapplePrice, e.getClick());
                case POTION -> {
                    // Check for specific potion types using metadata or other identifiers
                    PotionMeta potionMeta = (PotionMeta) currentItem.getItemMeta();
                    if (potionMeta != null) {
                        if (potionMeta.getBasePotionData().getType() == PotionType.NIGHT_VISION) {
                            itemTrade.buyItem(player, item, NPCBoostSellGUI.nightVisionPotionPrice, e.getClick());
                        } else if (potionMeta.getBasePotionData().getType() == PotionType.JUMP) {
                            itemTrade.buyItem(player, item, NPCBoostSellGUI.jumpBoostPotionPrice, e.getClick());
                        } else if (potionMeta.getBasePotionData().getType() == PotionType.SPEED) {
                            itemTrade.buyItem(player, item, NPCBoostSellGUI.speedPotionPrice, e.getClick());
                        } else if (potionMeta.getBasePotionData().getType() == PotionType.REGEN) {
                            itemTrade.buyItem(player, item, NPCBoostSellGUI.regenerationPotionPrice, e.getClick());
                        } else if (potionMeta.getBasePotionData().getType() == PotionType.STRENGTH) {
                            itemTrade.buyItem(player, item, NPCBoostSellGUI.strengthPotionPrice, e.getClick());
                        } else if (potionMeta.getBasePotionData().getType() == PotionType.SLOW_FALLING) {
                            itemTrade.buyItem(player, item, NPCBoostSellGUI.slowFallingSplashPotionPrice, e.getClick());
                        } else {
                            // Handle unknown potion types or cases where item is not a potion
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

}

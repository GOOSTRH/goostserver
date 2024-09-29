package me.goost.goostserver.server.Casino;

import me.goost.goostserver.GoostServer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class casinoMachines implements Listener {
    private final List<Location> buttonLocations = Arrays.asList(
            new Location(Bukkit.getWorld("world"), -15, 161, -26),
            new Location(Bukkit.getWorld("world"), -15, 161, -29),
            new Location(Bukkit.getWorld("world"), -15, 161, -32)
    );

    @EventHandler
    public void onButtonPress(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        Location blockLocation = block.getLocation();

        // Check if the block is a button and if it's at any of the specified coordinates
        if (isButton(block.getType()) && isButtonLocation(blockLocation)) {
            // Check if the button was pressed (powered)
            if (event.getOldCurrent() == 0 && event.getNewCurrent() > 0) {
                // Button pressed, remove it and start slot machine process
                Player player = findNearestPlayer(blockLocation);
                if (player != null) {
                    block.setType(Material.AIR); // Remove the button
                    Bukkit.getLogger().info("Button at " + blockLocation + " was pressed and removed!");
                    runSlotMachine(player, blockLocation, (Block) block.getBlockData());
                }
            }
        }
    }

    private Player findNearestPlayer(Location location) {
        double nearestDistance = Double.MAX_VALUE;
        Player nearestPlayer = null;
        for (Player player : Bukkit.getOnlinePlayers()) {
            double distance = player.getLocation().distance(location);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPlayer = player;
            }
        }
        return nearestPlayer;
    }

    private boolean isButton(Material material) {
        return material == Material.STONE_BUTTON || material == Material.OAK_BUTTON ||
                material == Material.SPRUCE_BUTTON || material == Material.BIRCH_BUTTON ||
                material == Material.JUNGLE_BUTTON || material == Material.ACACIA_BUTTON ||
                material == Material.DARK_OAK_BUTTON || material == Material.CRIMSON_BUTTON ||
                material == Material.WARPED_BUTTON || material == Material.POLISHED_BLACKSTONE_BUTTON;
    }

    private boolean isButtonLocation(Location location) {
        return buttonLocations.stream().anyMatch(loc -> loc.equals(location));
    }

    private static final String[] NOTHING_SYMBOLS = {"☹", "☠"}; // 50% chance
    private static final String[] MULTIPLIER_1_5X_SYMBOLS = {"❤", "☀"}; // 23% chance
    private static final String[] MULTIPLIER_2X_SYMBOLS = {"♠", "♥", "♦", "♣"}; // 23% chance
    private static final String[] RARE_SYMBOLS = {"☺", "7"}; // Low chance

    private void runSlotMachine(Player player, Location buttonLocation, Block buttonBlock) {
        Random random = new Random();

        String[] reels = new String[3];

        new BukkitRunnable() {
            int index = 0;
            long delay = 0L;

            @Override
            public void run() {
                if (index < reels.length) {
                    reels[index] = spinReel(random);
                    player.sendMessage(ChatColor.GREEN + "Reel " + (index + 1) + ": " + reels[index]);
                    playReelSound(player, reels[index]);

                    // Increment index and increase delay to simulate slowing down
                    index++;
                    delay += 10L;
                    this.runTaskLater(GoostServer.getPlugin(), delay);
                } else {
                    // All reels stopped, display results and place button back
                    double multiplier = calculateMultiplier(reels);
                    if (multiplier > 0) {
                        player.sendMessage(ChatColor.GOLD + "Congratulations! You won " + multiplier + "x your bet!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Sorry, you lost. Better luck next time.");
                    }

                    // Restore the button
                    buttonLocation.getBlock().setType(buttonBlock.getType());
                    buttonLocation.getBlock().setBlockData((BlockData) buttonBlock);
                    Bukkit.getLogger().info("Button replaced at " + buttonLocation);
                    this.cancel();
                }
            }
        }.runTaskLater(GoostServer.getPlugin(), 10L); // Initial delay of 10 ticks before the first reel spins
    }

    private void playReelSound(Player player, String symbol) {
        Sound sound;
        switch (symbol) {
            case "☺":
            case "7":
                sound = Sound.BLOCK_NOTE_BLOCK_BELL;
                break;
            case "❤":
            case "☀":
                sound = Sound.BLOCK_NOTE_BLOCK_HARP;
                break;
            case "♠":
            case "♥":
            case "♦":
            case "♣":
                sound = Sound.BLOCK_NOTE_BLOCK_BASEDRUM;
                break;
            default:
                sound = Sound.BLOCK_NOTE_BLOCK_PLING;
        }
        player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
    }

    private String spinReel(Random random) {
        int chance = random.nextInt(100); // Generate a number between 0 and 99

        if (chance < 50) {
            return NOTHING_SYMBOLS[random.nextInt(NOTHING_SYMBOLS.length)];
        } else if (chance < 73) { // 50 + 23 = 73
            return MULTIPLIER_1_5X_SYMBOLS[random.nextInt(MULTIPLIER_1_5X_SYMBOLS.length)];
        } else if (chance < 96) { // 73 + 23 = 96
            return MULTIPLIER_2X_SYMBOLS[random.nextInt(MULTIPLIER_2X_SYMBOLS.length)];
        } else {
            return RARE_SYMBOLS[random.nextInt(RARE_SYMBOLS.length)];
        }
    }

    private double calculateMultiplier(String[] reels) {
        if (reels[0].equals(reels[1]) && reels[1].equals(reels[2])) {
            return switch (reels[0]) {
                case "☺" -> 5.0;
                case "7" -> 50.0;
                default -> 3.0;
            };
        }
        return switch (reels[0]) {
            case "☺", "7" -> 5.0;
            case "❤", "☀" -> 1.5;
            case "♠", "♥", "♦", "♣" -> 2.0;
            default -> 0;
        };
    }
}

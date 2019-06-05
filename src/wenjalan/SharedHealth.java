package wenjalan;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import wenjalan.commands.SetHealthBonusPerPlayerCommand;
import wenjalan.listeners.SharedHealthListener;

public class SharedHealth extends JavaPlugin {

    // the amount of health to add to the pool for each player
    public static double health_bonus_per_player = 10.0D;

    // the maximum health of the server
    public static double max_health = 10.0D;

    // the current health of the server
    public static double current_health = 10.0D;

    @Override
    public void onEnable() {
        // attach listeners
        Bukkit.getServer().getPluginManager().registerEvents(new SharedHealthListener(), this);

        // attach commands
        Bukkit.getPluginCommand("sethealthbonusperplayer").setExecutor(new SetHealthBonusPerPlayerCommand());
    }

    @Override
    public void onDisable() {
        // set everyone's health back to normal
        max_health = 20.0D;
        current_health = 20.0D;
        SharedHealthListener.syncPlayers();
    }

}

package wenjalan;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
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
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}

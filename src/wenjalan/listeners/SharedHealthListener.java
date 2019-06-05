package wenjalan.listeners;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import wenjalan.SharedHealth;

public class SharedHealthListener implements Listener {

    // fires when an entity receives damage
    // deals the damage taken by a player to all other players on the server
    @EventHandler
    public void onPlayerHurtEvent(EntityDamageEvent e) {
        // check if this entity is a player
        if (e.getEntity() instanceof Player) {
            // if the damage source wasn't a natural cause, quit
            if (e.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM)) {
                return;
            }

            // get the player
            Player p = (Player) e.getEntity();

            // get the amount of damage taken
            double damageTaken = e.getFinalDamage();

            // deal that much damage to everyone else
            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                // if this player isn't the player that took damage
                if (otherPlayer.getUniqueId() != p.getUniqueId()) {
                    // damage them
                    otherPlayer.damage(damageTaken);
                }
            }

            // set new health values
            SharedHealth.current_health = p.getHealth();

            // ensure sync
            syncPlayers();
        }
    }

    // fires when a player recovers health
    // unsure if this method is triggered by Player#setHealth()
    @EventHandler
    public void onPlayerHeal(EntityRegainHealthEvent e) {
        // check if this is a player
        if (e.getEntity() instanceof Player) {
            // check if the source was a custom source, and if so, quit
            if (e.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.CUSTOM)) {
                return;
            }

            // get the player
            Player p = (Player) e.getEntity();

            // get the amount healed
            double damageHealed = e.getAmount();

            // heal everyone else by that much
            // deal that much damage to everyone else
            for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
                // if this player isn't the player that took damage
                if (otherPlayer.getUniqueId() != p.getUniqueId()) {
                    // heal them
                    otherPlayer.setHealth(otherPlayer.getHealth() + damageHealed);
                }
            }

            // set new health values
            SharedHealth.current_health = p.getHealth();

            // ensure sync
            syncPlayers();
        }
    }

    // when a player joins, set everyone's health accordingly
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // set new max health
        // get the number of players on the server
        int playersOnline = Bukkit.getOnlinePlayers().size();

        // set the max health
        SharedHealth.max_health = SharedHealth.health_bonus_per_player * playersOnline;

        // ensure sync
        syncPlayers();
    }

    // when a player leaves, set everyone's health
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        // set new max health
        // get the number of players on the server
        int playersOnline = Bukkit.getOnlinePlayers().size();

        // set the max health
        SharedHealth.max_health = SharedHealth.health_bonus_per_player * playersOnline;

        // ensure sync
        syncPlayers();
    }

    // when a player dies, kill everyone else
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        // get the player
        Player culprit = e.getEntity();

        // check if we've killed everyone yet
        if (SharedHealth.current_health != 0.0D) {
            // kill everyone
            SharedHealth.current_health = 0.0D;

            // sync
            syncPlayers();
        }

        // send a message
        e.setDeathMessage(culprit.getDisplayName() + " died, causing everyone else to die");
    }

    // when someone respawns, stop killing everyone
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        // get the player
        Player respawnee = e.getPlayer();

        // set their values for everyone else
        SharedHealth.current_health = respawnee.getHealth();

        // don't sync to avoid resurrection issues
        // syncPlayers();
    }

    // sets all players healths to the collective amounts, setting max health and current health defined in SharedHealth
    public static void syncPlayers() {
        // for all players
        for (Player p : Bukkit.getOnlinePlayers()) {
            // if their max health isn't already standardized
            if (p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() != SharedHealth.max_health) {
                // set their max health to the standard
                AttributeInstance maxHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                if (maxHealth != null) {
                    maxHealth.setBaseValue(SharedHealth.max_health);
                }
            }

            // if their health isn't already the current health
            if (p.getHealth() != SharedHealth.current_health) {
                // set their current health to the current
                p.setHealth(SharedHealth.current_health);
            }
        }
    }

}

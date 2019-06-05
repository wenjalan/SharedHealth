package wenjalan.listeners;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import wenjalan.SharedHealth;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class SharedHealthListener implements Listener {

    // the Shared Health "entity", acts as the source of the damage to prevent loops
    // really just an empty Entity, shouldn't be used for anything but a damage source
    public class SharedHealthEntity implements Entity {

        // the player that caused this SharedHealthEntity
        protected Player culprit;

        SharedHealthEntity(Player culprit) {
            this.culprit = culprit;
        }

        @Override
        public Location getLocation() {
            return null;
        }

        @Override
        public Location getLocation(Location location) {
            return null;
        }

        @Override
        public void setVelocity(Vector vector) {

        }

        @Override
        public Vector getVelocity() {
            return null;
        }

        @Override
        public double getHeight() {
            return 0;
        }

        @Override
        public double getWidth() {
            return 0;
        }

        @Override
        public BoundingBox getBoundingBox() {
            return null;
        }

        @Override
        public boolean isOnGround() {
            return false;
        }

        @Override
        public World getWorld() {
            return null;
        }

        @Override
        public void setRotation(float v, float v1) {

        }

        @Override
        public boolean teleport(Location location) {
            return false;
        }

        @Override
        public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
            return false;
        }

        @Override
        public boolean teleport(Entity entity) {
            return false;
        }

        @Override
        public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
            return false;
        }

        @Override
        public List<Entity> getNearbyEntities(double v, double v1, double v2) {
            return null;
        }

        @Override
        public int getEntityId() {
            return 0;
        }

        @Override
        public int getFireTicks() {
            return 0;
        }

        @Override
        public int getMaxFireTicks() {
            return 0;
        }

        @Override
        public void setFireTicks(int i) {

        }

        @Override
        public void remove() {

        }

        @Override
        public boolean isDead() {
            return false;
        }

        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public Server getServer() {
            return null;
        }

        @Override
        public boolean isPersistent() {
            return false;
        }

        @Override
        public void setPersistent(boolean b) {

        }

        @Override
        public Entity getPassenger() {
            return null;
        }

        @Override
        public boolean setPassenger(Entity entity) {
            return false;
        }

        @Override
        public List<Entity> getPassengers() {
            return null;
        }

        @Override
        public boolean addPassenger(Entity entity) {
            return false;
        }

        @Override
        public boolean removePassenger(Entity entity) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean eject() {
            return false;
        }

        @Override
        public float getFallDistance() {
            return 0;
        }

        @Override
        public void setFallDistance(float v) {

        }

        @Override
        public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {

        }

        @Override
        public EntityDamageEvent getLastDamageCause() {
            return null;
        }

        @Override
        public UUID getUniqueId() {
            return null;
        }

        @Override
        public int getTicksLived() {
            return 0;
        }

        @Override
        public void setTicksLived(int i) {

        }

        @Override
        public void playEffect(EntityEffect entityEffect) {

        }

        @Override
        public EntityType getType() {
            return null;
        }

        @Override
        public boolean isInsideVehicle() {
            return false;
        }

        @Override
        public boolean leaveVehicle() {
            return false;
        }

        @Override
        public Entity getVehicle() {
            return null;
        }

        @Override
        public void setCustomNameVisible(boolean b) {

        }

        @Override
        public boolean isCustomNameVisible() {
            return false;
        }

        @Override
        public void setGlowing(boolean b) {

        }

        @Override
        public boolean isGlowing() {
            return false;
        }

        @Override
        public void setInvulnerable(boolean b) {

        }

        @Override
        public boolean isInvulnerable() {
            return false;
        }

        @Override
        public boolean isSilent() {
            return false;
        }

        @Override
        public void setSilent(boolean b) {

        }

        @Override
        public boolean hasGravity() {
            return false;
        }

        @Override
        public void setGravity(boolean b) {

        }

        @Override
        public int getPortalCooldown() {
            return 0;
        }

        @Override
        public void setPortalCooldown(int i) {

        }

        @Override
        public Set<String> getScoreboardTags() {
            return null;
        }

        @Override
        public boolean addScoreboardTag(String s) {
            return false;
        }

        @Override
        public boolean removeScoreboardTag(String s) {
            return false;
        }

        @Override
        public PistonMoveReaction getPistonMoveReaction() {
            return null;
        }

        @Override
        public BlockFace getFacing() {
            return null;
        }

        @Override
        public Pose getPose() {
            return null;
        }

        @Override
        public Spigot spigot() {
            return null;
        }

        @Override
        public String getCustomName() {
            return null;
        }

        @Override
        public void setCustomName(String s) {

        }

        @Override
        public void sendMessage(String s) {

        }

        @Override
        public void sendMessage(String[] strings) {

        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public void setMetadata(String s, MetadataValue metadataValue) {

        }

        @Override
        public List<MetadataValue> getMetadata(String s) {
            return null;
        }

        @Override
        public boolean hasMetadata(String s) {
            return false;
        }

        @Override
        public void removeMetadata(String s, Plugin plugin) {

        }

        @Override
        public boolean isPermissionSet(String s) {
            return false;
        }

        @Override
        public boolean isPermissionSet(Permission permission) {
            return false;
        }

        @Override
        public boolean hasPermission(String s) {
            return false;
        }

        @Override
        public boolean hasPermission(Permission permission) {
            return false;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
            return null;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin) {
            return null;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
            return null;
        }

        @Override
        public PermissionAttachment addAttachment(Plugin plugin, int i) {
            return null;
        }

        @Override
        public void removeAttachment(PermissionAttachment permissionAttachment) {

        }

        @Override
        public void recalculatePermissions() {

        }

        @Override
        public Set<PermissionAttachmentInfo> getEffectivePermissions() {
            return null;
        }

        @Override
        public boolean isOp() {
            return false;
        }

        @Override
        public void setOp(boolean b) {

        }

        @Override
        public PersistentDataContainer getPersistentDataContainer() {
            return null;
        }
    }

    // fires when an entity receives damage
    // deals the damage taken by a player to all other players on the server
    @EventHandler
    public void onPlayerHurtEvent(EntityDamageEvent e) {
        // check if this entity is a player
        if (e.getEntity() instanceof Player) {
            // if the source was a SharedHealthEntity, quit
            if (e.getEntity() instanceof SharedHealthEntity) {
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
                    otherPlayer.damage(damageTaken, new SharedHealthEntity(p));
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

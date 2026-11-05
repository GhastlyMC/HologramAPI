package io.github.proxycipher.hologram;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public final class HologramBuilder {

    private static final double LINE_OFFSET = 0.25;

    private final Set<Player> players = new HashSet<>();
    private final List<Entity> lines  = new ArrayList<>();
    private final Location hologramLocation;
    private final VersionSupport versionSupport = NMS.getVersionSupport();

    private double currentOffset = 0.0;

    public HologramBuilder(Location location) {
        this.hologramLocation = location.clone();
    }

    public HologramBuilder addPlayer(Player player) {
        players.add(player);
        update(player);
        return this;
    }

    private void update(Player player) {
        for (Entity entity : lines) {
            if (entity instanceof Item) {
                versionSupport.spawnItem(entity, player);
                continue;
            }
            versionSupport.spawnEntityLiving(entity, player);
        }
    }

    public HologramBuilder update() {
        for (Player player : players) {
            update(player);
        }
        return this;
    }

    public HologramBuilder add(String line) {
        Entity entity = versionSupport.createHologramLine(
                line,
                hologramLocation.clone().subtract(0, currentOffset, 0)
        );
        lines.add(entity);
        currentOffset += LINE_OFFSET;
        return this;
    }

    public HologramBuilder add(ItemStack itemStack) {
        Entity entity = versionSupport.createHologramItem(
                itemStack, hologramLocation.clone().subtract(0, currentOffset, 0)
        );
        lines.add(entity);
        currentOffset += entity.getHeight();
        return this;
    }

    public HologramBuilder clear() {
        for (Entity entity : lines) {
            for (Player player : players) {
                versionSupport.despawnEntity(entity, player);
            }
        }
        lines.clear();
        currentOffset = 0.0;
        return this;
    }
}

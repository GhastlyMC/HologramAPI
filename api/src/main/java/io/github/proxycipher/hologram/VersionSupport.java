package io.github.proxycipher.hologram;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class VersionSupport {

    abstract Entity createHologramLine(String line, Location location);
    abstract Entity createHologramItem(ItemStack itemStack, Location location);
    abstract void spawnEntityLiving(Entity entity, Player player);
    abstract void spawnItem(Entity entity, Player player);
    abstract void despawnEntity(Entity entity, Player player);

}

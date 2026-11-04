package io.github.proxycipher.hologram;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class v1_12_R1 extends VersionSupport {

    private void sendPacket(Player player, Packet packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    private void setEntityLocation(net.minecraft.server.v1_12_R1.Entity entity, Location location) {
        entity.setLocation(
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                location.getYaw()
        );
    }

    @Override
    public Entity createHologramLine(String line, Location location) {
        EntityArmorStand armorStand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle());
        setEntityLocation(armorStand, location);
        armorStand.setInvisible(true);
        armorStand.setCustomName(line);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setNoGravity(true);
        armorStand.setMarker(true);
        return armorStand.getBukkitEntity();
    }

    @Override
    public Entity createHologramItem(ItemStack itemStack, Location location) {
        EntityItem item = new EntityItem(((CraftWorld) location.getWorld()).getHandle());
        setEntityLocation(item, location);
        net.minecraft.server.v1_12_R1.ItemStack NMSItemStack = CraftItemStack.asNMSCopy(itemStack);
        item.setItemStack(NMSItemStack);
        item.setInvulnerable(true);
        item.setNoGravity(true);
        return item.getBukkitEntity();
    }

    @Override
    public void spawnEntityLiving(Entity entity, Player player) {
        net.minecraft.server.v1_12_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
        sendPacket(player, new PacketPlayOutSpawnEntityLiving(
            (EntityLiving) nmsEntity
        ));
    }

    @Override
    public void spawnItem(Entity entity, Player player) {
        net.minecraft.server.v1_12_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
        sendPacket(player, new PacketPlayOutSpawnEntity(nmsEntity, 2));
        sendPacket(player, new PacketPlayOutEntityMetadata(nmsEntity.getId(), nmsEntity.getDataWatcher(), true));
    }

    @Override
    void despawnEntity(Entity entity, Player player) {
        sendPacket(player, new PacketPlayOutEntityDestroy(entity.getEntityId()));
    }
}

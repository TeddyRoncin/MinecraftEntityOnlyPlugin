package fr.teddy.entityonlymcplugin.command;

import fr.teddy.entityonlymcplugin.EntityOnly;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.kyori.adventure.pointer.Pointer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.EntityNBTComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Shulker;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class Apply implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (EntityOnly.pos1 == null) {
            commandSender.sendMessage("Pos1 is not defined");
            return true;
        }
        if (EntityOnly.pos2 == null) {
            commandSender.sendMessage("Pos2 is not defined");
            return true;
        }
        World world = EntityOnly.pos1.getWorld();
        int pos1X = Math.min(EntityOnly.pos1.getBlockX(), EntityOnly.pos2.getBlockX());
        int pos1Y = Math.min(EntityOnly.pos1.getBlockY(), EntityOnly.pos2.getBlockY());
        int pos1Z = Math.min(EntityOnly.pos1.getBlockZ(), EntityOnly.pos2.getBlockZ());
        int pos2X = Math.max(EntityOnly.pos1.getBlockX(), EntityOnly.pos2.getBlockX());
        int pos2Y = Math.max(EntityOnly.pos1.getBlockY(), EntityOnly.pos2.getBlockY());
        int pos2Z = Math.max(EntityOnly.pos1.getBlockZ(), EntityOnly.pos2.getBlockZ());
        for (int x = pos1X; x <= pos2X; x++) {
            for (int y = pos1Y; y <= pos2Y; y++) {
                for (int z = pos1Z; z <= pos2Z; z++) {
                    System.out.println("setting a block ! at " + x  + " " + y + " " + z);
                    Block block = world.getBlockAt(x, y, z);
                    if (!block.getType().isCollidable()) {
                        continue;
                    }
                    FallingBlock fallingBlock = world.spawnFallingBlock(new Location(world, x+0.5, y, z+0.5), block.getBlockData());
                    fallingBlock.setGravity(false);
                    fallingBlock.setMetadata("Time", new FixedMetadataValue(EntityOnly.instance, -999999999));
                    Shulker shulker = (Shulker) world.spawnEntity(new Location(world, x+0.5, y, z+0.5), EntityType.SHULKER);
                    shulker.setGravity(false);
                    shulker.setInvisible(true);
                    shulker.setAI(false);
                    shulker.setInvulnerable(true);
                    shulker.setPersistent(true);
                    world.setBlockData(x, y, z, Material.AIR.createBlockData());
                }
            }
        }
        return true;
    }
}

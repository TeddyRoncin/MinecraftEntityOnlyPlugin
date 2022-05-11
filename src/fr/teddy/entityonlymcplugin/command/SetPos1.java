package fr.teddy.entityonlymcplugin.command;

import fr.teddy.entityonlymcplugin.EntityOnly;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SetPos1 implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player !");
            return true;
        }
        EntityOnly.pos1 = ((Player) commandSender).getLocation().toBlockLocation();
        commandSender.sendMessage("pos1 = " + EntityOnly.pos1.getX() + " " + EntityOnly.pos1.getY() + " " + EntityOnly.pos1.getZ());
        return true;
    }

}

package fr.teddy.entityonlymcplugin;

import fr.teddy.entityonlymcplugin.command.Apply;
import fr.teddy.entityonlymcplugin.command.SetPos1;
import fr.teddy.entityonlymcplugin.command.SetPos2;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
public class EntityOnly extends JavaPlugin {

    public static Location pos1;
    public static Location pos2;

    public static EntityOnly instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("setpos1").setExecutor(new SetPos1());
        this.getCommand("setpos2").setExecutor(new SetPos2());
        this.getCommand("apply").setExecutor(new Apply());
    }

    @Override
    public void onDisable() {
        System.out.println("disabled !");
    }
}

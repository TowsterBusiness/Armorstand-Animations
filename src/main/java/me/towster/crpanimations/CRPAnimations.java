package me.towster.crpanimations;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class CRPAnimations extends JavaPlugin {

    static List<AnimStands> animationArmorStands;
    static ArmorChoices armorChoices;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("anim").setExecutor(new Commands());
        this.getCommand("save-data").setExecutor(new SaveData());

        animationArmorStands = new ArrayList<AnimStands>();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run() {
                for (AnimStands stand : animationArmorStands) {
                    stand.tick(3);
                }
            }

        }, 0L, 3L);

        ArmorChoices oldData = ArmorChoices.loadData("ArmorChoices.json");
        if (oldData == null) {
            armorChoices = new ArmorChoices();
        } else {
            armorChoices = oldData;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}



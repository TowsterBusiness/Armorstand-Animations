package me.towster.crpanimations;

import me.towster.crpanimations.Animations.WaveAnimation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SaveData implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CRPAnimations.armorChoices.saveData("ArmorChoices.json");
        return true;
    }
}
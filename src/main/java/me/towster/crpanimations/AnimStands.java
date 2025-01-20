package me.towster.crpanimations;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.UUID;

public class AnimStands {
    /*
        Storage Class
        - Keeps the pointer for the armor stand
        - Stores keyframes for the armor stand
     */

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    ArmorStand armorStand;
    public ArrayList<KeyFrame> keyFrames;
    public int tickCount = 0;

    public AnimStands(World world, Location loc, Player player) {
        armorStand = (ArmorStand) world.spawn(loc, ArmorStand.class);
        armorStand.setArms(true);
        UUID playerUUID = player.getUniqueId();
        ArmorChoiceData armorChoiceData = CRPAnimations.armorChoices.data.get(playerUUID);
        if (armorChoiceData == null) {
            armorChoiceData = CRPAnimations.armorChoices.addPlayerData(player);
        }
        ItemStack[] armorList = armorChoiceData.data;
        if (armorList[0] != null) {
            armorStand.getEquipment().setHelmet(armorList[0]);//.setArmorContents(armorList);
            armorStand.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            armorStand.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        }
        keyFrames = new ArrayList<KeyFrame>();

        PersistentDataContainer data = player.getPersistentDataContainer();

        CRPAnimations.animationArmorStands.add(this);
    }

    public void tick(int time) {
        tickCount += time;
        for (KeyFrame keyFrame : keyFrames) {
            keyFrame.move(armorStand, tickCount);
        }
    }
}

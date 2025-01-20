package me.towster.crpanimations;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

class ArmorChoiceData {
    ItemStack[] data = null;
    ArmorChoiceData(ItemStack[] data) {
        if (data.length != 4) {
            System.out.println("Array has " + data.length + " items not 4!");
            return;
        }
        this.data = data;
    }
}

public class ArmorChoices implements Serializable {
    private static transient final long serialVersionUID = -1681012206529286330L;

    public final HashMap<UUID, ArmorChoiceData> data;


    public ArmorChoices() {
        this.data = new HashMap<UUID, ArmorChoiceData>();
    }

    // Can be used for saving
    public ArmorChoices(HashMap<UUID, ArmorChoiceData> data) {
        this.data = data;
    }
    // Can be used for loading
    public ArmorChoices(ArmorChoices loadedData) {
        this.data = loadedData.data;
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(Files.newOutputStream(Paths.get(filePath))));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public static ArmorChoices loadData(String filePath) {
        try {
            System.out.println("Loading " + filePath);
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(Files.newInputStream(Paths.get(filePath))));
            ArmorChoices data = (ArmorChoices) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public ArmorChoiceData addPlayerData(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setUnbreakable(true);
        meta.setOwningPlayer(player);
        head.setItemMeta(meta);
        ItemStack[] armorSet = new ItemStack[4];
        armorSet[0] = head;
        ArmorChoiceData armorChoice = new ArmorChoiceData(armorSet);
        data.put(player.getUniqueId(), armorChoice);
        Bukkit.getServer().getLogger().log(Level.INFO, player.getName() + "'s data");
        return armorChoice;
    }
}

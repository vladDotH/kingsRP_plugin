package com.vladdoth.kings_rp_plugin.jobs;

import com.vladdoth.kings_rp_plugin.ConfigFields;
import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JobsMenu implements Listener {
    public static final String CLOSE_ITEM = "Закрыть меню",
            MENU_NAME = Plugin.getInstance().getConfig().getString(ConfigFields.MENU_NAME);

    private static ItemStack miner = new ItemStack(Material.IRON_PICKAXE),
            lumber = new ItemStack(Material.IRON_AXE),
            farmer = new ItemStack(Material.IRON_HOE),
            hunter = new ItemStack(Material.IRON_SWORD),
            close = new ItemStack(Material.BARRIER);

    public static void openMenu(Player p) {
        Inventory menu = Bukkit.createInventory(p, 9, MENU_NAME);

        ItemMeta meta = miner.getItemMeta();
        meta.setDisplayName(Jobs.MINER.convert());
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        miner.setItemMeta(meta);

        meta = lumber.getItemMeta();
        meta.setDisplayName(Jobs.LUMBER.convert());
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        lumber.setItemMeta(meta);

        meta = farmer.getItemMeta();
        meta.setDisplayName(Jobs.FARMER.convert());
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        farmer.setItemMeta(meta);

        meta = hunter.getItemMeta();
        meta.setDisplayName(Jobs.HUNTER.convert());
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        hunter.setItemMeta(meta);

        meta = close.getItemMeta();
        meta.setDisplayName(CLOSE_ITEM);
        close.setItemMeta(meta);

        menu.addItem(miner, lumber, farmer, hunter);
        menu.setItem(8, close);
        p.openInventory(menu);
    }

    @EventHandler
    public void guiInteract(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals(MENU_NAME))
            return;

        event.setCancelled(true);

        UserData user = Plugin.getInstance().getUsers().get(event.getWhoClicked().getName());
        String employMsg = ChatColor.YELLOW + "Вы успешно устроились на работу: ";

        switch (event.getCurrentItem().getType()) {
            case IRON_PICKAXE:
                user.getSkills().setJob(Jobs.MINER);
                event.getWhoClicked().sendMessage(employMsg + "шахтёра");
                break;
            case IRON_AXE:
                user.getSkills().setJob(Jobs.LUMBER);
                event.getWhoClicked().sendMessage(employMsg + "лесоруба");
                break;
            case IRON_HOE:
                user.getSkills().setJob(Jobs.FARMER);
                event.getWhoClicked().sendMessage(employMsg + "фермера");
                break;
            case IRON_SWORD:
                user.getSkills().setJob(Jobs.HUNTER);
                event.getWhoClicked().sendMessage(employMsg + "охотника");
                break;
            case BARRIER:
                event.getWhoClicked().closeInventory();
        }

        event.getWhoClicked().closeInventory();
    }
}

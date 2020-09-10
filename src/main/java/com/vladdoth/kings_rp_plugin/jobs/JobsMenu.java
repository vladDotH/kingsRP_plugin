package com.vladdoth.kings_rp_plugin.jobs;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JobsMenu implements Listener {
    public static final String QUIT_ITEM = "Уволиться",
            employMsg = ChatColor.YELLOW + "Вы успешно устроились на работу: ",
            quitMsg = ChatColor.RED + "Вы уволились",
            employWarn = ChatColor.RED + "Вы уже на этой работе",
            quitWarn = ChatColor.RED + "Вы и так не работаете";


    private static ItemStack miner = new ItemStack(Material.IRON_PICKAXE),
            lumber = new ItemStack(Material.IRON_AXE),
            farmer = new ItemStack(Material.IRON_HOE),
            hunter = new ItemStack(Material.IRON_SWORD),
            none = new ItemStack(Material.BARRIER);

    public static void openMenu(Player p) {
        Inventory menu = Bukkit.createInventory(p, 9, Values.JOB_MENU_NAME);

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

        meta = none.getItemMeta();
        meta.setDisplayName(QUIT_ITEM);
        none.setItemMeta(meta);

        menu.addItem(miner, lumber, farmer, hunter, none);
        p.openInventory(menu);
    }

    @EventHandler
    public void guiInteract(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals(Values.JOB_MENU_NAME))
            return;

        event.setCancelled(true);

        UserData user = Plugin.getInstance().getUsers().get(event.getWhoClicked().getName());
        HumanEntity p = event.getWhoClicked();

        Material type = event.getCurrentItem().getType();
        if (type == miner.getType()) {
            if (user.getSkills().getJob() == Jobs.MINER){
                p.sendMessage(employWarn);
                return;
            }
            user.getSkills().setJob(Jobs.MINER);
            p.sendMessage(employMsg + Jobs.MINER.convert());

        } else if (type == lumber.getType()) {
            if (user.getSkills().getJob() == Jobs.LUMBER){
                p.sendMessage(employWarn);
                return;
            }
            user.getSkills().setJob(Jobs.LUMBER);
            event.getWhoClicked().sendMessage(employMsg + Jobs.LUMBER.convert());

        } else if (type == farmer.getType()) {
            if (user.getSkills().getJob() == Jobs.FARMER){
                p.sendMessage(employWarn);
                return;
            }
            user.getSkills().setJob(Jobs.FARMER);
            event.getWhoClicked().sendMessage(employMsg + Jobs.FARMER.convert());

        } else if (type == hunter.getType()) {
            if (user.getSkills().getJob() == Jobs.HUNTER){
                p.sendMessage(employWarn);
                return;
            }
            user.getSkills().setJob(Jobs.HUNTER);
            event.getWhoClicked().sendMessage(employMsg + Jobs.HUNTER.convert());

        } else if (type == none.getType()) {
            if (user.getSkills().getJob() == Jobs.NONE){
                p.sendMessage(quitWarn);
                return;
            }

            user.getSkills().setJob(Jobs.NONE);
            event.getWhoClicked().sendMessage(quitMsg);
        }

        event.getWhoClicked().closeInventory();
    }
}

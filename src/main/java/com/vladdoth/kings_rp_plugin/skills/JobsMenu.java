package com.vladdoth.kings_rp_plugin.skills;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.mask.BinaryMask;
import org.ipvp.canvas.mask.Mask;
import org.ipvp.canvas.mask.RecipeMask;
import org.ipvp.canvas.slot.ClickOptions;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;

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

    private static ItemStack[] options = {none, miner, farmer, lumber, hunter};

    public static void openMenu(Player p) {
        Menu menu = ChestMenu.builder(1)
                .title(Values.JOB_MENU_NAME)
                .build();

        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta border_meta = border.getItemMeta();
        border_meta.setDisplayName(" ");
        border.setItemMeta(border_meta);

        Mask mask = BinaryMask.builder(menu)
                .item(new ItemStack(border))
                .pattern("110000011")
                .build();

        mask.apply(menu);

        SkillsData skills = Plugin.getInstance().getUsers().get(p.getName()).getSkills();

        int shift = 2;
        for (int i = 0; i < options.length; i++) {
            Slot slot = menu.getSlot(i + shift);
            slot.setClickOptions(ClickOptions.builder()
                    .allow(ClickType.LEFT)
                    .build());

            final int pos = i;
            slot.setItemTemplate(
                    (player) -> {
                        ItemStack item = options[pos];
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(Jobs
                                .values()[pos]
                                .convert());
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        item.setItemMeta(meta);
                        return item;
                    });

            slot.setClickHandler(
                    (player, info) -> {
                        if (pos == skills.getJob().ordinal())
                            if (pos == 0)
                                player.sendMessage(quitWarn);
                            else
                                player.sendMessage(employWarn);

                        else {
                            Jobs job = Jobs.values()[pos];
                            skills.setJob(job);

                            if (pos == 0)
                                player.sendMessage(quitMsg);
                            else
                                player.sendMessage(employMsg + job.convert());

                            player.closeInventory();
                        }
                    });
        }

        menu.open(p);
    }
}

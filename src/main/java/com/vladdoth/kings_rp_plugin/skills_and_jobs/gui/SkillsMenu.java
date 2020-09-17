package com.vladdoth.kings_rp_plugin.skills_and_jobs.gui;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Skills;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.data.JobData;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.data.SkillsData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.mask.BinaryMask;
import org.ipvp.canvas.mask.Mask;
import org.ipvp.canvas.slot.ClickOptions;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;

import java.util.Arrays;
import java.util.Date;

public class SkillsMenu {
    private static ItemStack
            mining = new ItemStack(Material.IRON_PICKAXE),
            farming = new ItemStack(Material.IRON_HOE),
            lumbering = new ItemStack(Material.IRON_AXE),
            attack = new ItemStack(Material.IRON_SWORD),
            shooting = new ItemStack(Material.BOW),
            breeding = new ItemStack(Material.SHEARS),
            fishing = new ItemStack(Material.FISHING_ROD);

    private static ItemStack[] options = {mining, farming, lumbering, attack,
            shooting, breeding, fishing};


    public static void openMenu(Player p) {
        Menu menu = ChestMenu.builder(3)
                .title(Values.SKILLS_MENU_NAME)
                .build();

        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta border_meta = border.getItemMeta();
        border_meta.setDisplayName(" ");
        border.setItemMeta(border_meta);

        ItemStack web = new ItemStack(Material.WEB);
        ItemMeta web_meta = web.getItemMeta();
        web_meta.setDisplayName(" ");
        web.setItemMeta(web_meta);

        Mask mask = BinaryMask.builder(menu)
                .item(new ItemStack(border))
                .pattern("111111111")
                .item(new ItemStack(web))
                .pattern("100000001")
                .item(new ItemStack(border))
                .pattern("111111111")
                .build();

        mask.apply(menu);

        SkillsData skills = Plugin.getInstance().getUsers().get(p.getName()).getSkills();

        int shift = 10;
        for (int i = 0; i < options.length; i++) {
            Slot slot = menu.getSlot(i + shift);

            final int pos = i;
            slot.setItemTemplate(
                    (player) -> {
                        ItemStack item = new ItemStack(options[pos]);
                        ItemMeta meta = item.getItemMeta();
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        meta.setDisplayName(Skills.values()[pos].convert());
                        meta.setLore(
                                Arrays.asList(parseSkill(skills.getSkill(Skills.values()[pos])))
                        );
                        item.setItemMeta(meta);
                        return item;
                    });

        }

        menu.open(p);
    }

    private static String parseSkill(double skill) {
        int lvl = (int) Math.floor(skill);
        int exp = (int) ((skill - lvl) * 1000);
        return lvl + " уровень, " + exp + " опыта";
    }
}

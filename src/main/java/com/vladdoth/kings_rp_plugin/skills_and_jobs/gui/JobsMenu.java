package com.vladdoth.kings_rp_plugin.skills_and_jobs.gui;

import com.vladdoth.kings_rp_plugin.Plugin;
import com.vladdoth.kings_rp_plugin.UserData;
import com.vladdoth.kings_rp_plugin.configs.Values;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.Jobs;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.data.JobData;
import com.vladdoth.kings_rp_plugin.skills_and_jobs.data.SkillsData;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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

import java.util.Date;

public class JobsMenu implements Listener {
    public static final String quit_item = ChatColor.DARK_PURPLE + "Уволиться",
            employMsg = ChatColor.YELLOW + "Вы успешно устроились на работу: ",
            quitMsg = ChatColor.RED + "Вы уволились",
            employWarn = ChatColor.RED + "Вы уже на этой работе",
            quitWarn = ChatColor.RED + "Вы и так не работаете",
            timeWarn = ChatColor.YELLOW + "Вы не можете так часто менять работу, подождите еще: ",
            infoMsg = ChatColor.YELLOW + "Ваша текущая работа: ",
            employInfo = ChatColor.YELLOW + "Устроиться на работу: ";


    private static ItemStack
            none = new ItemStack(Material.BARRIER),
            miner = new ItemStack(Material.IRON_PICKAXE),
            farmer = new ItemStack(Material.IRON_HOE),
            lumber = new ItemStack(Material.IRON_AXE),
            hunter = new ItemStack(Material.IRON_SWORD),
            breeder = new ItemStack(Material.SHEARS),
            fisher = new ItemStack(Material.FISHING_ROD);

    private static ItemStack[] options = {none, miner, farmer, lumber, hunter, breeder, fisher};

    public static void openMenu(Player p) {
        Menu menu = ChestMenu.builder(3)
                .title(Values.JOB_MENU_NAME)
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
                .pattern("100000001")
                .item(new ItemStack(web))
                .pattern("111111111")
                .item(new ItemStack(border))
                .pattern("111101111")
                .build();

        mask.apply(menu);

        JobData jobData = Plugin.getInstance().getUsers().get(p.getName()).getJobData();

        int shift = 1;
        for (int i = 0; i < options.length; i++) {
            Slot slot = menu.getSlot(i + shift);
            slot.setClickOptions(ClickOptions.builder()
                    .allow(ClickType.LEFT)
                    .build());

            final int pos = i;
            slot.setItemTemplate(
                    (player) -> {
                        ItemStack item = new ItemStack(options[pos]);
                        ItemMeta meta = item.getItemMeta();
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                        if (pos == Jobs.NONE.ordinal())
                            meta.setDisplayName(quit_item);

                        else
                            meta.setDisplayName(employInfo + Jobs.values()[pos].convert());

                        item.setItemMeta(meta);
                        return item;
                    });

            slot.setClickHandler(
                    (player, info) -> {
                        if (pos == jobData.getJob().ordinal())
                            if (pos == 0)
                                player.sendMessage(quitWarn);
                            else
                                player.sendMessage(employWarn);

                        else {
                            if (p.getGameMode() != GameMode.CREATIVE) {
                                UserData user = Plugin.getInstance().getUsers().get(p.getName());
                                long delta = new Date().getTime() - user.getJobData().getLastJobChange();
                                if (delta < Values.TIME_TO_CHANGE_JOB) {
                                    p.sendMessage(timeWarn + (int) ((Values.TIME_TO_CHANGE_JOB - delta) / 60_000) + " минут(у/ы)");
                                    return;
                                }
                            }

                            Jobs job = Jobs.values()[pos];
                            jobData.setJob(job);

                            if (pos == 0)
                                player.sendMessage(quitMsg);
                            else
                                player.sendMessage(employMsg + job.convert());

                            player.closeInventory();
                        }
                    });
        }

        int infoSlot = 22;
        Slot info = menu.getSlot(infoSlot);
        info.setItemTemplate(
                player -> {
                    UserData data = Plugin.getInstance().getUsers().get(p.getName());
                    ItemStack job = new ItemStack(options[data.getJobData().getJob().ordinal()]);
                    ItemMeta meta = job.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    meta.setDisplayName(infoMsg + data.getJobData().getJob().convert());
                    job.setItemMeta(meta);
                    return job;
                });

        menu.open(p);
    }
}

package net.kohy.commands.reports;

import net.kohy.utils.BasicMenu;
import net.kohy.utils.manager.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ReportAction {

    private Player player, cheater;

    public ReportAction(Player player, Player cheater) {
        this.player = player;
        this.cheater = cheater;
    }

    public void triggerReport(){

        GUI gui = GUI.Builder
                .create(9, "§6Report of §c" + cheater.getName())
                .players(player)
                .addOption(0, Material.DIAMOND_AXE, "§1KillAura/Forcefield/FightBot", (player, event) -> {
                    ReportSender killauraSender = new ReportSender();
                    killauraSender.sendReportToStaff("KillAura/Forcefield", cheater, player);
                    player.closeInventory();
                    player.sendMessage("§aThank you for your report !");
                })
                .addOption(2, Material.WEB, "§1AntiknockBack", (player, event) -> {
                    ReportSender antikbSender = new ReportSender();
                    antikbSender.sendReportToStaff("Antiknockback", cheater, player);
                    player.closeInventory();
                    player.sendMessage("§aThank you for your report !");
                }).addOption(4, Material.FEATHER, "§1Fly/Speedhack", (player, event) -> {
                    ReportSender flySender = new ReportSender();
                    flySender.sendReportToStaff("Fly/Speedhack", cheater, player);
                    player.closeInventory();
                    player.sendMessage("§aThank you for your report !");
                })
                .addOption(6, Material.POTION, "§1Chat", (player, event) -> {
                    ReportSender speedSender = new ReportSender();
                    speedSender.sendReportToStaff("Chat", cheater, player);
                    player.closeInventory();
                    player.sendMessage("§aThank you for your report !");
                })
                .addOption(8, Material.ANVIL, "§1Other", (player, event) -> {
                    ReportSender otherSender = new ReportSender();
                    otherSender.sendReportToStaff("Other", cheater, player);
                    player.closeInventory();
                    player.sendMessage("§aThank you for your report !");
                })
                .lockAll()
                .build();
        gui.open();




    }




}

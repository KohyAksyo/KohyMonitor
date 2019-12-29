package net.kohy.commands.reports;

import net.kohy.utils.moderator.Moderator;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;



public class ReportSender {


    public static void sendReportToStaff(String reportMessage, Player cheater, Player reportSender){

        for(Moderator staff : Moderator.getModerators()){

            net.md_5.bungee.api.chat.TextComponent report = new TextComponent("§3[Monitor] §c" + cheater.getName() + " §6has been reported by §a" + reportSender.getName()
            + "§6 for §c" + reportMessage);
            report.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fClick to teleport to the reported player").create()));
            report.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + cheater.getName()));
            staff.getPlayer().spigot().sendMessage(report);

        }


    }

}

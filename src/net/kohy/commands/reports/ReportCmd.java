package net.kohy.commands.reports;

import net.kohy.Monitor;
import net.kohy.utils.BasicMenu;
import net.kohy.utils.MonitorPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if(s instanceof Player){
            Player player = (Player) s;
            MonitorPlayer target = MonitorPlayer.getMonitorProfile(player);
            if(target.isAllowedReports()){
                if(args.length == 1){
                    if(Bukkit.getPlayer(args[0].toString()).isOnline()){
                        Player cheater = Bukkit.getPlayer(args[0]);
                        ReportAction reportAction = new ReportAction(player, cheater);
                        reportAction.triggerReport();

                        MonitorPlayer reportedPlayer = MonitorPlayer.getMonitorProfile(cheater);
                        reportedPlayer.addReportedTime();
                    }else {
                        player.sendMessage("§cThis player doesn't exists or isn't online");
                    }

                }else {
                    player.sendMessage("§c/report <player>");
                }

            }else {
                player.sendMessage("§cYou have been banned from reports ! You can appeal this decision on our forum.");
            }


        }else {
            s.sendMessage("You have to be a player to execute this command");
        }

        return false;
    }
}

package net.kohy.commands.reports;

import net.kohy.utils.MonitorPlayer;
import net.kohy.utils.moderator.Moderator;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportClear implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if(s instanceof Player){
            Player player = (Player) s;
            Moderator moderator = Moderator.getModeratorProfile(player);
            if(moderator.isModMode()){
                if(args.length == 1){
                    if(Bukkit.getPlayer(args[0]) != null){
                        Player clearedPlayer = Bukkit.getPlayer(args[0]);
                        MonitorPlayer monitorPlayer = MonitorPlayer.getMonitorProfile(clearedPlayer);
                        monitorPlayer.clearReportedTimes();
                        moderator.getPlayer().sendMessage("§3[Monitor] §aYou cleared §e" + clearedPlayer.getName() + "§a from all reports");
                        clearedPlayer.sendMessage("§eYou have been §acleared §efrom §aall §eyour reports");
                    }else {
                        player.sendMessage("§3[Monitor] §cThis player doesn't exists or isn't online");
                    }
                }else {
                    player.sendMessage("§c/rclear <player>");
                }
            }else {
                player.sendMessage("§3[Monitor] §cYou have to be in Moderator mode to use this command");
            }

        }else {
            s.sendMessage("You have to be a player to execute this command");
        }

        return false;
    }
}

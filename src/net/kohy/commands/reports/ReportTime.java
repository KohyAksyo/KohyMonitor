package net.kohy.commands.reports;

import net.kohy.utils.MonitorPlayer;
import net.kohy.utils.moderator.Moderator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if(s instanceof Player){
            Player player = (Player) s;
            Moderator moderator = Moderator.getModeratorProfile(player);
            if(moderator.isModMode()){
                if(Bukkit.getPlayer(args[0]) != null){
                    Player target = Bukkit.getPlayer(args[0]);
                    MonitorPlayer monitoredTarget = MonitorPlayer.getMonitorProfile(target);
                    player.sendMessage("§3[Monitor] §e" + target.getName() + "§b has been reported §e" + monitoredTarget.getReported() + "§b times");

                }else {
                    player.sendMessage("§3[Monitor] §cThis player doesn't exists or isn't online");
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

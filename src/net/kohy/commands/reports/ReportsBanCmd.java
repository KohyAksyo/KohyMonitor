package net.kohy.commands.reports;

import net.kohy.utils.MonitorPlayer;
import net.kohy.utils.moderator.Moderator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportsBanCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if(s instanceof Player){
            Player player = (Player) s;
            Moderator target =  Moderator.getModeratorProfile(player);
            if(target.isModMode()){

                if(args.length == 1){
                    if(Bukkit.getPlayer(args[0]) != null){
                        Player banned = Bukkit.getPlayer(args[0]);
                        MonitorPlayer bannedPlayer = MonitorPlayer.getMonitorProfile(banned);
                        bannedPlayer.banReport();
                        player.sendMessage("§c[Monitor] §aYou banned §e" + banned.getName() + "§a from using reports");
                        banned.sendMessage("§cYou are now banned from the report system. You can appeal this decision or our forum.");
                    }else {
                        player.sendMessage("§3[Monitor] §cThis player doesn't exists or isn't online");
                    }

                }else {
                    player.sendMessage("§c/reportban <player>");
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

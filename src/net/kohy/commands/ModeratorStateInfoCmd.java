package net.kohy.commands;

import net.kohy.utils.moderator.GlobalState;
import net.kohy.utils.moderator.Moderator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModeratorStateInfoCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if(s instanceof Player){
            Player player = (Player) s;
            Moderator moderator = Moderator.getModeratorProfile(player);
            if(moderator.isModMode()){
                if(args.length == 0){
                    GlobalState.getModeratorState(moderator, player);
                }else {
                    player.sendMessage("§3[Monitor] §c/myinfo");
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

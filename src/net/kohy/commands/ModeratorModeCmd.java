package net.kohy.commands;

import net.kohy.utils.moderator.BreakBlockState;
import net.kohy.utils.moderator.Moderator;
import net.kohy.utils.moderator.ModeratorItems;
import net.kohy.utils.moderator.VanishState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class ModeratorModeCmd implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if(s instanceof Player){
            Player player = (Player) s;
            if(args.length == 0){
                Moderator target = Moderator.getModeratorProfile(player);
                if(target.isModMode()){

                    player.getInventory().clear();
                    target.setReceiveReports(false);
                    VanishState.unVanishModerator(player, false);
                    BreakBlockState.switchOnBreak(target, true);
                    target.setModMode(false);
                    player.sendMessage("§3[Monitor] §eYou are no longer in §cModerator mode");

                }else if(!target.isModMode()) {


                    player.getInventory().clear();
                    target.setReceiveReports(true);
                    target.setModMode(true);
                    VanishState.vanishModerator(player, false);
                    BreakBlockState.switchOffBreak(target, true);
                    player.getInventory().setItem(0, ModeratorItems.KNOCKBACK.getItems());
                    player.getInventory().setItem(1, ModeratorItems.VANISH.getItems());
                    player.getInventory().setItem(8, ModeratorItems.BLOCKBREAKEVENT.getItems());
                    player.sendMessage("§3[Monitor] §eYou are now in §aModerator mode");
                }

            }else {
                player.sendMessage("§3[Monitor] §c/km");
            }

        }else {
            s.sendMessage("You have to be a player to execute this command");
        }

        return false;
    }
}

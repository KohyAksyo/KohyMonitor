package net.kohy.utils.moderator;

import org.bukkit.entity.Player;

import java.awt.*;

public class GlobalState {


    public static final void getModeratorState(Moderator moderator, Player player){

        String vanish = "";
        String block = "";

        if(VanishState.isVanished(player)){
            vanish = "§aYou are vanished";
        }else if(!VanishState.isVanished(player)){
            vanish = "§cYou are not vanished";
        }
        if(BreakBlockState.canBreak(moderator)){
            block = "§aYou can break blocks";
        }if(!BreakBlockState.canBreak(moderator)){
            block = "§cYou cant break blocks";
        }

        player.sendMessage("§3[Monitor] §e[STATE§e] " + vanish);
        player.sendMessage("§3[Monitor] §e[STATE§e] " + block);

    }


}

package net.kohy.utils.moderator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BreakBlockState {

    //TRUE == Can break blocks

    private static HashMap<Moderator, Boolean> breakState = new HashMap<Moderator, Boolean>();

    public static void switchOnBreak(Moderator moderator, boolean sendSwitchMessage){

        breakState.put(moderator, true);

        if(sendSwitchMessage){
            moderator.getPlayer().sendMessage("§3[Monitor] §aYou can break blocks");
        }

    }

    public static void switchOffBreak(Moderator moderator, boolean sendSwitchMessage){

        breakState.put(moderator, false);

        if(sendSwitchMessage){
            moderator.getPlayer().sendMessage("§3[Monitor] §cYou cant break blocks");
        }
    }

    public static final Boolean canBreak(Moderator moderator){
        return breakState.get(moderator);
    }

}

package net.kohy.utils.moderator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class VanishState {

    private static HashMap<Player, Boolean> vanishState = new HashMap<Player, Boolean>();

    public static void vanishModerator(Player player, Boolean sendVanishMessage){

        vanishState.put(player, true);
        for(Player p : Bukkit.getOnlinePlayers()){
            p.hidePlayer(player);

        }
        if(sendVanishMessage){
            player.sendMessage("§3[Monitor] §aYou are now in vanish mode");
        }

    }

    public static void unVanishModerator(Player player, Boolean sendVanishMessage){

        vanishState.put(player, false);
        for(Player p : Bukkit.getOnlinePlayers()){
            p.showPlayer(player);
        }
        if(sendVanishMessage) {
            player.sendMessage("§3[Monitor] §cYou are now unvanished");
        }
    }

    public static Boolean isVanished(Player player){
        return vanishState.get(player);
    }
}

package net.kohy.utils.moderator;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Moderator{

    private Player player;

    private static Map<Player, Moderator> moderators = new HashMap<>();

    private int banCount = 0;
    private boolean modMode = false;
    private boolean reportReceiver = false;
    private Inventory moderatorIventory;

    public int getBans() {
        return banCount;
    }

    public boolean isModMode() {
        return modMode;
    }

    public boolean isReportReceiver() {
        return reportReceiver;
    }

    public static Moderator getModeratorProfile(Player player) {
        if(!moderators.containsKey(player)) {
            moderators.put(player, new Moderator(player));
        }
        return moderators.get(player);
    }

    private Moderator() {

    }
    private Moderator(Player player) {
        this.player = player;
    }

    public void setModMode(boolean mode){
        modMode = mode;
    }

    public void setBans(int ban) {
        banCount = ban;
    }

    public void addBanAttribute() {
        banCount++;
    }


    public Boolean isReceivingReports() {
        return reportReceiver;
    }

    public void setReceiveReports(boolean reportReceiver) {
        this.reportReceiver = reportReceiver;
    }


    public static Moderator[] getModerators() {
        List<Moderator> list = new ArrayList<>();
        for(Map.Entry<Player, Moderator> entry : moderators.entrySet()) {
            list.add(entry.getValue());
        }
        Moderator[] array = new Moderator[list.size()];
        return list.toArray(array);
    }


    public Player getPlayer() {
        return player;
    }
}

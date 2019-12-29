package net.kohy.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MonitorPlayer {

    private static Map<Player, MonitorPlayer> monitored = new HashMap<>();

    private Player player;
    private boolean allowReports = true;
    private int reported = 0;

    private MonitorPlayer(){

    }

    private MonitorPlayer(Player player) {
        this.player = player;
    }


    public boolean isAllowedReports(){
        return allowReports;
    }

    public void unbanReport(){
        allowReports = true;
    }

    public void banReport(){
        allowReports = false;
    }

    public void addReportedTime(){
        reported++;
    }

    public void clearReportedTimes(){
        reported = 0;
    }

    public int getReported() {
        return reported;
    }

    public static MonitorPlayer getMonitorProfile(Player player) {
        if(!monitored.containsKey(player)) {
            monitored.put(player, new MonitorPlayer(player));
        }
        return monitored.get(player);
    }






}

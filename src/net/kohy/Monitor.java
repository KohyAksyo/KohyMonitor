package net.kohy;


import net.kohy.commands.ModeratorModeCmd;
import net.kohy.commands.ModeratorPunishCmd;
import net.kohy.commands.ModeratorStateInfoCmd;
import net.kohy.commands.reports.*;
import net.kohy.events.ModeratorGlobalEvents;
import net.kohy.utils.MonitorPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Monitor extends JavaPlugin {

    private static Monitor PLUGIN;

    Server server = Bukkit.getServer();
    PluginManager pm = server.getPluginManager();

    @Override
    public void onEnable() {
        PLUGIN = this;
        loadCmd();
        loadEvents();

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisable() {

    }

    public void loadCmd(){

        getCommand("mod").setExecutor(new ModeratorModeCmd());
        getCommand("report").setExecutor(new ReportCmd());
        getCommand("reportban").setExecutor(new ReportsBanCmd());
        getCommand("reportunban").setExecutor(new ReportUnbanCmd());
        getCommand("ks").setExecutor(new ModeratorPunishCmd());
        getCommand("myinfo").setExecutor(new ModeratorStateInfoCmd());
        getCommand("reportclear").setExecutor(new ReportClear());
        getCommand("reportinfo").setExecutor(new ReportTime());

    }

    public void loadEvents(){

        pm.registerEvents(new ModeratorGlobalEvents(), this);
    }

    public static Monitor getPlugin() {
        return PLUGIN;
    }



}

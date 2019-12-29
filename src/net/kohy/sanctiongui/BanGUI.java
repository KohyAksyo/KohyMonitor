package net.kohy.sanctiongui;

import net.kohy.api.KohyAPI;
import net.kohy.api.player.KohyPlayer;
import net.kohy.utils.MonitorPlayer;
import net.kohy.utils.manager.GUI;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BanGUI {

    public static final void openBanGUI(Player target, Player cheater){

        GUI.Builder mute = GUI.Builder.create(9, "§cBan manager for §6" + cheater.getName())
                .players(target);
        KohyPlayer kohycheater = KohyAPI.getInstance().getPlayer(cheater.getUniqueId().toString());
        MonitorPlayer monitorcheater = MonitorPlayer.getMonitorProfile(cheater);

        mute.addHeadedOption(0, cheater.getName(), "§c" + cheater.getName(), Arrays.asList("", kohycheater.getRank(),
                "§bReported times : §e" + monitorcheater.getReported()), (player, event) ->{

        });

    }

}

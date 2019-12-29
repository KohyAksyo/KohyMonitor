package net.kohy.utils.manager;

import net.kohy.api.KohyAPI;
import net.kohy.api.player.KohyPlayer;
import net.kohy.sanctiongui.BanGUI;
import net.kohy.sanctiongui.MuteGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ManagerGUI {

    public void triggerGUIManager(Player player, Player cheater){

        KohyPlayer staff = KohyAPI.getInstance().getPlayer(player.getUniqueId().toString());

        if (staff.getRank().toLowerCase() == "trial-mod" || staff.getRank().toLowerCase() == "builder") {
            Staff builder = Staff.getStaffProfile(player);
            builder.isBuilder();
        }else if(staff.getRank().toLowerCase() == "moderator"){
            Staff mod = Staff.getStaffProfile(player);
            mod.isModerator();
        }else if(staff.getRank().toLowerCase() == "seniormoderator"){
            Staff snrMod = Staff.getStaffProfile(player);
            snrMod.isSeniorModerator();
        }

        GUI guimanager = GUI.Builder.create(27, "§6Manager")
                .addOption(12, Material.BIRCH_FENCE, "§bMute", (player1, event) -> {
                    MuteGUI.openMuteGUI(player, cheater);

        }).addOption(14, Material.BARRIER, "§bBan", (player1, event) -> {
            Staff staffmanager = Staff.getStaffProfile(player);
            if(staffmanager.getBanAccess()){
                BanGUI.openBanGUI(player, cheater);
            }else {
                player.sendMessage("§3[Monitor] §cYou cannot access this section");
            }

                })
                .build();

    }


}

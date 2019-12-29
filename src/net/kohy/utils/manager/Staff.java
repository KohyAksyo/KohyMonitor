package net.kohy.utils.manager;

import net.kohy.utils.moderator.Moderator;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Staff {

    private Player player;

    private static Map<Player, Staff> staff = new HashMap<>();

    private boolean canMute = false;
    private boolean canBan = false;
    private boolean canSuperiorBan = false;

    private Staff(){

    }

    private Staff(Player player){
        this.player = player;
    }

    public static Staff[] getStaff() {
        List<Staff> list = new ArrayList<>();
        for(Map.Entry<Player, Staff> entry : staff.entrySet()) {
            list.add(entry.getValue());
        }
        Staff[] array = new Staff[list.size()];
        return list.toArray(array);
    }

    public static Staff getStaffProfile(Player player) {
        if(!staff.containsKey(player)) {
            staff.put(player, new Staff(player));
        }
        return staff.get(player);
    }

    public void setMuteAccess(boolean setaccess){
        this.canMute = setaccess;
    }

    public void setBanAccess(boolean setaccess) {
        this.canBan = setaccess;
    }

    public void setSuperiorBanAccess(boolean setaccess) {
        this.canSuperiorBan = setaccess;
    }

    public boolean getMuteAccess(){
        return canMute;
    }

    public boolean getBanAccess(){
        return canBan;
    }

    public boolean getSuperiorBanAccess(){
        return canMute;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void isBuilder(){
        canMute = true;
        canBan = false;
        canSuperiorBan = false;
    }

    public void isModerator(){
        canMute = true;
        canBan = true;
        canSuperiorBan = false;
    }

    public void isSeniorModerator(){
        canMute = true;
        canBan = true;
        canSuperiorBan = true;
    }
}

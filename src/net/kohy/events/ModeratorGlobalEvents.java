package net.kohy.events;

import net.kohy.utils.moderator.BreakBlockState;
import net.kohy.utils.moderator.Moderator;
import net.kohy.utils.moderator.ModeratorItems;
import net.kohy.utils.moderator.VanishState;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import javax.jws.WebParam;

public class ModeratorGlobalEvents implements Listener {

    @EventHandler
    public void moderatorPickupItemsEvent(PlayerPickupItemEvent event){

        Player player = event.getPlayer();
        Moderator moderator = Moderator.getModeratorProfile(player);
        if(moderator.isModMode()){
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }

    }

    @EventHandler
    public void moderatorBreakingBlockEvent(BlockBreakEvent event){

        Player player = event.getPlayer();
        Moderator moderator = Moderator.getModeratorProfile(player);
        if(moderator.isModMode()){
            if(BreakBlockState.canBreak(moderator)){
                event.setCancelled(false);
            }else {
                event.setCancelled(true);
            }
        }else {
            event.setCancelled(false);
        }

    }

    @EventHandler
    public void moderatorPlacingBlockEvent(BlockPlaceEvent event){

        Player player = event.getPlayer();
        Moderator moderator = Moderator.getModeratorProfile(player);

        if(moderator.isModMode()){
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }


    }

    @EventHandler
    public void moderatorUseModItemsEvent(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack current = event.getItem();
        Moderator moderator = Moderator.getModeratorProfile(player);

        if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
            if(current.getType() == ModeratorItems.VANISH.getMaterial()){
                if(current.getItemMeta().getDisplayName() == ModeratorItems.VANISH.getItems().getItemMeta().getDisplayName()){
                    if(moderator.isModMode()){
                        if(VanishState.isVanished(player)){
                            VanishState.unVanishModerator(player, true);
                        }else {
                            VanishState.vanishModerator(player, true);
                        }
                    }
                }

            }
            else if(current.getType() == ModeratorItems.BLOCKBREAKEVENT.getMaterial()){
                if(current.getItemMeta().getDisplayName() == ModeratorItems.BLOCKBREAKEVENT.getItems().getItemMeta().getDisplayName()){
                    if(moderator.isModMode()){
                        if(BreakBlockState.canBreak(moderator)){
                            BreakBlockState.switchOffBreak(moderator, true);
                        }else {
                            BreakBlockState.switchOnBreak(moderator, true);
                        }
                    }
                }

            }

        }

    }

}

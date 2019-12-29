package net.kohy.utils.manager;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import net.kohy.Monitor;
import net.kohy.utils.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


public class GUI implements Listener {

    private HashMap<Integer, BiConsumer<Player, InventoryClickEvent>> actions;
    private HashMap<Integer, Boolean> lockedSlots;

    private Inventory inventory;
    private Player[] players;

    private boolean isOpen = false;
    private boolean isLocked = false;

    private BiConsumer<Player, InventoryCloseEvent> action;

    public GUI(Player[] players, int size, String name) {
        this.players = players;
        actions = new HashMap<Integer, BiConsumer<Player, InventoryClickEvent>>();
        lockedSlots = new HashMap<Integer, Boolean>();
        inventory = Bukkit.createInventory(null, size, name);

        Monitor.getPlugin().getServer().getPluginManager().registerEvents(this, Monitor.getPlugin());

    }

    public boolean isOpen() {
        return isOpen;
    }

    public Player getFirstPlayer() {
        return players[0];
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player... players) {
        this.players = players;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ItemStack addOption(int slot, Material mat, String name, BiConsumer<Player, InventoryClickEvent> action) {
        ItemStack items = new ItemStack(mat);

        setStackName(items, name);

        inventory.setItem(slot, items);

        actions.put(slot, action);

        return items;
    }

    public ItemStack addOption(int slot, ItemStack items, String name, BiConsumer<Player, InventoryClickEvent> action) {
        setStackName(items, name);

        inventory.setItem(slot, items);

        actions.put(slot, action);

        return items;
    }

    public ItemStack addOption(int slot, ItemStack items, String name, List<String> lore, BiConsumer<Player, InventoryClickEvent> action) {
        setStackName(items, name);
        setStackLore(items, lore);

        inventory.setItem(slot, items);

        actions.put(slot, action);

        return items;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getPlayerHead(String name) {
        ItemStack items = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) items.getItemMeta();
        meta.setOwner(name);
        items.setItemMeta(meta);
        return items;
    }

    public ItemStack addHeadedOption(int slot, String ply, String name, BiConsumer<Player, InventoryClickEvent> action) {
        ItemStack items = getPlayerHead(ply);

        setStackName(items, name);

        inventory.setItem(slot, items);

        actions.put(slot, action);

        return items;
    }

    public ItemStack addHeadedOption(int slot, String ply, String name, List<String> lore, BiConsumer<Player, InventoryClickEvent> action) {
        ItemStack items = getPlayerHead(ply);

        setStackName(items, name);
        setStackLore(items, lore);

        inventory.setItem(slot, items);

        actions.put(slot, action);

        return items;
    }

    public boolean setStackName(ItemStack items, String name) {
        ItemMeta meta = items.getItemMeta();

        if(meta instanceof SkullMeta) {

            SkullMeta skullMeta = (SkullMeta) meta;

            if (skullMeta != null) {
                skullMeta.setDisplayName(name);
                items.setItemMeta(skullMeta);
                return true;
            }

        } else {

            if (meta != null) {
                meta.setDisplayName(name);
                items.setItemMeta(meta);
                return true;
            }

        }
        return false;
    }

    public boolean setStackLore(ItemStack items, List<String> lore) {
        ItemMeta meta = items.getItemMeta();

        if(meta instanceof SkullMeta) {

            SkullMeta skullMeta = (SkullMeta) meta;

            if (skullMeta != null) {
                skullMeta.setLore(lore);
                items.setItemMeta(skullMeta);
                return true;
            }

        } else {

            if (meta != null) {
                meta.setLore(lore);
                items.setItemMeta(meta);
                return true;
            }

        }
        return false;
    }

	/*public void SetTitle(String title) {
		EntityPlayer entPly = ((CraftPlayer) player).getHandle();
		PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(entPly.activeContainer.windowId, "minecraft:chest", new ChatMessage(title), inventory.getSize());
		entPly.playerConnection.sendPacket(packet);
		entPly.updateInventory(entPly.activeContainer);
	}*/

    public void setCloseAction(BiConsumer<Player, InventoryCloseEvent> action) {
        this.action = action;
    }

    public void clear() {
        lockedSlots.clear();
        inventory.clear();
        actions.clear();
    }

    public void open() {
        isOpen = true;
        ArrayUtils.executeArray(players, player -> player.openInventory(inventory));
    }

    public void close() {
        ArrayUtils.executeArray(players, player -> player.closeInventory());
    }

    public void reopen() {
        close();
        open();
    }

    public void onClose(InventoryCloseEvent event) {
        valueRefresh(event);
    }

    public void valueRefresh(InventoryCloseEvent event) {
        if (action != null) action.accept((Player) event.getPlayer(), event);
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setSlotLocked(int slot, boolean locked) {
        lockedSlots.put(slot, locked);
    }

    public boolean isSlotLocked(int slot) {
        if (isLocked) return true;
        if (lockedSlots.containsKey(slot) && lockedSlots.get(slot)) return true;
        return false;
    }

    private boolean onClick(int slot, ItemStack item, InventoryAction action, boolean cancelled, InventoryClickEvent event) {
        if (actions.containsKey(slot)) {
            BiConsumer<Player, InventoryClickEvent> act = actions.get(slot);
            if (act != null) act.accept((Player) event.getWhoClicked(), event);
        }

        return cancelled;
    }

    private void onTakeItem(int slot, ItemStack item, InventoryClickEvent event) { }
    private boolean canTakeItem(int slot, ItemStack item, InventoryClickEvent event) {
        if (isSlotLocked(slot)) return false;
        return true;
    }

    private void onInsertItem(int slot, ItemStack item, InventoryClickEvent event) { }
    private boolean canInsertItem(int slot, ItemStack item, InventoryClickEvent event) {
        if (isSlotLocked(slot)) return false;
        return true;
    }

    private void onDropItem(int slot, ItemStack item, InventoryClickEvent event) { }
    private boolean canDropItem(int slot, ItemStack item, InventoryClickEvent event) {
        if (isSlotLocked(slot)) return false;
        return true;
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        if (!ArrayUtils.contains(ArrayUtils.castArray(players, event.getWhoClicked()), event.getWhoClicked())) return;
        if (!event.getInventory().equals(inventory)) return;

        int slot = event.getSlot();

        if (event.getRawSlot() != slot) return;

        boolean canceled = event.isCancelled();

        InventoryAction act = event.getAction();

        ItemStack item = event.getCurrentItem();

        if (act == InventoryAction.PLACE_ONE || act == InventoryAction.PLACE_SOME || act == InventoryAction.PLACE_ALL || act == InventoryAction.COLLECT_TO_CURSOR) item = event.getCursor();

        if (item == null || item.getType() == Material.AIR) return;

        if (act == InventoryAction.PICKUP_ONE || act == InventoryAction.PICKUP_SOME || act == InventoryAction.PICKUP_HALF || act == InventoryAction.PICKUP_ALL || act == InventoryAction.COLLECT_TO_CURSOR) {
            if (canTakeItem(slot, item, event)) { onTakeItem(slot, item, event); } else { canceled = true; }
        } else if (act == InventoryAction.PLACE_ONE || act == InventoryAction.PLACE_SOME || act == InventoryAction.PLACE_ALL) {
            if (canInsertItem(slot, item, event)) { onInsertItem(slot, item, event); } else { canceled = true; }
        } else if (act == InventoryAction.DROP_ALL_SLOT || act == InventoryAction.DROP_ONE_SLOT ) {
            if (canDropItem(slot, item, event)) { onDropItem(slot, item, event); } else { canceled = true; }
        } else if (act == InventoryAction.SWAP_WITH_CURSOR ) {
            ItemStack held = event.getWhoClicked().getItemOnCursor();
            if (canTakeItem(slot, item, event) && canInsertItem(slot, held, event)) {
                onTakeItem(slot, item, event);
                onInsertItem(slot, held, event);
            } else { canceled = true; }
        } else { canceled = true; }

        canceled = onClick(slot, item, act, canceled, event);

        event.setCancelled(canceled);
    }


    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event) {
        if(!ArrayUtils.contains(players, event.getPlayer())) return;
        if (!event.getInventory().equals(inventory)) return;

        isOpen = false;

        onClose(event);
    }

    public static final class Builder {

        private GUI gui;
        private boolean done = false;

        private Builder(Player[] players, int size, String name) {
            gui = new GUI(players, size, name);
        }

        private Builder(int size, String name) {
            gui = new GUI(null, size, name);
        }

        public static final Builder create(Player[] players, int size, String name) {
            return new Builder(players, size, name);
        }

        public static final Builder create(int size, String name) {
            return new Builder(size, name);
        }

        public Builder addOption(int slot, Material mat, String name, BiConsumer<Player, InventoryClickEvent> action) {
            gui.addOption(slot, mat, name, action);
            return this;
        }

        public ItemStack addOption(int slot, ItemStack items, String name, BiConsumer<Player, InventoryClickEvent> action) {
            gui.addOption(slot, items, name, action);
            return items;
        }

        public Builder addOption(int slot, ItemStack items, String name, List<String> lore, BiConsumer<Player, InventoryClickEvent> action) {
            gui.addOption(slot, items, name, lore, action);
            return this;
        }

        public Builder addHeadedOption(int slot, String player, String name, BiConsumer<Player, InventoryClickEvent> action) {
            gui.addHeadedOption(slot, player, name, action);
            return this;
        }

        public Builder addHeadedOption(int slot, String player, String name, List<String> lore, BiConsumer<Player, InventoryClickEvent> action) {
            gui.addHeadedOption(slot, player, name, lore, action);
            return this;
        }

        public Builder lock(int slot) {
            gui.setSlotLocked(slot, true);
            return this;
        }

        public Builder lockAll() {
            gui.setLocked(true);
            return this;
        }

        public Builder players(Player... players) {
            gui.setPlayers(players);
            return this;
        }

        public Builder onClose(BiConsumer<Player, InventoryCloseEvent> action) {
            gui.setCloseAction(action);
            return this;
        }

        public GUI build() {
            if(gui.players == null || gui.players.length == 0) throw new IllegalStateException("Player array cannot be null or empty !");
            for(Player player : gui.players) if(player == null) throw new IllegalStateException("Player array cannot contain null !");
            if(done) throw new IllegalStateException("Cannot build a GUIBuilder multiple times !");
            done = true;
            return gui;
        }

    }

}

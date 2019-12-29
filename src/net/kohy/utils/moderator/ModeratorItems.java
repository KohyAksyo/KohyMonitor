package net.kohy.utils.moderator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public enum ModeratorItems {

    KNOCKBACK(new ItemStack(Material.FEATHER), "§5Knockback Check", Arrays.asList("§dCheck a player knockback by hitting them with this feather"), Enchantment.KNOCKBACK, 3),
    VANISH(new ItemStack(Material.WATCH), "§5Vanish", Arrays.asList("§dSet your vanish state"), Enchantment.LUCK, 10),
    BLOCKBREAKEVENT(new ItemStack(Material.DIAMOND_ORE), "§5Break Blocks", Arrays.asList("§dClick to break or to stop breaking blocks", "§d/myinfo to see your current state"), Enchantment.DIG_SPEED, 10);

    ItemStack items;
    String name;
    List<String> lore;
    Enchantment enchantment;
    Integer epower;

    ModeratorItems(org.bukkit.inventory.ItemStack items, String name, List<String> lore, Enchantment enchantment, Integer epower){
        this.items = items;
        this.name = name;
        this.lore = lore;
        this.enchantment = enchantment;
        this.epower = epower;
    }

    public ItemStack getItems(){

        ItemMeta itemMeta = items.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.addEnchant(enchantment, epower, true);
        items.setItemMeta(itemMeta);

        return items;
    }

    public Material getMaterial(){
        return items.getType();
    }
}

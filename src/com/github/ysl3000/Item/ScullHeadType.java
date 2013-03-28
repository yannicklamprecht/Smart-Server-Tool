package com.github.ysl3000.Item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ScullHeadType {
	public static ItemStack Skull(String skullOwner) {
		ItemStack skullHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta skullHeadMeta = (SkullMeta) skullHead.getItemMeta();
		skullHeadMeta.setOwner(skullOwner);
		skullHead.setItemMeta(skullHeadMeta);
		return skullHead;
	}
}

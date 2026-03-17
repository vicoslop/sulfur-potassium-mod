package com.branders.sulfurpotassiummod.registry;

import com.branders.sulfurpotassiummod.SulfurPotassiumMod;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

/**
 * 	Mod items registry and reference
 * 
 * 	@author Anders <Branders> Blomqvist
 */
public class ModItems {

	private static final ResourceKey<CreativeModeTab> INGREDIENTS_TAB = tabKey("ingredients");

	public static Item
		POTASSIUM,
		RAW_POTASSIUM,
		SULFUR;
	
	public static void register() {
		registerItem(itemKey("potassium"), POTASSIUM = new BoneMealItem(new Item.Properties().setId(itemKey("potassium"))), INGREDIENTS_TAB);
		registerItem(itemKey("raw_potassium"), RAW_POTASSIUM = new Item(new Item.Properties().setId(itemKey("raw_potassium"))), INGREDIENTS_TAB);
		registerItem(itemKey("sulfur"), SULFUR = new Item(new Item.Properties().setId(itemKey("sulfur"))), INGREDIENTS_TAB);
	}
	
	public static void registerItem(ResourceKey<Item> key, Item item, ResourceKey<CreativeModeTab> group) {
		Registry.register(BuiltInRegistries.ITEM, key, item);
		CreativeModeTabEvents.modifyOutputEvent(group).register(entries -> entries.accept(item));
	}

	private static ResourceKey<Item> itemKey(String path) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, path));
	}

	private static ResourceKey<CreativeModeTab> tabKey(String path) {
		return ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.withDefaultNamespace(path));
	}
}

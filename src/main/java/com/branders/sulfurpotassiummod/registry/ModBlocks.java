package com.branders.sulfurpotassiummod.registry;

import com.branders.sulfurpotassiummod.SulfurPotassiumMod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * 	Mod Block registry and references
 * 
 * 	@author Anders <Branders> Blomqvist
 */
public class ModBlocks {

	private static final ResourceKey<CreativeModeTab> NATURAL_BLOCKS_TAB = tabKey("natural_blocks");
	private static final ResourceKey<CreativeModeTab> BUILDING_BLOCKS_TAB = tabKey("building_blocks");
	
	public static final Block 
		SULFUR_ORE = registerBlock(blockKey("sulfur_ore"), new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3.0F, 3.0F).sound(SoundType.DEEPSLATE).setId(blockKey("sulfur_ore"))), NATURAL_BLOCKS_TAB),
		SULFUR_NETHER_ORE = registerBlock(blockKey("sulfur_nether_ore"), new DropExperienceBlock(UniformInt.of(2, 5), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE).mapColor(MapColor.NETHER).setId(blockKey("sulfur_nether_ore"))), NATURAL_BLOCKS_TAB),
		SULFUR_BLOCK = registerBlock(blockKey("sulfur_block"), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).strength(5.0F, 6.0F).setId(blockKey("sulfur_block"))), BUILDING_BLOCKS_TAB),
		POTASSIUM_ORE = registerBlock(blockKey("potassium_ore"), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3.0F, 3.0F).setId(blockKey("potassium_ore"))), NATURAL_BLOCKS_TAB),
		POTASSIUM_BLOCK = registerBlock(blockKey("potassium_block"), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(5.0F, 6.0F).sound(SoundType.METAL).setId(blockKey("potassium_block"))), BUILDING_BLOCKS_TAB);
	
	// Initialize static variables (registration)
	public static void register() {}
	
	private static Block registerBlock(ResourceKey<Block> key, Block block, ResourceKey<CreativeModeTab> group) {	
		Registry.register(BuiltInRegistries.BLOCK, key, block);
		String path = key.identifier().getPath();
		ModItems.registerItem(itemKey(path), new BlockItem(block, new Item.Properties().setId(itemKey(path))), group);
		return block;
	}

	private static ResourceKey<Block> blockKey(String path) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, path));
	}

	private static ResourceKey<Item> itemKey(String path) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, path));
	}

	private static ResourceKey<CreativeModeTab> tabKey(String path) {
		return ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.withDefaultNamespace(path));
	}
}

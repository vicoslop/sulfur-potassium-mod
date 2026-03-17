package com.branders.sulfurpotassiummod.gen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import com.branders.sulfurpotassiummod.config.ConfigValues;
import com.branders.sulfurpotassiummod.registry.ModBlocks;
import com.branders.sulfurpotassiummod.registry.ModFeatures;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

/**
 *  Called from data generation where we create .json files with appropriate values.
 * 
 * 	@author Anders <Branders> Blomqvist
 */
public class OreGenProvider extends FabricDynamicRegistryProvider {

    private static int potassium_middle_vein_size = ConfigValues.CONFIG_SPEC.get("potassium_middle_vein_size");
    private static int potassium_middle_max_height = ConfigValues.CONFIG_SPEC.get("potassium_middle_max_height");
    private static int potassium_middle_min_height = ConfigValues.CONFIG_SPEC.get("potassium_middle_min_height");
    private static int potassium_middle_count = ConfigValues.CONFIG_SPEC.get("potassium_middle_count");
    
    private static int potassium_upper_vein_size = ConfigValues.CONFIG_SPEC.get("potassium_upper_vein_size");
    private static int potassium_upper_max_height = ConfigValues.CONFIG_SPEC.get("potassium_upper_max_height");
    private static int potassium_upper_min_height = ConfigValues.CONFIG_SPEC.get("potassium_upper_min_height");
    private static int potassium_upper_count = ConfigValues.CONFIG_SPEC.get("potassium_upper_count");
    
    private static int sulfur_vein_size = ConfigValues.CONFIG_SPEC.get("sulfur_vein_size");
    private static int sulfur_max_height = ConfigValues.CONFIG_SPEC.get("sulfur_max_height");
    private static int sulfur_count = ConfigValues.CONFIG_SPEC.get("sulfur_count");
    
    private static int sulfur_nether_vein_size = ConfigValues.CONFIG_SPEC.get("nether_sulfur_vein_size");
    private static int sulfur_nether_max_height = ConfigValues.CONFIG_SPEC.get("nether_sulfur_max_height");
    private static int sulfur_nether_count = ConfigValues.CONFIG_SPEC.get("nether_sulfur_count");
    
    private TagMatchTest base_stone = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    private TagMatchTest deepslate = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    private BlockMatchTest nether = new BlockMatchTest(Blocks.NETHERRACK);
    
    public OreGenProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "Sulfur & Potassium";
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        
        ConfiguredFeature<?, ?> POTASSIUM_MIDDLE = newFeature(base_stone, ModBlocks.POTASSIUM_ORE.defaultBlockState(), potassium_middle_vein_size);
        ConfiguredFeature<?, ?> POTASSIUM_UPPER  = newFeature(base_stone, ModBlocks.POTASSIUM_ORE.defaultBlockState(), potassium_upper_vein_size);
        
        ConfiguredFeature<?, ?> SULFUR  = newFeature(deepslate, ModBlocks.SULFUR_ORE.defaultBlockState(), sulfur_vein_size);
        ConfiguredFeature<?, ?> SULFUR_NETHER  = newFeature(nether, ModBlocks.SULFUR_NETHER_ORE.defaultBlockState(), sulfur_nether_vein_size);
        
        addEntry(entries,
                ModFeatures.CF_POTASSIUM_MIDDLE, 
                ModFeatures.PF_POTASSIUM_MIDDLE, 
                POTASSIUM_MIDDLE, 
                potassium_middle_count,
                potassium_middle_min_height,
                potassium_middle_max_height);
        
        addEntry(entries,
                ModFeatures.CF_POTASSIUM_UPPER, 
                ModFeatures.PF_POTASSIUM_UPPER, 
                POTASSIUM_UPPER, 
                potassium_upper_count,
                potassium_upper_min_height,
                potassium_upper_max_height);
        
        addEntry(entries,
                ModFeatures.CF_SULFUR, 
                ModFeatures.PF_SULFUR, 
                SULFUR, 
                sulfur_count,
                -64,
                sulfur_max_height);
        
        addEntry(entries,
                ModFeatures.CF_SULFUR_NETHER, 
                ModFeatures.PF_SULFUR_NETHER, 
                SULFUR_NETHER, 
                sulfur_nether_count,
                -64,
                sulfur_nether_max_height);
        
    }

    private void addEntry(Entries entries, ResourceKey<ConfiguredFeature<?, ?>> rcf, ResourceKey<PlacedFeature> rpf, ConfiguredFeature<?, ?> cf, int count, int minHeight, int maxHeight) {
        entries.add(rcf, cf);
        Holder<ConfiguredFeature<?, ?>> featureRef = Holder.direct(cf);
        PlacedFeature placedFeature = new PlacedFeature(featureRef, Arrays.asList(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight))));
        entries.add(rpf, placedFeature);
    }
    
    private ConfiguredFeature<?, ?> newFeature(TagMatchTest ruleTest, BlockState blockstate, int size) {
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ruleTest, blockstate, size));
    }
    
    private ConfiguredFeature<?, ?> newFeature(BlockMatchTest ruleTest, BlockState blockstate, int size) {
        return new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ruleTest, blockstate, size));
    }
}

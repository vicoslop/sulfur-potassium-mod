package com.branders.sulfurpotassiummod.registry;

import java.util.function.BiConsumer;

import com.branders.sulfurpotassiummod.SulfurPotassiumMod;
import com.branders.sulfurpotassiummod.config.ConfigValues;

import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * 	Handles the registration of the ore generation features from data generation.
 * 
 * 	@author Anders <Branders> Blomqvist
 */
public class ModFeatures {

	private static final int disable_gen_potassium = ConfigValues.CONFIG_SPEC.get("disable_gen_potassium");
	private static final int disable_gen_sulfur = ConfigValues.CONFIG_SPEC.get("disable_gen_sulfur");
	private static final int disable_gen_nether_sulfur = ConfigValues.CONFIG_SPEC.get("disable_gen_nether_sulfur");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CF_POTASSIUM_MIDDLE = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "potassium_ores_middle"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CF_POTASSIUM_UPPER = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "potassium_ores_upper"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CF_SULFUR = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "sulfur_ores"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> CF_SULFUR_NETHER = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "sulfur_nether_ores"));
    
    public static final ResourceKey<PlacedFeature> PF_POTASSIUM_MIDDLE = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "potassium_ores_middle"));
    public static final ResourceKey<PlacedFeature> PF_POTASSIUM_UPPER  = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "potassium_ores_upper"));
    public static final ResourceKey<PlacedFeature> PF_SULFUR  = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "sulfur_ores"));
    public static final ResourceKey<PlacedFeature> PF_SULFUR_NETHER  = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "sulfur_nether_ores"));
    
    public static void register() {
        // Overworld features
        BiomeModifications.create(Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "features"))
            .add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), overworldOres());
        
        // Nether features
        BiomeModifications.create(Identifier.fromNamespaceAndPath(SulfurPotassiumMod.MOD_ID, "nether_features"))
            .add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInTheNether(), netherOres());
    }
    
    private static BiConsumer<BiomeSelectionContext, BiomeModificationContext> overworldOres() {
        return (biomeSelectionContext, biomeModificationContext) -> {
            if(disable_gen_potassium == 0) {
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PF_POTASSIUM_MIDDLE);
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PF_POTASSIUM_UPPER);
            }
            if(disable_gen_sulfur == 0)
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PF_SULFUR);
        };
    }
    
    private static BiConsumer<BiomeSelectionContext, BiomeModificationContext> netherOres() {
        return (biomeSelectionContext, biomeModificationContext) -> {
            if(disable_gen_nether_sulfur == 0)
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PF_SULFUR_NETHER);
        };
    }
}

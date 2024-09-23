package net.thematizo.swarmcraftmod.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.thematizo.swarmcraftmod.SwarmCraftMod;

public class ModModelLayers {

    public static final ModelLayerLocation SWARMWORM_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(SwarmCraftMod.MOD_ID,"swarmworm"), "main");

    public static final ModelLayerLocation FIREPROJECTILE_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(SwarmCraftMod.MOD_ID,"fireball_projectile"), "main");


}

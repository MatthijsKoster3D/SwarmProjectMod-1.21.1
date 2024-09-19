package net.thematizo.swarmcraftmod.entity.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thematizo.swarmcraftmod.SwarmCraftMod;
import net.thematizo.swarmcraftmod.entity.client.FireballProjectileModel;
import net.thematizo.swarmcraftmod.entity.client.ModModelLayers;
import net.thematizo.swarmcraftmod.entity.custom.FireProjectileEntity;

public class FireProjectileRenderer extends EntityRenderer<FireProjectileEntity> {

    public FireProjectileRenderer(EntityRendererProvider.Context context) {
            super(context);
        }

    @Override
    public ResourceLocation getTextureLocation(FireProjectileEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(SwarmCraftMod.MOD_ID, "textures/entity/fire_projectile.png");
    }
}

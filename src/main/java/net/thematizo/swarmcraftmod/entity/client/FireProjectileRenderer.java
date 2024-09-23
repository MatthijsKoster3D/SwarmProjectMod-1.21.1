package net.thematizo.swarmcraftmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thematizo.swarmcraftmod.SwarmCraftMod;
import net.thematizo.swarmcraftmod.entity.custom.FireProjectileEntity;

public class FireProjectileRenderer extends EntityRenderer<FireProjectileEntity> {

    public FireProjectileRenderer(EntityRendererProvider.Context pContext) {
            super(pContext);
            //System.out.println("does this get rendered?");
        }

    @Override
    public ResourceLocation getTextureLocation(FireProjectileEntity pEntity) {
        System.out.println("Rendering FireProjectileEntity texture method");
        return ResourceLocation.fromNamespaceAndPath(SwarmCraftMod.MOD_ID, "textures/entity/fireball_projectile.png");
    }
}

package net.thematizo.swarmcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thematizo.swarmcraft.SwarmCraftMod;
import net.thematizo.swarmcraft.entity.custom.SwarmWormEntity;

public class SwarmWormRenderer extends MobRenderer<SwarmWormEntity, SwarmWormModel> {

    public SwarmWormRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SwarmWormModel(pContext.bakeLayer(ModModelLayers.SWARMWORM_LAYER)), 0.2f);
    }

    @Override
    public ResourceLocation getTextureLocation(SwarmWormEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(SwarmCraftMod.MOD_ID, "textures/entity/swarmworm.png");
    }

    @Override
    public void render(SwarmWormEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // Call the renderToBuffer method
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}

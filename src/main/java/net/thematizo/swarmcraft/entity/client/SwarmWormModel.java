package net.thematizo.swarmcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.thematizo.swarmcraft.entity.animations.SwarmWormAnimations;
import net.thematizo.swarmcraft.entity.custom.SwarmWormEntity;

public class SwarmWormModel extends HierarchicalModel<SwarmWormEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    public final ModelPart body;
    public final ModelPart middlebone;
    public final ModelPart backbone;
    public final ModelPart frontbone;

    public SwarmWormModel(ModelPart root) {
        this.body = root.getChild("body");
        this.middlebone = this.body.getChild("middlebone");
        this.backbone = this.body.getChild("backbone");
        this.frontbone = this.body.getChild("frontbone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition middlebone = body.addOrReplaceChild("middlebone", CubeListBuilder.create().texOffs(21, 22).addBox(-3.0F, -6.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(35, 35).addBox(3.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 12).addBox(-4.0F, -5.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition backbone = body.addOrReplaceChild("backbone", CubeListBuilder.create().texOffs(2, 29).addBox(-3.0F, -4.0F, 3.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 7).addBox(2.0F, -4.0F, 3.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(2, 15).addBox(-2.0F, -5.0F, 3.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition frontbone = body.addOrReplaceChild("frontbone", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -8.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(17, 16).addBox(-3.0F, -8.0F, -8.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(25, 33).addBox(-3.0F, -6.0F, -9.0F, 6.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 33).addBox(4.0F, -6.0F, -8.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(30, 0).addBox(-5.0F, -6.0F, -8.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(SwarmWormEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.walkAnimationState, SwarmWormAnimations.walk, ageInTicks, 1f);
        this.animate(entity.idleAnimationState, SwarmWormAnimations.idle, ageInTicks, 1f);
        this.animate(entity.attackAnimationState, SwarmWormAnimations.attack, ageInTicks, 2f);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}
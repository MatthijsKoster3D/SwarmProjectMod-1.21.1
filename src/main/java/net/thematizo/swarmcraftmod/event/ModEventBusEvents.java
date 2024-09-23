package net.thematizo.swarmcraftmod.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thematizo.swarmcraftmod.SwarmCraftMod;
import net.thematizo.swarmcraftmod.entity.ModEntities;
import net.thematizo.swarmcraftmod.entity.client.FireballProjectileModel;
import net.thematizo.swarmcraftmod.entity.client.ModModelLayers;
import net.thematizo.swarmcraftmod.entity.client.SwarmWormModel;
import net.thematizo.swarmcraftmod.entity.custom.SwarmWormEntity;

@Mod.EventBusSubscriber(modid = SwarmCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SWARMWORM_LAYER, SwarmWormModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FIREPROJECTILE_LAYER, FireballProjectileModel::createBodyLayer);
        System.out.println("ModEventBusEvents gets called");
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SWARMWORM.get(), SwarmWormEntity.createAttributes().build());
    }
}

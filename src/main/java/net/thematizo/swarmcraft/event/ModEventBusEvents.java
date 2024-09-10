package net.thematizo.swarmcraft.event;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thematizo.swarmcraft.SwarmCraftMod;
import net.thematizo.swarmcraft.entity.ModEntities;
import net.thematizo.swarmcraft.entity.client.ModModelLayers;
import net.thematizo.swarmcraft.entity.client.SwarmWormModel;
import net.thematizo.swarmcraft.entity.custom.SwarmWormEntity;

@Mod.EventBusSubscriber(modid = SwarmCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SWARMWORM_LAYER, SwarmWormModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SWARMWORM.get(), SwarmWormEntity.createAttributes().build());
    }
}

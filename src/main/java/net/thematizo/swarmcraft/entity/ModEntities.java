package net.thematizo.swarmcraft.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thematizo.swarmcraft.SwarmCraftMod;
import net.thematizo.swarmcraft.entity.custom.SwarmWormEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SwarmCraftMod.MOD_ID);

    public static final RegistryObject<EntityType<SwarmWormEntity>> SWARMWORM =
            ENTITY_TYPES.register("swarmworm",() -> EntityType.Builder.of(SwarmWormEntity::new, MobCategory.MONSTER)
                    .sized(1.1f,1.0f).build("swarmworm"));

    public static void register(IEventBus eventBus){

        ENTITY_TYPES.register(eventBus);
    }
}

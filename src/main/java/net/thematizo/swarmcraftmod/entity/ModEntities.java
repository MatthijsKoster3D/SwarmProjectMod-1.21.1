package net.thematizo.swarmcraftmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thematizo.swarmcraftmod.SwarmCraftMod;
import net.thematizo.swarmcraftmod.entity.custom.FireProjectileEntity;
import net.thematizo.swarmcraftmod.entity.custom.SwarmWormEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SwarmCraftMod.MOD_ID);

    public static final RegistryObject<EntityType<SwarmWormEntity>> SWARMWORM =
            ENTITY_TYPES.register("swarmworm",() -> EntityType.Builder.of(SwarmWormEntity::new, MobCategory.MONSTER)
                    .sized(1f,0.5f).build("swarmworm"));

    public static final RegistryObject<EntityType<FireProjectileEntity>> FIRE_PROJECTILE = ENTITY_TYPES.register("fire_projectile",
            () -> EntityType.Builder.<FireProjectileEntity>of(FireProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F) // Adjust size as needed
                    .build("fire_projectile"));

    public static void register(IEventBus eventBus){

        ENTITY_TYPES.register(eventBus);
    }


}

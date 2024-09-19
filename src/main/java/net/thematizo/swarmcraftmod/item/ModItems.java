package net.thematizo.swarmcraftmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thematizo.swarmcraftmod.SwarmCraftMod;
import net.thematizo.swarmcraftmod.item.custom.SwarmWandFire;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SwarmCraftMod.MOD_ID);

    public static final RegistryObject<Item> GOLD_COIN = ITEMS.register("gold_coin",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SWARM_WAND_FIRE = ITEMS.register("swarm_wand_fire",
            () -> new SwarmWandFire(new Item.Properties().stacksTo(1)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

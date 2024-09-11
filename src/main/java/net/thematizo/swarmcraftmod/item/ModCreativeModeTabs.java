package net.thematizo.swarmcraftmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thematizo.swarmcraftmod.SwarmCraftMod;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SwarmCraftMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SWARM_CRAFT_ITEMS_TAB = CREATIVE_MODE_TABS.register("swarm_craft_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GOLD_COIN.get()))
                    .title(Component.translatable("creativetab.swarmcraftmod.swarm_craft_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GOLD_COIN.get());


                    }).build());

    public static final RegistryObject<CreativeModeTab> SWARM_CRAFT_WEAPONS_TAB = CREATIVE_MODE_TABS.register("swarm_craft_weapons_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GOLD_COIN.get()))
                    .withTabsBefore(SWARM_CRAFT_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.swarmcraftmod.swarm_craft_weapons"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GOLD_COIN.get());


                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

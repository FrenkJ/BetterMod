package net.deval.bettermod.item;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTab {
    public static final DeferredRegister<CreativeModeTab>CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab>MOD_ITEMS_TAB =CREATIVE_MODE_TABS.register("better_mod_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.ALEXANDRITE.get()))
                    .title(Component.translatable("creativetab.bettermod.alexandrite_items"))
                    .displayItems((itemDisplayParameters,output)->{
                        output.accept(ModItems.ALEXANDRITE.get());
                        output.accept(ModItems.RAW_GARNET.get());

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab>MOD_BLOCKS_TAB =CREATIVE_MODE_TABS.register("better_mod_block_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.ALEXANDRITE_BLOCK.get()))
                    .title(Component.translatable("creativetab.bettermod.alexandrite_blocks"))
                    .displayItems((itemDisplayParameters,output)->{
                        output.accept(ModBlocks.ALEXANDRITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
                        output.accept(ModBlocks.ALEXANDRITE_ORE.get());
                        output.accept(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}

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
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.GARNET_GEM.get()))
                    .title(Component.translatable("creativetab.bettermod.garnet_items"))
                    .displayItems((itemDisplayParameters,output)->{
                        output.accept(ModItems.RAW_GARNET.get());
                        output.accept(ModItems.GARNET_GEM.get());
                        output.accept(ModItems.GARNET_SWORD.get());
                        output.accept(ModItems.GARNET_PICKAXE.get());
                        output.accept(ModItems.GARNET_SHOVEL.get());
                        output.accept(ModItems.GARNET_AXE.get());
                        output.accept(ModItems.GARNET_HOE.get());
                        output.accept(ModItems.GARNET_HELMET.get());
                        output.accept(ModItems.GARNET_CHESTPLATE.get());
                        output.accept(ModItems.GARNET_LEGGINGS.get());
                        output.accept(ModItems.GARNET_BOOTS.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab>MOD_BLOCKS_TAB =CREATIVE_MODE_TABS.register("better_mod_block_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModBlocks.GARNET_BLOCK.get()))
                    .title(Component.translatable("creativetab.bettermod.garnet_blocks"))
                    .displayItems((itemDisplayParameters,output)->{
                        output.accept(ModBlocks.GARNET_BLOCK.get());
                        output.accept(ModBlocks.RAW_GARNET_BLOCK.get());
                        output.accept(ModBlocks.GARNET_ORE.get());
                        output.accept(ModBlocks.GARNET_DEEPSLATE_ORE.get());
                       // output.accept(ModBlocks.COAL_GENERATOR.get());
                    })
                    .build());
// ky esht nje koment
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}

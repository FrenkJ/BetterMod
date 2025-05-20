package net.deval.bettermod.item;

import net.deval.bettermod.BetterMod;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BetterMod.MOD_ID);

    public static final RegistryObject<Item> RAW_GARNET = ITEMS.register("raw_garnet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GARNET_GEM = ITEMS.register("garnet_gem",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GARNET_SWORD = ITEMS.register("garnet_sword",
            () -> new SwordItem(ModToolTiers.GARNET, new Item.Properties().attributes(
                    SwordItem.createAttributes(ModToolTiers.GARNET,3,-2.4f) )));
    public static final RegistryObject<Item> GARNET_PICKAXE = ITEMS.register("garnet_pickaxe",
            () -> new PickaxeItem(ModToolTiers.GARNET, new Item.Properties().attributes(
                    PickaxeItem.createAttributes(ModToolTiers.GARNET,1,-2.8f) )));
    public static final RegistryObject<Item> GARNET_SHOVEL = ITEMS.register("garnet_shovel",
            () -> new ShovelItem(ModToolTiers.GARNET, new Item.Properties().attributes(
                    ShovelItem.createAttributes(ModToolTiers.GARNET,1.5f,-3.0f) )));
    public static final RegistryObject<Item> GARNET_AXE = ITEMS.register("garnet_axe",
            () -> new AxeItem(ModToolTiers.GARNET, new Item.Properties().attributes(
                    AxeItem.createAttributes(ModToolTiers.GARNET,6,-3.2f) )));
    public static final RegistryObject<Item> GARNET_HOE = ITEMS.register("garnet_hoe",
            () -> new HoeItem(ModToolTiers.GARNET, new Item.Properties().attributes(
                    HoeItem.createAttributes(ModToolTiers.GARNET,0,-3.0f) )));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
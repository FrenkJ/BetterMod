package net.deval.bettermod.item;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.ModBlocks;
import net.deval.bettermod.entity.ModEntities;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
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

    public static final RegistryObject<Item> GARNET_HELMET = ITEMS.register("garnet_helmet",
            () -> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(18))));
    public static final RegistryObject<Item> GARNET_CHESTPLATE = ITEMS.register("garnet_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(18))));
    public static final RegistryObject<Item> GARNET_LEGGINGS = ITEMS.register("garnet_leggings",
            () -> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(18))));
    public static final RegistryObject<Item> GARNET_BOOTS = ITEMS.register("garnet_boots",
            () -> new ArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(18))));
   /* public static final RegistryObject<Item> BLUE_GEM_GENERATOR_ITEM =ITEMS.register("blue_gem_generator_item",
            ()-> new BlockItem(ModBlocks.BLUE_GEM_GENERATOR.get(),new Item.Properties()));*/

    public static final RegistryObject<Item> ALPHAGOLEM_SPAWN_EGG = ITEMS.register("alphagolem_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ALPHAGOLEM, 0x53524b, 0xdac741, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
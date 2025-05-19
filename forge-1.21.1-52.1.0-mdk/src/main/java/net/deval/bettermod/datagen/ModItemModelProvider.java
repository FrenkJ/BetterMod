package net.deval.bettermod.datagen;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, BetterMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ALEXANDRITE.get());
        basicItem(ModItems.RAW_GARNET.get());
        basicItem(ModItems.GARNET_GEM.get());


        handheldItem(ModItems.GARNET_SWORD);
        handheldItem(ModItems.GARNET_PICKAXE);
        handheldItem(ModItems.GARNET_AXE);
        handheldItem(ModItems.GARNET_SHOVEL);
        handheldItem(ModItems.GARNET_HOE);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(BetterMod.MOD_ID, "item/" + item.getId().getPath()));

    }
}

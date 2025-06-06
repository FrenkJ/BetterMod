package net.deval.bettermod.datagen;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.item.ModItems;
import net.deval.bettermod.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags,  @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, BetterMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.RAW_GARNET.get())
                .add(ModItems.GARNET_GEM.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.GARNET_HELMET.get())
                .add(ModItems.GARNET_CHESTPLATE.get())
                .add(ModItems.GARNET_LEGGINGS.get())
                .add(ModItems.GARNET_BOOTS.get());
    }
}

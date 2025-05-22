package net.deval.bettermod.datagen;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.ModBlocks;
import net.deval.bettermod.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BetterMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.GARNET_BLOCK.get())
                .add(ModBlocks.GARNET_ORE.get())
                .add(ModBlocks.GARNET_DEEPSLATE_ORE.get())
                .add(ModBlocks.RAW_GARNET_BLOCK.get());




        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.GARNET_DEEPSLATE_ORE.get());



        tag(ModTags.Blocks.NEEDS_GARNET_TOOL)
                .add(ModBlocks.GARNET_BLOCK.get())
                .add(Blocks.OBSIDIAN)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_GARNET_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_GARNET_TOOL);
    }
}

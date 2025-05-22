package net.deval.bettermod.datagen;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output,  ExistingFileHelper exFileHelper) {
        super(output, BetterMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.GARNET_BLOCK);
        blockWithItem(ModBlocks.RAW_GARNET_BLOCK);
        blockWithItem(ModBlocks.GARNET_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.GARNET_ORE);


    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}

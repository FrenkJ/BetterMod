package net.deval.bettermod.block;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.energyBlock.CoalGeneratorBlock;
import net.deval.bettermod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BetterMod.MOD_ID);

    // Keep track of blocks for which we also want to register block items
    private static final List<RegistryObject<Block>> BLOCKS_WITH_ITEMS = new ArrayList<>();

    public static final RegistryObject<Block> GARNET_BLOCK = registerBlock("garnet_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> RAW_GARNET_BLOCK = registerBlock("raw_garnet_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GARNET_ORE = registerBlock("garnet_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GARNET_DEEPSLATE_ORE = registerBlock("garnet_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

  /*  public static final RegistryObject<Block>BLUE_GEM_GENERATOR= BLOCKS.register("blue_gem_generator",
            ()-> new Block(BlockBehaviour.Properties.of().strength(3.5F).noOcclusion()));*/
 public static final RegistryObject<Block>COAL_GENERATOR=registerBlock("coal_generator",
          ()->new CoalGeneratorBlock(BlockBehaviour.Properties.of().noOcclusion()))

    ;



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        BLOCKS_WITH_ITEMS.add((RegistryObject<Block>) toReturn);
        return toReturn;
    }

    // Registers the blocks to the mod event bus
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    // Registers the corresponding block items to the mod event bus
    public static void registerBlockItems(IEventBus eventBus) {
        for (RegistryObject<Block> block : BLOCKS_WITH_ITEMS) {
            ModItems.ITEMS.register(block.getId().getPath(), () ->
                    new BlockItem(block.get(), new Item.Properties()));
        }
    }
}

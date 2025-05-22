package net.deval.bettermod.entity;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.ModBlocks;
import net.deval.bettermod.entity.custom.CoalGeneratorEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterMod.MOD_ID);

public static final RegistryObject<BlockEntityType<CoalGeneratorEntity>> COAL_GENERATOR_BE=
        BLOCK_ENTITIES.register("coal_generator_be",
                ()->BlockEntityType.Builder.of(CoalGeneratorEntity::new, ModBlocks.COAL_GENERATOR.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

package net.deval.bettermod.entity;


import net.deval.bettermod.BetterMod;
import net.deval.bettermod.entity.custom.AlphaGolemEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BetterMod.MOD_ID);

    public static final RegistryObject<EntityType<AlphaGolemEntity>> ALPHAGOLEM =
            ENTITY_TYPES.register("alphagolem", () -> EntityType.Builder.of(AlphaGolemEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.5f).build("alphagolem"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
package net.deval.bettermod.event;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.entity.ModEntities;
import net.deval.bettermod.entity.client.AlphaGolemModel;
import net.deval.bettermod.entity.custom.AlphaGolemEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AlphaGolemModel.LAYER_LOCATION, AlphaGolemModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ALPHA_GOLEM.get(), AlphaGolemEntity.createAttributes().build());
    }
}
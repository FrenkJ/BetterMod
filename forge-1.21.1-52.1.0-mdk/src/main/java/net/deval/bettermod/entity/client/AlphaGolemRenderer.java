package net.deval.bettermod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.deval.bettermod.BetterMod;
import net.deval.bettermod.entity.custom.AlphaGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AlphaGolemRenderer extends MobRenderer<AlphaGolemEntity, AlphaGolemModel<AlphaGolemEntity>> {
    public AlphaGolemRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AlphaGolemModel<>(pContext.bakeLayer(AlphaGolemModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(AlphaGolemEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(BetterMod.MOD_ID, "textures/entity/alpha_golem/alpha_golem.png");
    }

    @Override
    public void render(AlphaGolemEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
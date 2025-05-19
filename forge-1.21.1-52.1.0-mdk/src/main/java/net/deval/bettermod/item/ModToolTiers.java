package net.deval.bettermod.item;

import net.deval.bettermod.utils.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier GARNET = new ForgeTier(1500,4,4f, 30,
            ModTags.Blocks.NEEDS_GARNET_TOOL, () -> Ingredient.of(ModItems.GARNET_GEM.get()),
            ModTags.Blocks.INCORRECT_FOR_GARNET_TOOL);
}

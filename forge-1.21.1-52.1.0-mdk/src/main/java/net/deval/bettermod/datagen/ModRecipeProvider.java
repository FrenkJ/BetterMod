package net.deval.bettermod.datagen;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.ModBlocks;
import net.deval.bettermod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(ModItems.RAW_GARNET.get(),
                ModBlocks.GARNET_ORE.get(), ModBlocks.GARNET_DEEPSLATE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GARNET_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.GARNET_GEM.get())
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GARNET_GEM.get(), 9)
                .requires(ModBlocks.GARNET_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.GARNET_BLOCK.get()), has(ModBlocks.GARNET_BLOCK.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_SWORD.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" S ")
                .define('A', ModItems.GARNET_GEM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_PICKAXE.get())
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.GARNET_GEM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_AXE.get())
                .pattern("AA ")
                .pattern("AS ")
                .pattern(" S ")
                .define('A', ModItems.GARNET_GEM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_SHOVEL.get())
                .pattern(" A ")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.GARNET_GEM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_HOE.get())
                .pattern("AA ")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.GARNET_GEM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.GARNET_GEM.get())
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.GARNET_GEM.get())
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.GARNET_GEM.get())
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GARNET_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.GARNET_GEM.get())
                .unlockedBy(getHasName(ModItems.GARNET_GEM.get()), has(ModItems.GARNET_GEM.get())).save(pRecipeOutput);
        oreSmelting(pRecipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GARNET_GEM.get(), 0.25f, 200, "garnet_gem");
        oreBlasting(pRecipeOutput, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.GARNET_GEM.get(), 0.25f, 100, "garnet_gem");

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, BetterMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

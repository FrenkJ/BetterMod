package net.deval.bettermod.screen;

import net.deval.bettermod.BetterMod;
import net.deval.bettermod.block.entity.custom.CoalGeneratorEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModMenuTypes  {
    public static final DeferredRegister<MenuType<?>> MENUS=
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, BetterMod.MOD_ID);


    public static final RegistryObject<MenuType<CoalGeneratorMenu>> COAL_GENERATOR_MENU =
            registerMenuType(CoalGeneratorMenu::new, "coal_generator_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }




    public static void register(IEventBus bus){
        MENUS.register(bus);
    }



}

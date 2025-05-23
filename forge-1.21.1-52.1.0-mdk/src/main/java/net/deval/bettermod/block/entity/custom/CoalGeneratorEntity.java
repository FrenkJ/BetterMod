package net.deval.bettermod.block.entity.custom;

import net.deval.bettermod.block.entity.ModBlockEntities;
import net.deval.bettermod.item.ModItems;
import net.deval.bettermod.screen.CoalGeneratorMenu;
import net.deval.bettermod.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoalGeneratorEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler inventory= new ItemStackHandler(1) {




        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }



        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private LazyOptional<ItemStackHandler> lazyItemHandler= LazyOptional.empty();
    private  ContainerData data;
    private int progress= 0;
    private int maxProgress =78;

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap== ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();

        }


        return super.getCapability(cap, side);
    }
    public CoalGeneratorEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.COAL_GENERATOR_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CoalGeneratorEntity.this.progress;
                    case 1 -> CoalGeneratorEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {

                switch (index) {
                    case 0 -> CoalGeneratorEntity.this.progress = value;
                    case 1 -> CoalGeneratorEntity.this.maxProgress = value;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }

        };
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler= LazyOptional.of(() -> inventory);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }




    public void clearContents(){
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }
    public void drops(){
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", inventory.serializeNBT(pRegistries));
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        inventory.deserializeNBT(pRegistries,pTag.getCompound("inventory"));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("CoalGenerator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CoalGeneratorMenu(id,inventory,this,data);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, CoalGeneratorEntity pEntity) {
        if (level.isClientSide()) {
            return;
        }
        if (hasRecipe(pEntity)){
           pEntity.progress++;
           setChanged(level, blockPos, state);
           if (pEntity.progress>=pEntity.maxProgress) {
               craftItem(pEntity);
           }
        }else {
            pEntity.resetProgress();
            setChanged(level, blockPos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CoalGeneratorEntity pEntity) {
        pEntity.inventory.extractItem(1,1,false);
        pEntity.inventory.setStackInSlot(2,new ItemStack(ModItems.GARNET_GEM.get(),
                pEntity.inventory.getStackInSlot(2).getCount()+1));
        pEntity.resetProgress();
    }

    private static boolean hasRecipe(CoalGeneratorEntity entity) {

        SimpleContainer inventory = new SimpleContainer(entity.inventory.getSlots());
        for (int i =0 ;i<entity.inventory.getSlots();i++) {
            inventory.setItem(i, entity.inventory.getStackInSlot(i));
        }

        boolean hasItemInFirstSlot=entity.inventory.getStackInSlot(1).getItem()== ModItems.GARNET_GEM.get();

        return hasItemInFirstSlot && canInsertAmmountIntoOutputSlot(inventory)&&
                canInsertItemIntoOutputSlot(inventory,new ItemStack(ModItems.GARNET_GEM.get(),1));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return  inventory.getItem(2).getItem()== itemStack.getItem()|| inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize()>inventory.getItem(2).getCount();
    }
}

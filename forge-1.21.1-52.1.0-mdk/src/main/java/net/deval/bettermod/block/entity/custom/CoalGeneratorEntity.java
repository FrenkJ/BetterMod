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
    public final ItemStackHandler inventory= new ItemStackHandler(2) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                if(!level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }
        };

        private static final int INPUT_SLOT = 0;
        private static final int OUTPUT_SLOT = 1;

        private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

        protected final ContainerData data;
        private int progress = 0;
        private int maxProgress = 72;

        public CoalGeneratorEntity(BlockPos pPos, BlockState pBlockState) {
            super(ModBlockEntities.COAL_GENERATOR_BE.get(), pPos, pBlockState);
            data = new ContainerData() {
                @Override
                public int get(int i) {
                    return switch (i) {
                        case 0 -> CoalGeneratorEntity.this.progress;
                        case 1 -> CoalGeneratorEntity.this.maxProgress;
                        default -> 0;
                    };
                }

                @Override
                public void set(int i, int value) {
                    switch (i) {
                        case 0: CoalGeneratorEntity.this.progress = value;
                        case 1: CoalGeneratorEntity.this.maxProgress = value;
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
            lazyItemHandler = LazyOptional.of(() -> inventory);
        }

        @Override
        public void invalidateCaps() {
            super.invalidateCaps();
            lazyItemHandler.invalidate();
        }

        public void drops() {
            SimpleContainer inv = new SimpleContainer(inventory.getSlots());
            for (int i = 0; i < inventory.getSlots(); i++) {
                inv.setItem(i, inventory.getStackInSlot(i));
            }

            Containers.dropContents(this.level, this.worldPosition, inv);
        }

        @Override
        protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
            pTag.put("inventory", inventory.serializeNBT(pRegistries));
            pTag.putInt("growth_chamber.progress", progress);
            pTag.putInt("growth_chamber.max_progress", maxProgress);

            super.saveAdditional(pTag, pRegistries);
        }

        @Override
        protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
            super.loadAdditional(pTag, pRegistries);

            inventory.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
            progress = pTag.getInt("growth_chamber.progress");
            maxProgress = pTag.getInt("growth_chamber.max_progress");
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("block.tutorialmod.growth_chamber");
        }

        @Nullable
        @Override
        public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
            return new CoalGeneratorMenu(pContainerId, pPlayerInventory, this, this.data);
        }

        public void tick(Level level, BlockPos blockPos, BlockState blockState) {
            if(hasRecipe()) {
                increaseCraftingProgress();
                setChanged(level, blockPos, blockState);

                if (hasCraftingFinished()) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        }

        private void resetProgress() {
            this.progress = 0;
            this.maxProgress = 72;
        }

        private void craftItem() {
            ItemStack output = new ItemStack(ModItems.GARNET_GEM.get());

            inventory.extractItem(INPUT_SLOT, 1, false);
            inventory.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                   inventory.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
        }

        private boolean hasCraftingFinished() {
            return this.progress >= this.maxProgress;
        }

        private void increaseCraftingProgress() {
            progress++;
        }

        private boolean hasRecipe() {
            Item input = ModItems.RAW_GARNET.get();
            ItemStack output = new ItemStack(ModItems.GARNET_GEM.get());

            return inventory.getStackInSlot(INPUT_SLOT).is(input) && canInsertItemIntoOutputSlot(output)
                    && canInsertAmountIntoOutputSlot(output.getCount());
        }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.inventory.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : inventory.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
        }

        @Override
        public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
            return saveWithoutMetadata(pRegistries);
        }

        @Nullable
        @Override
        public Packet<ClientGamePacketListener> getUpdatePacket() {
            return ClientboundBlockEntityDataPacket.create(this);
        }
}

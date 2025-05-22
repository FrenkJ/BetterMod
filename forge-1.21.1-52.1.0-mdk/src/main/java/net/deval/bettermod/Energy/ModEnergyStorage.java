package net.deval.bettermod.Energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.energy.EnergyStorage;

public class ModEnergyStorage extends EnergyStorage {

    /**
     * Interface for notifying energy changes.
     */
    public interface IEnergyChangeListener {
        void onEnergyChanged();
    }

    private IEnergyChangeListener listener;

    // Constructors

    public ModEnergyStorage(int capacity) {
        super(capacity);
    }

    public ModEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    /**
     * Constructor with listener for energy change callbacks.
     */
    public ModEnergyStorage(int capacity, IEnergyChangeListener listener) {
        super(capacity);
        this.listener = listener;
    }

    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy, IEnergyChangeListener listener) {
        super(capacity, maxReceive, maxExtract, energy);
        this.listener = listener;
    }

    // Setter for listener in case you want to set later
    public void setListener(IEnergyChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Directly sets the energy stored, clamped between 0 and capacity.
     * Notifies listener if energy changed.
     */
    public void setEnergy(int energy) {
        if (energy < 0) energy = 0;
        if (energy > capacity) energy = capacity;

        if (this.energy != energy) {
            this.energy = energy;
            notifyListener();
        }
    }

    /**
     * Adds energy, clamped to capacity.
     * Notifies listener if energy changed.
     */
    public void addEnergy(int energy) {
        setEnergy(this.energy + energy);
    }

    /**
     * Removes energy, clamped to zero.
     * Notifies listener if energy changed.
     */
    public void removeEnergy(int energy) {
        setEnergy(this.energy - energy);
    }

    /**
     * Notify the listener of energy change.
     */
    private void notifyListener() {
        if (listener != null) {
            listener.onEnergyChanged();
        }
    }

    /**
     * Writes energy value to NBT.
     */
    public CompoundTag writeNBT(CompoundTag tag) {
        tag.putInt("Energy", this.energy);
        return tag;
    }

    /**
     * Reads energy value from NBT.
     */
    public void readNBT(CompoundTag tag) {
        if (tag.contains("Energy")) {
            setEnergy(tag.getInt("Energy"));
        }
    }
}

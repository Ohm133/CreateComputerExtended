package com.ohm133.createcomputerextended.registry;

import com.ohm133.createcomputerextended.CreateComputerExtended;
import com.ohm133.createcomputerextended.block.NetworkCasingBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, CreateComputerExtended.MODID);

    public static final DeferredHolder<Block, NetworkCasingBlock> NETWORK_CASING =
            BLOCKS.register("network_casing", NetworkCasingBlock::new);
    public static final DeferredHolder<Block, NetworkGearboxBlock> NETWORK_GEARBOX =
            BLOCKS.register("network_gearbox", NetworkGearboxBlock::new);
    public static final DeferredHolder<Block, NetworkShaftBlock> NETWORK_SHAFT =
            BLOCKS.register("network_shaft", NetworkShaftBlock::new);
}
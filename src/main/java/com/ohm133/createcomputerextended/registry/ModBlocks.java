package com.ohm133.createcomputerextended.registry;

import com.ohm133.createcomputerextended.CreateComputerExtended;
import com.ohm133.createcomputerextended.block.NetworkCasingBlock;
import com.ohm133.createcomputerextended.block.NetworkEncasedShaftBlock;
import com.ohm133.createcomputerextended.block.NetworkGearboxBlock;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, CreateComputerExtended.MODID);

    public static final DeferredHolder<Block, NetworkCasingBlock> NETWORK_CASING =
            BLOCKS.register("network_casing",
                    () -> new NetworkCasingBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(3.0f)));

    public static final DeferredHolder<Block, NetworkGearboxBlock> NETWORK_GEARBOX =
            BLOCKS.register("network_gearbox",
                    () -> new NetworkGearboxBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(3.0f)));

    public static final DeferredHolder<Block, NetworkEncasedShaftBlock> NETWORK_ENCASED_SHAFT =
            BLOCKS.register("network_encased_shaft",
                    () -> new NetworkEncasedShaftBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(3.0f)));
}
package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.NetworkMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, NetworkMod.MODID);

    public static final DeferredHolder<Block, NetworkCasing> NETWORK_CASING =
            BLOCKS.register("network_casing", NetworkCasing::new);
}
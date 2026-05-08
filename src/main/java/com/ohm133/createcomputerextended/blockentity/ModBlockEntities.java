package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.NetworkMod;
import com.ohm133.createcomputerextended.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlockEntities {

    // registre des block entities
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NetworkMod.MODID);

    // notre BlockEntity
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetworkBlockEntity>>
            NETWORK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register(
                    "network_block_entity",

                    // création du type
                    () -> BlockEntityType.Builder.of(
                            NetworkBlockEntity::new,

                            // bloc associé
                            ModBlocks.NETWORK_CASING.get()
                    ).build(null)
            );
}
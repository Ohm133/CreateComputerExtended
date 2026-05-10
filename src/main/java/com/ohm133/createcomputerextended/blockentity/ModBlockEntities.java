package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.CreateComputerExtended;
import com.ohm133.createcomputerextended.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {

    /*
     * Registre des BlockEntityType
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateComputerExtended.MODID);

    /*
     * Déclaration du BlockEntityType
     */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetworkBlockEntity>>
            NETWORK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register(
                    "network_block_entity",
                    () -> BlockEntityType.Builder.of(
                            NetworkBlockEntity::new,
                            ModBlocks.NETWORK_CASING.get()
                    ).build(null)
            );

    /*
     * Enregistrement dans le bus NeoForge
     */
    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
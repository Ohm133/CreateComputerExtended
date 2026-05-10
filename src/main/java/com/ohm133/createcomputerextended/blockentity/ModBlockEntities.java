package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.CreateComputerExtended;
import com.ohm133.createcomputerextended.registry.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreateComputerExtended.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetworkCasingBlockEntity>> NETWORK_CASING_BE =
            BLOCK_ENTITIES.register("network_casing",
                    () -> BlockEntityType.Builder.of(
                            NetworkCasingBlockEntity::new,
                            ModBlocks.NETWORK_CASING.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetworkEncasedShaftBlockEntity>> NETWORK_ENCASED_SHAFT_BE =
            BLOCK_ENTITIES.register("network_encased_shaft",
                    () -> BlockEntityType.Builder.of(
                            NetworkEncasedShaftBlockEntity::new,
                            ModBlocks.NETWORK_ENCASED_SHAFT.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetworkGearboxBlockEntity>> NETWORK_GEARBOX_BE =
            BLOCK_ENTITIES.register("network_gearbox",
                    () -> BlockEntityType.Builder.of(
                            NetworkGearboxBlockEntity::new,
                            ModBlocks.NETWORK_GEARBOX.get()
                    ).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
package com.ohm133.createcomputerextended;

import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;
import com.ohm133.createcomputerextended.item.ModItems;
import com.ohm133.createcomputerextended.registry.ModBlocks;

import dan200.computercraft.api.network.wired.WiredElementCapability;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod(CreateComputerExtended.MODID)
public class CreateComputerExtended {
    public static final String MODID = "createcomputerextended";

    public CreateComputerExtended(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);

        modBus.addListener(this::registerCapabilities);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                WiredElementCapability.get(),
                ModBlockEntities.NETWORK_CASING_BE.get(),
                (blockEntity, side) -> blockEntity
        );

        event.registerBlockEntity(
                WiredElementCapability.get(),
                ModBlockEntities.NETWORK_ENCASED_SHAFT_BE.get(),
                (blockEntity, side) -> blockEntity
        );

        event.registerBlockEntity(
                WiredElementCapability.get(),
                ModBlockEntities.NETWORK_GEARBOX_BE.get(),
                (blockEntity, side) -> blockEntity
        );
    }
}
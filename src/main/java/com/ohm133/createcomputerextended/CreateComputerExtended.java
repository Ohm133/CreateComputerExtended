package com.ohm133.createcomputerextended;

import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;
import com.ohm133.createcomputerextended.item.ModItems;
import com.ohm133.createcomputerextended.registry.ModBlocks;
import com.ohm133.createcomputerextended.blockentity.NetworkCasingBlockEntity;
import com.ohm133.createcomputerextended.blockentity.NetworkEncasedShaftBlockEntity;
import com.ohm133.createcomputerextended.blockentity.NetworkGearboxBlockEntity;

import dan200.computercraft.api.network.wired.WiredElementCapability;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.minecraft.core.Direction;

@Mod(CreateComputerExtended.MODID)
public class CreateComputerExtended {
    public static final String MODID = "createcomputerextended";

    public CreateComputerExtended(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);

    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                WiredElementCapability.get(),
                ModBlockEntities.NETWORK_CASING_BE.get(),
                (NetworkCasingBlockEntity be, Direction side) -> be.getNetworkElement());

        event.registerBlockEntity(
                WiredElementCapability.get(),
                ModBlockEntities.NETWORK_ENCASED_SHAFT_BE.get(),
                (NetworkEncasedShaftBlockEntity be, Direction side) -> be.getNetworkElement());

        event.registerBlockEntity(
                WiredElementCapability.get(),
                ModBlockEntities.NETWORK_GEARBOX_BE.get(),
                (NetworkGearboxBlockEntity be, Direction side) -> be.getNetworkElement());
    }
}
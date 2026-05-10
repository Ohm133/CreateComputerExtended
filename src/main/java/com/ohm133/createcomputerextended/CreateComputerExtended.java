package com.ohm133.createcomputerextended;

import com.ohm133.createcomputerextended.block.ModBlocks;
import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;
import com.ohm133.createcomputerextended.item.ModItems;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(CreateComputerExtended.MODID)
public class CreateComputerExtended {

    public static final String MODID = "createcomputerextended";

    public CreateComputerExtended(IEventBus modBus) {

        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBlockEntities.register(modBus);
    }
}
package com.ohm133.createcomputerextended;

import com.ohm133.createcomputerextended.block.ModBlocks;
import com.ohm133.createcomputerextended.item.ModItems;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NetworkMod.MODID)
public class NetworkMod {

    public static final String MODID = "createcomputerextended";

    public NetworkMod(IEventBus modBus) {

        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
    }
}
package com.ohm133.createcomputerextended.item;

import com.ohm133.createcomputerextended.CreateComputerExtended;
import com.ohm133.createcomputerextended.registry.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, CreateComputerExtended.MODID);

    public static final DeferredHolder<Item, BlockItem> NETWORK_CASING_ITEM =
            ITEMS.register("network_casing",
                    () -> new BlockItem(ModBlocks.NETWORK_CASING.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NETWORK_ENCASED_SHAFT_ITEM =
            ITEMS.register("network_encased_shaft",
                    () -> new BlockItem(ModBlocks.NETWORK_ENCASED_SHAFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NETWORK_GEARBOX_ITEM =
            ITEMS.register("network_gearbox",
                    () -> new BlockItem(ModBlocks.NETWORK_GEARBOX.get(), new Item.Properties()));
}
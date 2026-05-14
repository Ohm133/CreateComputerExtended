package com.ohm133.createcomputerextended.events;

import com.ohm133.createcomputerextended.CreateComputerExtended;
import com.ohm133.createcomputerextended.blockentity.NetworkCableHost;
import com.ohm133.createcomputerextended.network.SwivelBridgeResolver;
import com.ohm133.createcomputerextended.network.SwivelBridgeManager;

import dev.simulated_team.simulated.content.blocks.swivel_bearing.SwivelBearingBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = CreateComputerExtended.MODID, bus = EventBusSubscriber.Bus.GAME)
public class NetworkBlockEvents {
    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
        if (event.getLevel() instanceof Level level && !level.isClientSide) {
            refreshAround(level, event.getPos());
        }
    }

    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof Level level) || level.isClientSide) {
            return;
        }

        refreshAround(level, event.getPos());
    }

    private static void refreshAround(Level level, BlockPos pos) {
        refresh(level, pos);

        for (Direction direction : Direction.values()) {
            refresh(level, pos.relative(direction));
        }
    }

    private static void refresh(Level level, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);

        if (be instanceof NetworkCableHost host) {
            host.refreshNetworkConnections();
        }

        if (be instanceof SwivelBearingBlockEntity swivel) {
            SwivelBridgeResolver.resolveSwivel(level, swivel);
        }
    }
}
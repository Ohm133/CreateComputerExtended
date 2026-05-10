package com.ohm133.createcomputerextended.network;

import com.ohm133.createcomputerextended.blockentity.AbstractNetworkBlockEntity;

import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredElementCapability;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class NetworkConnector {
    public static void connectNeighbours(AbstractNetworkBlockEntity blockEntity) {
        Level level = blockEntity.getLevel();

        if (level == null || level.isClientSide) {
            return;
        }

        for (Direction direction : Direction.values()) {
            BlockPos targetPos = blockEntity.getBlockPos().relative(direction);

            WiredElement neighbour = level.getCapability(
                    WiredElementCapability.get(),
                    targetPos,
                    direction.getOpposite()
            );

            if (neighbour != null && neighbour.getNode() != blockEntity.getNode()) {
                blockEntity.getNode().connectTo(neighbour.getNode());
            }
        }
    }
}
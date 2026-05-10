package com.ohm133.createcomputerextended.network;

import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredElementCapability;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class NetworkConnector {
    public static void connectNeighbours(BlockEntity owner, WiredElement self) {
        Level level = owner.getLevel();

        if (level == null || level.isClientSide) {
            return;
        }

        for (Direction direction : Direction.values()) {
            BlockPos neighbourPos = owner.getBlockPos().relative(direction);

            WiredElement neighbour = level.getCapability(
                    WiredElementCapability.get(),
                    neighbourPos,
                    direction.getOpposite()
            );

            if (neighbour != null && neighbour.getNode() != self.getNode()) {
                self.getNode().connectTo(neighbour.getNode());
            }
        }
    }
}
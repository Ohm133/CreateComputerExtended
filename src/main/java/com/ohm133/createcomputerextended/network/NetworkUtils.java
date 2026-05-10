package com.ohm133.createcomputerextended.network;

import dan200.computercraft.api.network.wired.WiredElement;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;

public class NetworkUtils {

    public static List<WiredElement> findAdjacentNetworks(
            Level level,
            BlockPos pos
    ) {

        List<WiredElement> result = new ArrayList<>();

        for (Direction dir : Direction.values()) {

            BlockEntity be =
                    level.getBlockEntity(pos.relative(dir));

            if (be instanceof WiredElement wired) {
                result.add(wired);
            }
        }

        return result;
    }
}
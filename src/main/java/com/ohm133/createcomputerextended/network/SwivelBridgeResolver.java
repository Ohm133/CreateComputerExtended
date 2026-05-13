package com.ohm133.createcomputerextended.network;

import com.ohm133.createcomputerextended.blockentity.NetworkCableHost;

import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredElementCapability;

import dev.simulated_team.simulated.content.blocks.swivel_bearing.SwivelBearingBlock;
import dev.simulated_team.simulated.content.blocks.swivel_bearing.SwivelBearingBlockEntity;
import dev.simulated_team.simulated.content.blocks.swivel_bearing.link_block.SwivelBearingPlateBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SwivelBridgeResolver {
    public static void refreshAround(BlockEntity origin) {
        Level level = origin.getLevel();

        if (level == null || level.isClientSide) {
            return;
        }

        tryResolveFromNetworkBlock(origin);

        for (Direction direction : Direction.values()) {
            BlockEntity neighbour = level.getBlockEntity(origin.getBlockPos().relative(direction));

            if (neighbour instanceof SwivelBearingBlockEntity swivel) {
                resolveSwivel(level, swivel);
            }
        }
    }

    public static void tryResolveFromNetworkBlock(BlockEntity networkBlock) {
        Level level = networkBlock.getLevel();

        if (level == null || level.isClientSide) {
            return;
        }

        for (Direction direction : Direction.values()) {
            BlockPos pos = networkBlock.getBlockPos().relative(direction);
            BlockEntity be = level.getBlockEntity(pos);

            if (be instanceof SwivelBearingBlockEntity swivel) {
                resolveSwivel(level, swivel);
            }
        }
    }

    public static void resolveSwivel(Level level, SwivelBearingBlockEntity swivel) {
        if (level == null || level.isClientSide) {
            return;
        }

        BlockState swivelState = swivel.getBlockState();

        if (!(swivelState.getBlock() instanceof SwivelBearingBlock)) {
            return;
        }

        Direction facing = swivelState.getValue(SwivelBearingBlock.FACING);

        WiredElement local = findNetworkElement(level, swivel.getBlockPos().relative(facing.getOpposite()), facing);

        if (local == null) {
            local = findAnyAdjacentNetwork(level, swivel.getBlockPos());
        }

        if (local == null) {
            return;
        }

        WiredElement remote = swivel.isAssembled()
                ? findAssembledRemote(level, swivel, facing)
                : findUnassembledRemote(level, swivel, facing);

        if (remote == null) {
            return;
        }

        SwivelBridgeManager.connect(local.getNode(), remote.getNode());
    }

    private static WiredElement findUnassembledRemote(Level level, SwivelBearingBlockEntity swivel, Direction facing) {
        /*
         * Bearing non assemblé :
         *
         * [network] [swivel] [network]
         *
         * On cherche le bloc réseau côté face du bearing.
         */
        BlockPos remotePos = swivel.getBlockPos().relative(facing);
        return findNetworkElement(level, remotePos, facing.getOpposite());
    }

    private static WiredElement findAssembledRemote(Level level, SwivelBearingBlockEntity swivel, Direction facing) {
        /*
         * Bearing assemblé :
         *
         * Simulated stocke la position de la plate avec getPlatePos().
         * On tente d’abord une résolution monde simple autour de cette plate.
         *
         * Pour un vrai accès sublevel/contraption, il faudra probablement utiliser
         * Sable SubLevelContainer avec swivel.getSubLevelID().
         */
        BlockPos platePos = swivel.getPlatePos();

        if (platePos == null) {
            return null;
        }

        BlockState plateState = level.getBlockState(platePos);

        if (!(plateState.getBlock() instanceof SwivelBearingPlateBlock)) {
            return null;
        }

        Direction plateFacing = plateState.getValue(SwivelBearingPlateBlock.FACING);

        BlockPos remotePos = platePos.relative(plateFacing.getOpposite());

        WiredElement remote = findNetworkElement(level, remotePos, plateFacing);

        if (remote != null) {
            return remote;
        }

        return findAnyAdjacentNetwork(level, platePos);
    }

    private static WiredElement findNetworkElement(Level level, BlockPos pos, Direction side) {
        WiredElement element = level.getCapability(
                WiredElementCapability.get(),
                pos,
                side
        );

        if (element != null) {
            return element;
        }

        BlockEntity be = level.getBlockEntity(pos);

        if (be instanceof NetworkCableHost host) {
            return host.getNetworkElement();
        }

        return null;
    }

    private static WiredElement findAnyAdjacentNetwork(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            WiredElement element = findNetworkElement(
                    level,
                    pos.relative(direction),
                    direction.getOpposite()
            );

            if (element != null) {
                return element;
            }
        }

        return null;
    }
}
package com.ohm133.createcomputerextended.network;

import com.ohm133.createcomputerextended.blockentity.NetworkCableHost;

import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredElementCapability;

import dev.ryanhcode.sable.sublevel.SubLevel;
import dev.simulated_team.simulated.content.blocks.swivel_bearing.SwivelBearingBlock;
import dev.simulated_team.simulated.content.blocks.swivel_bearing.SwivelBearingBlockEntity;
import dev.simulated_team.simulated.content.blocks.swivel_bearing.link_block.SwivelBearingPlateBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SwivelBridgeResolver {
    public static void refreshAround(BlockEntity origin) {
        Level level = origin.getLevel();
        if (level == null || level.isClientSide) return;

        for (Direction direction : Direction.values()) {
            BlockEntity neighbour = level.getBlockEntity(origin.getBlockPos().relative(direction));
            if (neighbour instanceof SwivelBearingBlockEntity swivel) {
                resolveSwivel(level, swivel);
            }
        }
    }

    public static void resolveSwivel(Level level, SwivelBearingBlockEntity swivel) {
        if (level == null || level.isClientSide) return;

        BlockState swivelState = swivel.getBlockState();
        if (!(swivelState.getBlock() instanceof SwivelBearingBlock)) return;

        Direction facing = swivelState.getValue(SwivelBearingBlock.FACING);

        WiredElement local = findAnyAdjacentNetwork(level, swivel.getBlockPos());
        if (local == null) {
            System.out.println("[CCE] No local network around swivel " + swivel.getBlockPos());
            return;
        }

        WiredElement remote = swivel.isAssembled()
                ? findAssembledRemote(level, swivel)
                : findUnassembledRemote(level, swivel, facing);

        System.out.println("[CCE] resolve swivel assembled="
                + swivel.isAssembled()
                + " plate=" + swivel.getPlatePos()
                + " sublevel=" + swivel.getSubLevelID()
                + " local=" + local
                + " remote=" + remote);

        if (remote == null || remote.getNode() == local.getNode()) return;

        SwivelBridgeManager.connect(local.getNode(), remote.getNode());
    }

    private static WiredElement findUnassembledRemote(Level level, SwivelBearingBlockEntity swivel, Direction facing) {
        return findNetworkElement(level, swivel.getBlockPos().relative(facing), facing.getOpposite());
    }

    private static WiredElement findAssembledRemote(Level level, SwivelBearingBlockEntity swivel) {
        BlockPos platePos = swivel.getPlatePos();
        if (platePos == null) return null;

        SubLevel subLevel = SableCompat.getSubLevel(level, swivel.getSubLevelID());
        if (subLevel == null) {
            System.out.println("[CCE] SubLevel not found for " + swivel.getSubLevelID());
            return null;
        }

        /*
         * 1) Essai monde normal : la plate est visible dans le monde principal.
         */
        WiredElement worldRemote = findAnyAdjacentNetwork(level, platePos);
        if (worldRemote != null) return worldRemote;

        /*
         * 2) Essai Sable : les blocs de la contraption sont dans l'embedded level accessor.
         */
        LevelAccessor embedded = SableCompat.getEmbeddedLevelAccessor(subLevel);

        if (embedded == null) {
            System.out.println("[CCE] Embedded LevelAccessor not found");
            return null;
        }

        BlockState plateState = level.getBlockState(platePos);
        if (plateState.getBlock() instanceof SwivelBearingPlateBlock) {
            Direction plateFacing = plateState.getValue(SwivelBearingPlateBlock.FACING);

            WiredElement direct = findNetworkHostInAccessor(
                    embedded,
                    platePos.relative(plateFacing.getOpposite())
            );

            if (direct != null) return direct;
        }

        /*
         * 3) Fallback robuste : cherche tout bloc réseau autour de la plate dans le sublevel.
         */
        for (Direction direction : Direction.values()) {
            WiredElement element = findNetworkHostInAccessor(embedded, platePos.relative(direction));
            if (element != null) return element;
        }

        return null;
    }

    private static WiredElement findNetworkElement(Level level, BlockPos pos, Direction side) {
        WiredElement element = level.getCapability(WiredElementCapability.get(), pos, side);
        if (element != null) return element;

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof NetworkCableHost host) return host.getNetworkElement();

        return null;
    }

    private static WiredElement findAnyAdjacentNetwork(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            WiredElement element = findNetworkElement(level, pos.relative(direction), direction.getOpposite());
            if (element != null) return element;
        }

        return null;
    }

    private static WiredElement findNetworkHostInAccessor(LevelAccessor accessor, BlockPos pos) {
        BlockEntity be = accessor.getBlockEntity(pos);

        if (be instanceof NetworkCableHost host) {
            return host.getNetworkElement();
        }

        return null;
    }
}
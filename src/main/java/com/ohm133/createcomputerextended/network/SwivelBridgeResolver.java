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
            BlockEntity be = level.getBlockEntity(networkBlock.getBlockPos().relative(direction));

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
                ? findAssembledRemote(level, swivel)
                : findUnassembledRemote(level, swivel, facing);

        if (remote == null || remote.getNode() == local.getNode()) {
            return;
        }

        SwivelBridgeManager.connect(local.getNode(), remote.getNode());
    }

    private static WiredElement findUnassembledRemote(
            Level level,
            SwivelBearingBlockEntity swivel,
            Direction facing
    ) {
        BlockPos remotePos = swivel.getBlockPos().relative(facing);
        return findNetworkElement(level, remotePos, facing.getOpposite());
    }

    private static WiredElement findAssembledRemote(
            Level level,
            SwivelBearingBlockEntity swivel
    ) {
        BlockPos platePos = swivel.getPlatePos();

        if (platePos == null) {
            return null;
        }

        /*
         * Simulated place la plate dans le level principal à platePos.
         * Le sublevel est utile pour retrouver/valider la contraption,
         * mais la plate reste lisible via level.getBlockState(platePos).
         */
        SubLevel subLevel = SableCompat.getSubLevel(level, swivel.getSubLevelID());

        if (subLevel == null) {
            return null;
        }

        BlockState plateState = level.getBlockState(platePos);

        if (!(plateState.getBlock() instanceof SwivelBearingPlateBlock)) {
            return null;
        }

        Direction plateFacing = plateState.getValue(SwivelBearingPlateBlock.FACING);

        /*
         * Premier essai : le bloc réseau juste derrière la plate, dans le monde principal.
         */
        BlockPos worldRemotePos = platePos.relative(plateFacing.getOpposite());
        WiredElement remote = findNetworkElement(level, worldRemotePos, plateFacing);

        if (remote != null) {
            return remote;
        }

        /*
         * Deuxième essai : chercher autour de la plate dans le monde principal.
         * C'est utile car Simulated garde souvent la link plate accessible en coordonnées plot.
         */
        remote = findAnyAdjacentNetwork(level, platePos);

        if (remote != null) {
            return remote;
        }

        /*
         * Pour l’instant, on ne tente pas encore d’utiliser l'embedded LevelAccessor
         * de Sable ici, parce que les capabilities NeoForge demandent un Level,
         * pas seulement un LevelAccessor.
         *
         * L'étape suivante sera de récupérer le vrai Level/plot access de SubLevel
         * si nécessaire selon le comportement observé en jeu.
         */
        return null;
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
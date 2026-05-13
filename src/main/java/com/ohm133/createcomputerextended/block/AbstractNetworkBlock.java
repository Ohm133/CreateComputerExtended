package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.NetworkCableHost;
import com.ohm133.createcomputerextended.network.SwivelBridgeResolver;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractNetworkBlock extends Block implements EntityBlock {
    protected AbstractNetworkBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void neighborChanged(
            BlockState state,
            Level level,
            BlockPos pos,
            Block block,
            BlockPos fromPos,
            boolean isMoving
    ) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);

        if (!level.isClientSide && level.getBlockEntity(pos) instanceof NetworkCableHost host) {
            host.refreshNetworkConnections();
            SwivelBridgeResolver.refreshAround(level.getBlockEntity(pos));
        }
    }
}
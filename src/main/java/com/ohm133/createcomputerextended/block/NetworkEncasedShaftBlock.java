package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.NetworkEncasedShaftBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkEncasedShaftBlock extends AbstractNetworkBlock {
    public NetworkEncasedShaftBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NetworkEncasedShaftBlockEntity(pos, state);
    }
}
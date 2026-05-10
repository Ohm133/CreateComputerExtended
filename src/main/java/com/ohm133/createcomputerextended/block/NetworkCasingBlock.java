package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.NetworkCasingBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkCasingBlock
        extends AbstractNetworkBlock {

    public NetworkCasingBlock(Properties properties) {

        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(
            BlockPos pos,
            BlockState state
    ) {

        return new NetworkCasingBlockEntity(pos, state);
    }
}
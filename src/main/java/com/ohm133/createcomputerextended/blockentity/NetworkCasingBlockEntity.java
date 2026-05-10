package com.ohm133.createcomputerextended.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkCasingBlockEntity
        extends AbstractNetworkBlockEntity {

    public NetworkCasingBlockEntity(
            BlockPos pos,
            BlockState state
    ) {

        super(
                ModBlockEntities.NETWORK_CASING_BE.get(),
                pos,
                state
        );
    }
}
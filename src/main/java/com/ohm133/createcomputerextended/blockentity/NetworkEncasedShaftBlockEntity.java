package com.ohm133.createcomputerextended.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkEncasedShaftBlockEntity
        extends AbstractNetworkBlockEntity {

    public NetworkEncasedShaftBlockEntity(
            BlockPos pos,
            BlockState state
    ) {

        super(
                ModBlockEntities.NETWORK_ENCASED_SHAFT_BE.get(),
                pos,
                state
        );
    }
}
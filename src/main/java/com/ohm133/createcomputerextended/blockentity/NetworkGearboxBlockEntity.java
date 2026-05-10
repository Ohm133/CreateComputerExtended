package com.ohm133.createcomputerextended.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkGearboxBlockEntity
        extends AbstractNetworkBlockEntity {

    public NetworkGearboxBlockEntity(
            BlockPos pos,
            BlockState state
    ) {

        super(
                ModBlockEntities.NETWORK_GEARBOX_BE.get(),
                pos,
                state
        );
    }
}
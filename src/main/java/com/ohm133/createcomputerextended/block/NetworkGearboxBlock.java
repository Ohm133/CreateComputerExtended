package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.NetworkGearboxBlockEntity;

import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkGearboxBlock
        extends GearboxBlock {

    public NetworkGearboxBlock(Properties properties) {

        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(
            BlockPos pos,
            BlockState state
    ) {

        return new NetworkGearboxBlockEntity(pos, state);
    }
}
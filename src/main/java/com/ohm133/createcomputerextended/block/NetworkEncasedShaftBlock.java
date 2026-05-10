package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.NetworkEncasedShaftBlockEntity;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.decoration.encasing.EncasableBlock;
import com.simibubi.create.content.decoration.girder.GirderEncasedShaftBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlock;
import com.simibubi.create.foundation.placement.PoleHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkEncasedShaftBlock
        extends AbstractSimpleShaftBlock {

    public NetworkEncasedShaftBlock(Properties properties) {

        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(
            BlockPos pos,
            BlockState state
    ) {

        return new NetworkEncasedShaftBlockEntity(pos, state);
    }
}
package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;
import com.ohm133.createcomputerextended.blockentity.NetworkEncasedShaftBlockEntity;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkEncasedShaftBlock extends EncasedShaftBlock {
    public NetworkEncasedShaftBlock(Properties properties) {
        super(properties, () -> AllBlocks.ANDESITE_CASING.get());
    }

    @Override
    public Class<NetworkEncasedShaftBlockEntity> getBlockEntityClass() {
        return NetworkEncasedShaftBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends NetworkEncasedShaftBlockEntity> getBlockEntityType() {
        return ModBlockEntities.NETWORK_ENCASED_SHAFT_BE.get();
    }
}
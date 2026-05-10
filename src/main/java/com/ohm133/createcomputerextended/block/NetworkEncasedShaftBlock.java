package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;
import com.ohm133.createcomputerextended.blockentity.NetworkEncasedShaftBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("unchecked")
public class NetworkEncasedShaftBlock extends EncasedShaftBlock {
    public NetworkEncasedShaftBlock(Properties properties) {
        super(properties, () -> Blocks.ANDESITE);
    }

    @Override
    public Class<KineticBlockEntity> getBlockEntityClass() {
        return (Class<KineticBlockEntity>) (Class<?>) NetworkEncasedShaftBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return ModBlockEntities.NETWORK_ENCASED_SHAFT_BE.get();
    }
}
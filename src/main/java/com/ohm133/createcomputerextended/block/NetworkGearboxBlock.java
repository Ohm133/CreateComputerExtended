package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;
import com.ohm133.createcomputerextended.blockentity.NetworkGearboxBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;

import net.minecraft.world.level.block.entity.BlockEntityType;

public class NetworkGearboxBlock extends GearboxBlock {
    public NetworkGearboxBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<NetworkGearboxBlockEntity> getBlockEntityClass() {
        return NetworkGearboxBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends NetworkGearboxBlockEntity> getBlockEntityType() {
        return ModBlockEntities.NETWORK_GEARBOX_BE.get();
    }
}
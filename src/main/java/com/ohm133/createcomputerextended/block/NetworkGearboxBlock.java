package com.ohm133.createcomputerextended.block;

import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;
import com.ohm133.createcomputerextended.blockentity.NetworkGearboxBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("unchecked")
public class NetworkGearboxBlock extends GearboxBlock {
    public NetworkGearboxBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<GearboxBlockEntity> getBlockEntityClass() {
        return (Class<GearboxBlockEntity>) (Class<?>) NetworkGearboxBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends GearboxBlockEntity> getBlockEntityType() {
        return ModBlockEntities.NETWORK_GEARBOX_BE.get();
    }
}
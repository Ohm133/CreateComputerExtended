package com.ohm133.createcomputerextended.block;

import org.jetbrains.annotations.Nullable;

import com.ohm133.createcomputerextended.blockentity.NetworkBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkCasing extends Block implements EntityBlock {

    public NetworkCasing() {
        super(BlockBehaviour.Properties.of().strength(2.0f));
    }

    // création du BlockEntity
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {

        return new NetworkBlockEntity(pos, state);
    }
}
package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.network.NetworkCableComponent;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkCasingBlockEntity extends BlockEntity {
    private final NetworkCableComponent network = new NetworkCableComponent(this);

    public NetworkCasingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NETWORK_CASING_BE.get(), pos, state);
    }

    public NetworkCableComponent getNetworkElement() {
        return network;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        network.connectNeighbours();
    }

    public void refreshNetworkConnections() {
        network.connectNeighbours();
    }

    @Override
    public void setRemoved() {
        network.remove();
        super.setRemoved();
    }
}
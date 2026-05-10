package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.network.NetworkCableComponent;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkEncasedShaftBlockEntity extends KineticBlockEntity implements NetworkCableHost {
        private final NetworkCableComponent network = new NetworkCableComponent(this);

        public NetworkEncasedShaftBlockEntity(BlockPos pos, BlockState state) {
                super(ModBlockEntities.NETWORK_ENCASED_SHAFT_BE.get(), pos, state);
        }

        @Override
        public NetworkCableComponent getNetworkElement() {
                return network;
        }

        @Override
        public void onLoad() {
                super.onLoad();
                network.connectNeighbours();
        }

        @Override
        public void invalidate() {
                network.remove();
                super.invalidate();
        }
}

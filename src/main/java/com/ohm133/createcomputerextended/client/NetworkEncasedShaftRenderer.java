package com.ohm133.createcomputerextended.client;

import com.ohm133.createcomputerextended.blockentity.NetworkEncasedShaftBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class NetworkEncasedShaftRenderer
        extends KineticBlockEntityRenderer<NetworkEncasedShaftBlockEntity> {

    public NetworkEncasedShaftRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected BlockState getRenderedBlockState(NetworkEncasedShaftBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }
}
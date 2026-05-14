package com.ohm133.createcomputerextended.client;

import com.ohm133.createcomputerextended.blockentity.NetworkGearboxBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class NetworkGearboxRenderer extends KineticBlockEntityRenderer<NetworkGearboxBlockEntity> {
    public NetworkGearboxRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
}
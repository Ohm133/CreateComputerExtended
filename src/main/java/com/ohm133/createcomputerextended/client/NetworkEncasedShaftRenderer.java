package com.ohm133.createcomputerextended.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.ohm133.createcomputerextended.blockentity.NetworkEncasedShaftBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class NetworkEncasedShaftRenderer extends KineticBlockEntityRenderer<NetworkEncasedShaftBlockEntity> {
    public NetworkEncasedShaftRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
}
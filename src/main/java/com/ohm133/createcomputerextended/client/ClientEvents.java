package com.ohm133.createcomputerextended.client;

import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ClientEvents {
    public static void registerBlockEntityRenderers(
            EntityRenderersEvent.RegisterRenderers event
    ) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.NETWORK_ENCASED_SHAFT_BE.get(),
                NetworkEncasedShaftRenderer::new
        );

        event.registerBlockEntityRenderer(
                ModBlockEntities.NETWORK_GEARBOX_BE.get(),
                NetworkGearboxRenderer::new
        );
    }
}
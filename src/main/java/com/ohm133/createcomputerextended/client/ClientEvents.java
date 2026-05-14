package com.ohm133.createcomputerextended.client;

import com.ohm133.createcomputerextended.CreateComputerExtended;
import com.ohm133.createcomputerextended.blockentity.ModBlockEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(
        modid = CreateComputerExtended.MODID,
        bus = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ClientEvents {

    @SubscribeEvent
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
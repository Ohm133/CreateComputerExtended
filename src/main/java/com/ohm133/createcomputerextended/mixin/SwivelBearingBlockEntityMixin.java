package com.ohm133.createcomputerextended.mixin;

import com.ohm133.createcomputerextended.network.SwivelBridgeResolver;
import com.ohm133.createcomputerextended.network.SwivelBridgeManager;
import dev.simulated_team.simulated.content.blocks.swivel_bearing.SwivelBearingBlockEntity;

import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwivelBearingBlockEntity.class)
public abstract class SwivelBearingBlockEntityMixin {
    @Inject(method = "assemble", at = @At("TAIL"), remap = false)
    private void createcomputerextended$afterAssemble(CallbackInfo ci) {
        SwivelBearingBlockEntity swivel = (SwivelBearingBlockEntity) (Object) this;
        Level level = swivel.getLevel();

        if (level != null && !level.isClientSide) {
            System.out.println("[CCE] Swivel assembled hook: " + swivel.getBlockPos());
            SwivelBridgeResolver.resolveSwivel(level, swivel);
        }
    }

    @Inject(method = "disassemble", at = @At("HEAD"), remap = false)
    private void createcomputerextended$beforeDisassemble(CallbackInfo ci) {
        SwivelBearingBlockEntity swivel = (SwivelBearingBlockEntity) (Object) this;
        Level level = swivel.getLevel();

        if (level != null && !level.isClientSide) {
            SwivelBridgeManager.disconnect(swivel.getBlockPos());
        }
    }
    @Inject(method = "remove", at = @At("HEAD"), remap = false)
    private void createcomputerextended$onRemove(CallbackInfo ci) {
        SwivelBearingBlockEntity swivel = (SwivelBearingBlockEntity) (Object) this;
        Level level = swivel.getLevel();

        if (level != null && !level.isClientSide) {
            System.out.println("[CCE] Swivel removed hook: " + swivel.getBlockPos());
            SwivelBridgeManager.disconnect(swivel.getBlockPos());
        }
    }
}
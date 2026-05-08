package com.ohm133.createcomputerextended.blockentity;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredNode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class NetworkBlockEntity extends BlockEntity implements WiredElement {

    // node réseau CC
    private WiredNode node;

    public NetworkBlockEntity(BlockPos pos, BlockState state) {
        super(
                ModBlockEntities.NETWORK_BLOCK_ENTITY.get(),
                pos,
                state
        );
    }

    // appelé quand le block entity est chargé dans le monde
    @Override
    public void onLoad() {
        super.onLoad();

        // évite double création
        if (node == null) {
            node = ComputerCraftAPI.createWiredNodeForElement(this);
        }
    }

    // appelé quand le bloc est détruit/déchargé
    @Override
    public void setRemoved() {

        // détruit proprement le node CC
        if (node != null) {
            node.remove();
        }

        super.setRemoved();
    }

    // retourne le node réseau
    @Override
    public WiredNode getNode() {
        return node;
    }

    // identifiant réseau
    @Override
    public String getSenderID() {
        return "createcomputerextended.network_block";
    }

    // monde actuel
    @Override
    public Level getLevel() {
        return level;
    }

    // position dans le monde
    @Override
    public Vec3 getPosition() {
        return Vec3.atCenterOf(worldPosition);
    }
}
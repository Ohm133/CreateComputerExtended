package com.ohm133.createcomputerextended.network;

import com.ohm133.createcomputerextended.network.SwivelBridgeManager;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredNode;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public class NetworkCableComponent implements WiredElement {
    private final BlockEntity owner;
    private final WiredNode node;

    public NetworkCableComponent(BlockEntity owner) {
        this.owner = owner;
        this.node = ComputerCraftAPI.createWiredNodeForElement(this);
    }

    public BlockEntity getOwner() {
        return owner;
    }

    @Override
    public WiredNode getNode() {
        return node;
    }

    @Override
    public Level getLevel() {
        return owner.getLevel();
    }

    @Override
    public Vec3 getPosition() {
        return Vec3.atCenterOf(owner.getBlockPos());
    }

    @Override
    public String getSenderID() {
        return "createcomputerextended:" + owner.getBlockPos().toShortString();
    }

    public void connectNeighbours() {
        if (owner.getLevel() != null && !owner.getLevel().isClientSide) {
            NetworkConnector.connectNeighbours(owner, this);
            SwivelBridgeResolver.refreshAround(owner);
        }
    }

    public void remove() {

        node.remove();
    }
}
package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.network.NetworkConnector;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredNode;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractNetworkBlockEntity extends BlockEntity implements WiredElement {
    private final WiredNode node;

    protected AbstractNetworkBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.node = ComputerCraftAPI.createWiredNodeForElement(this);
    }

    @Override
    public WiredNode getNode() {
        return node;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public Vec3 getPosition() {
        return Vec3.atCenterOf(worldPosition);
    }

    @Override
    public String getSenderID() {
        return "createcomputerextended:" + worldPosition.toShortString();
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (level != null && !level.isClientSide) {
            NetworkConnector.connectNeighbours(this);
        }
    }

    @Override
    public void setRemoved() {
        if (level != null && !level.isClientSide) {
            node.remove();
        }

        super.setRemoved();
    }
}
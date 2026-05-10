package com.ohm133.createcomputerextended.blockentity;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.network.Packet;
import dan200.computercraft.api.network.PacketReceiver;
import dan200.computercraft.api.network.PacketSender;
import dan200.computercraft.api.network.wired.WiredElement;
import dan200.computercraft.api.network.wired.WiredNode;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractNetworkBlockEntity
        extends BlockEntity
        implements WiredElement, PacketReceiver, PacketSender {

    /*
     * Node réseau officiel CC
     */
    protected final WiredNode node;

    public AbstractNetworkBlockEntity(
            BlockEntityType<?> type,
            BlockPos pos,
            BlockState state
    ) {

        super(type, pos, state);

        /*
         * Création officielle du node
         */
        this.node =
                ComputerCraftAPI.createWiredNodeForElement(this);
    }

    /*
     * Retourne le node réseau
     */
    @Override
    public WiredNode getNode() {
        return node;
    }

    /*
     * ID unique réseau
     */
    @Override
    public String getSenderID() {

        return worldPosition.toShortString();
    }

    /*
     * Position réseau
     */
    @Override
    public Vec3 getPosition() {

        return Vec3.atCenterOf(worldPosition);
    }

    /*
     * Niveau Minecraft
     */
    @Override
    public Level getLevel() {

        return level;
    }

    /*
     * Réception packet même dimension
     *
     * Vide :
     * ton câble ne traite pas les packets.
     */
    @Override
    public void receiveSameDimension(
            Packet packet,
            double distance
    ) {
    }

    /*
     * Réception inter-dimension
     */
    @Override
    public void receiveDifferentDimension(
            Packet packet
    ) {
    }

    /*
     * Chargement du bloc
     */
    @Override
    public void onLoad() {

        super.onLoad();

        if (!level.isClientSide) {

            /*
             * Connexion auto voisins
             */
            com.ohm133.createcomputerextended.network.NetworkConnector
                    .connectNeighbours(this);
        }
    }

    /*
     * Destruction propre réseau
     */
    @Override
    public void setRemoved() {

        super.setRemoved();

        if (level != null && !level.isClientSide) {

            node.remove();
        }
    }
}
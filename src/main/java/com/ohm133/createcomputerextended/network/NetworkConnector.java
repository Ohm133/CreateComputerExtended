package com.ohm133.createcomputerextended.network;

import dan200.computercraft.api.network.wired.WiredElement;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.ohm133.createcomputerextended.blockentity.AbstractNetworkBlockEntity;

public class NetworkConnector {

    /*
     * Connecte automatiquement
     * les voisins réseau CC.
     */
    public static void connectNeighbours(
            AbstractNetworkBlockEntity blockEntity
    ) {

        for (Direction direction : Direction.values()) {

            BlockPos targetPos =
                    blockEntity.getBlockPos()
                            .relative(direction);

            BlockEntity neighbour =
                    blockEntity.getLevel()
                            .getBlockEntity(targetPos);

            /*
             * Vérifie si le voisin
             * est un élément réseau CC.
             */
            if (neighbour instanceof WiredElement wired) {

                /*
                 * Connexion réseau officielle
                 */
                blockEntity.getNode()
                        .connectTo(wired.getNode());
            }
        }
    }
}
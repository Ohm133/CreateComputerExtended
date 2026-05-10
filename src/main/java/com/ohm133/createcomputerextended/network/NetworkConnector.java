package com.ohm133.createcomputerextended.network;

import com.ohm133.createcomputerextended.blockentity.NetworkBlockEntity;

import dan200.computercraft.api.network.wired.WiredElement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

public class NetworkConnector {

    /*
     * Connecte automatiquement les blocs adjacents
     */
    public static void connectAdjacent(NetworkBlockEntity blockEntity) {

        for (Direction direction : Direction.values()) {

            BlockPos targetPos =
                    blockEntity.getBlockPos().relative(direction);

            BlockEntity neighbour =
                    blockEntity.getLevel().getBlockEntity(targetPos);

            /*
             * Vérifie si le voisin est un élément réseau CC
             */
            if (neighbour instanceof WiredElement wiredElement) {

                /*
                 * Connecte les deux nodes
                 */
                blockEntity.getNode().connectTo(
                        wiredElement.getNode()
                );

                System.out.println(
                        "Connexion réseau : "
                                + blockEntity.getBlockPos()
                                + " -> "
                                + targetPos
                );
            }
        }
    }
}
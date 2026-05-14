package com.ohm133.createcomputerextended.network;

import dan200.computercraft.api.network.wired.WiredNode;

import net.minecraft.core.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class SwivelBridgeManager {

    private record Bridge(WiredNode a, WiredNode b) {}

    /*
     * 1 bridge par swivel
     */
    private static final Map<BlockPos, Bridge> ACTIVE_BRIDGES = new HashMap<>();

    public static void connect(BlockPos swivelPos, WiredNode a, WiredNode b) {
        if (a == null || b == null) return;
        if (a == b) return;

        Bridge existing = ACTIVE_BRIDGES.get(swivelPos);

        /*
         * Déjà connecté exactement pareil
         */
        if (existing != null) {
            boolean same =
                    (existing.a == a && existing.b == b)
                            || (existing.a == b && existing.b == a);

            if (same) return;

            /*
             * Nouveau bridge différent :
             * on nettoie l'ancien
             */
            disconnect(swivelPos);
        }

        System.out.println("[CCE] connect bridge " + swivelPos);

        a.connectTo(b);

        ACTIVE_BRIDGES.put(swivelPos, new Bridge(a, b));
    }

    public static void disconnect(BlockPos swivelPos) {
        Bridge bridge = ACTIVE_BRIDGES.remove(swivelPos);

        if (bridge == null) return;

        try {
            System.out.println("[CCE] disconnect bridge " + swivelPos);

            bridge.a.disconnectFrom(bridge.b);
        } catch (Exception e) {
            System.out.println("[CCE] disconnect failed " + e);
        }
    }
}
package com.ohm133.createcomputerextended.network;

import dan200.computercraft.api.network.wired.WiredNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SwivelBridgeManager {
    private static final Set<BridgeConnection> CONNECTIONS = new HashSet<>();

    public static void connect(WiredNode a, WiredNode b) {
        if (a == null || b == null || a == b) return;

        BridgeConnection connection = new BridgeConnection(a, b);

        if (CONNECTIONS.add(connection)) {
            a.connectTo(b);
        }
    }

    public static void disconnect(WiredNode a, WiredNode b) {
        BridgeConnection connection = new BridgeConnection(a, b);

        if (CONNECTIONS.remove(connection)) {
            a.disconnectFrom(b);
        }
    }

    public static void disconnectAllFor(WiredNode node) {
        Iterator<BridgeConnection> iterator = CONNECTIONS.iterator();

        while (iterator.hasNext()) {
            BridgeConnection connection = iterator.next();

            if (connection.contains(node)) {
                connection.a().disconnectFrom(connection.b());
                iterator.remove();
            }
        }
    }
}
package com.ohm133.createcomputerextended.network;

import dan200.computercraft.api.network.wired.WiredNode;

public class SwivelBearingNetworkBridge {

    public static void connect(
            WiredNode nodeA,
            WiredNode nodeB
    ) {

        nodeA.connectTo(nodeB);
    }

    public static void disconnect(
            WiredNode nodeA,
            WiredNode nodeB
    ) {

        nodeA.disconnectFrom(nodeB);
    }
}
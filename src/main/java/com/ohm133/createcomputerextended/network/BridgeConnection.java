package com.ohm133.createcomputerextended.network;

import dan200.computercraft.api.network.wired.WiredNode;

public record BridgeConnection(WiredNode a, WiredNode b) {
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BridgeConnection other)) return false;

        return (a == other.a && b == other.b)
                || (a == other.b && b == other.a);
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(a) + System.identityHashCode(b);
    }

    public boolean contains(WiredNode node) {
        return a == node || b == node;
    }
}
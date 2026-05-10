package com.ohm133.createcomputerextended.blockentity;

import com.ohm133.createcomputerextended.network.NetworkCableComponent;

public interface NetworkCableHost {
    NetworkCableComponent getNetworkElement();

    default void refreshNetworkConnections() {
        getNetworkElement().connectNeighbours();
    }
}
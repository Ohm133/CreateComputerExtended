package com.ohm133.createcomputerextended.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;

public abstract class AbstractNetworkBlock
        extends Block
        implements EntityBlock {

    public AbstractNetworkBlock(Properties properties) {

        super(properties);
    }
}
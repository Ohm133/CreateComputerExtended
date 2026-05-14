package com.ohm133.createcomputerextended.network;

import dev.ryanhcode.sable.api.sublevel.SubLevelContainer;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.UUID;

public class SableCompat {
    @Nullable
    public static Object getSubLevel(Level level, @Nullable UUID id) {
        if (level == null || id == null) return null;

        SubLevelContainer container = SubLevelContainer.getContainer(level);
        if (container == null) return null;

        return container.getSubLevel(id);
    }

    @Nullable
    public static LevelAccessor getEmbeddedLevelAccessor(Object subLevel) {
        if (subLevel == null) return null;

        try {
            Method getPlot = subLevel.getClass().getMethod("getPlot");
            Object plot = getPlot.invoke(subLevel);

            if (plot == null) return null;

            Method getEmbeddedLevelAccessor =
                    plot.getClass().getMethod("getEmbeddedLevelAccessor");

            Object result = getEmbeddedLevelAccessor.invoke(plot);

            if (result instanceof LevelAccessor accessor) {
                return accessor;
            }
        } catch (Exception exception) {
            System.out.println("[CCE] Failed to access Sable embedded level: " + exception);
        }

        return null;
    }
}
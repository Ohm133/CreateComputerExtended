package com.ohm133.createcomputerextended.network;

import dev.ryanhcode.sable.api.sublevel.SubLevelContainer;
import dev.ryanhcode.sable.sublevel.SubLevel;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.UUID;

public class SableCompat {
    @Nullable
    public static SubLevel getSubLevel(Level level, @Nullable UUID id) {
        if (level == null || id == null) return null;

        SubLevelContainer container = SubLevelContainer.getContainer(level);
        if (container == null) return null;

        return container.getSubLevel(id);
    }

    @Nullable
    public static LevelAccessor getEmbeddedLevelAccessor(SubLevel subLevel) {
        try {
            Object plot = subLevel.getPlot();

            Method method = plot.getClass().getMethod("getEmbeddedLevelAccessor");
            Object result = method.invoke(plot);

            if (result instanceof LevelAccessor accessor) {
                return accessor;
            }
        } catch (Exception exception) {
            System.out.println("[CCE] Failed to access Sable embedded level: " + exception);
        }

        return null;
    }
}
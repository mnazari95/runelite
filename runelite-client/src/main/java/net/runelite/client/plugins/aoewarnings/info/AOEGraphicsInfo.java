package net.runelite.client.plugins.aoewarnings.info;

import com.google.common.collect.Maps;
import net.runelite.api.GraphicID;
import net.runelite.client.plugins.aoewarnings.AoeWarningConfig;

import java.time.Duration;
import java.util.concurrent.ConcurrentMap;

public enum AOEGraphicsInfo implements AOEInfo {

    OLM_FALLING_CRYSTALS(GraphicID.OLM_FALLING_CRYSTAL, 3000, 3) {
        @Override
        public boolean isEnabled(AoeWarningConfig config) {
            return config.isOlmEnabled();
        }
    }
    ;

    private int id, duration, size;

    //Get from ID.
    private static ConcurrentMap<Integer, AOEGraphicsInfo> info;
    static {
        info = Maps.newConcurrentMap();
        for(AOEGraphicsInfo i : values()) {
            info.put(i.getID(), i);
        }
    }

    /**
     * Get the AOEInfo from the given ID.
     * @param id The ID to look for.
     * @return The AOEInfo from the given ID. Null if not found.
     */
    public static AOEGraphicsInfo getFromID(int id) {
        return info.get(id);
    }

    private AOEGraphicsInfo(int id, int duration, int size) {
        this.id = id;
        this.size = size;
        this.duration = duration;
    }

    public int getID() {
        return id;
    }

    @Override
    public Duration getDuration() {
        return Duration.ofMillis(duration);
    }

    @Override
    public int getSize() {
        return size;
    }
}

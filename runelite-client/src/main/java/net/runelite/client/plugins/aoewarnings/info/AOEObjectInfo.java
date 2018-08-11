package net.runelite.client.plugins.aoewarnings.info;

import com.google.common.collect.Maps;
import net.runelite.api.ObjectID;
import net.runelite.client.plugins.aoewarnings.AoeWarningConfig;

import java.time.Duration;
import java.util.concurrent.ConcurrentMap;

public enum AOEObjectInfo implements AOEInfo {

    OLM_BOMB(ObjectID.CRYSTAL_BOMB, 6000, 7) {
        @Override
        public boolean isEnabled(AoeWarningConfig config) {
            return config.isOlmEnabled();
        }
    };

    private int id, duration, size;

    //Get from ID.
    private static ConcurrentMap<Integer, AOEObjectInfo> info;
    static {
        info = Maps.newConcurrentMap();
        for(AOEObjectInfo i : values()) {
            info.put(i.getID(), i);
        }
    }

    /**
     * Get the AOEInfo from the given ID.
     * @param id The ID to look for.
     * @return The AOEInfo from the given ID. Null if not found.
     */
    public static AOEObjectInfo getFromID(int id) {
        return info.get(id);
    }

    private AOEObjectInfo(int id, int duration, int size) {
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

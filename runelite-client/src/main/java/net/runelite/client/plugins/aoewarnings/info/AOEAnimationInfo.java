package net.runelite.client.plugins.aoewarnings.info;

import com.google.common.collect.Maps;
import net.runelite.client.plugins.aoewarnings.AoeWarningConfig;

import java.time.Duration;
import java.util.concurrent.ConcurrentMap;

public enum AOEAnimationInfo implements AOEInfo {

    LIZARDMAN_SHAMAN_MINION(7159, 2400, 3) {
        @Override
        public boolean isEnabled(AoeWarningConfig config) {
            return config.isShamansEnabled();
        }
    };

    private int animationID;
    private int duration;
    private int size;

    //Get from ID.
    private static ConcurrentMap<Integer, AOEAnimationInfo> info;
    static {
        info = Maps.newConcurrentMap();
        for(AOEAnimationInfo i : values()) {
            info.put(i.getAnimationID(), i);
        }
    }

    /**
     * Get the AOEInfo from the given ID.
     * @param id The ID to look for.
     * @return The Info for the given ID.
     */
    public static AOEAnimationInfo getFromAnimationID(int id) {
        return info.get(id);
    }

    private AOEAnimationInfo(int animationID, int duration, int size) {
        this.animationID = animationID;
        this.duration = duration;
        this.size = size;
    }

    public int getAnimationID() {
        return animationID;
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

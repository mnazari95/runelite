package net.runelite.client.plugins.aoewarnings.info;

import net.runelite.client.plugins.aoewarnings.AoeWarningConfig;

import java.time.Duration;

/**
 * Simple interface providing all necessary information to process an AOE marker.
 * This is implemented by different types of AOE, such as on animation and on projectile.
 */
public interface AOEInfo {

    /**
     * Get the duration in millis that this indicator should last.
     * @return The duration in millis.
     */
    public Duration getDuration();

    /**
     * Get the size in tiles of this indicator. 1 = one tile. 3 = 3x3 area.
     * @return The size of the indicated area.
     */
    public int getSize();

    /**
     * See if this feature is enabled.
     * @return Whether this feature is enabled.
     */
    public boolean isEnabled(AoeWarningConfig config);
}

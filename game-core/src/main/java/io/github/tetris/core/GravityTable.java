package io.github.tetris.core;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Published gravity timings for each level. Values are drawn from Appendix A of the
 * specification and form a monotonically decreasing sequence.
 */
public final class GravityTable {
    private static final List<GravityLevel> LEVELS = List.of(
            new GravityLevel(1, Duration.ofMillis(800)),
            new GravityLevel(2, Duration.ofMillis(720)),
            new GravityLevel(3, Duration.ofMillis(630)),
            new GravityLevel(4, Duration.ofMillis(550)),
            new GravityLevel(5, Duration.ofMillis(500)),
            new GravityLevel(6, Duration.ofMillis(450)),
            new GravityLevel(7, Duration.ofMillis(400)),
            new GravityLevel(8, Duration.ofMillis(350)),
            new GravityLevel(9, Duration.ofMillis(320)),
            new GravityLevel(10, Duration.ofMillis(300)),
            new GravityLevel(11, Duration.ofMillis(270)),
            new GravityLevel(12, Duration.ofMillis(240)),
            new GravityLevel(13, Duration.ofMillis(220)),
            new GravityLevel(14, Duration.ofMillis(210)),
            new GravityLevel(15, Duration.ofMillis(200)),
            new GravityLevel(16, Duration.ofMillis(180)),
            new GravityLevel(17, Duration.ofMillis(160)),
            new GravityLevel(18, Duration.ofMillis(140)),
            new GravityLevel(19, Duration.ofMillis(120)),
            new GravityLevel(20, Duration.ofMillis(100))
    );

    private static final NavigableMap<Integer, Duration> LEVEL_LOOKUP = initialiseLookup();

    private GravityTable() {
    }

    private static NavigableMap<Integer, Duration> initialiseLookup() {
        NavigableMap<Integer, Duration> lookup = new TreeMap<>();
        for (GravityLevel entry : LEVELS) {
            Duration previous = lookup.put(entry.level(), entry.stepDuration());
            if (previous != null) {
                throw new IllegalStateException("duplicate level " + entry.level());
            }
        }
        return java.util.Collections.unmodifiableNavigableMap(
                new TreeMap<>(lookup)
        );
    }

    /**
     * Unmodifiable view of the entire gravity table.
     *
     * @return ordered list of level/duration pairs.
     */
    public static List<GravityLevel> levels() {
        return LEVELS;
    }

    /**
     * Resolves the gravity duration for the requested level. Levels above the published
     * range use the highest available entry.
     *
     * @param level the requested level (1-based).
     * @return the duration between automatic drops.
     */
    public static Duration gravityForLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("level must be positive");
        }
        Map.Entry<Integer, Duration> entry = LEVEL_LOOKUP.floorEntry(level);
        if (entry != null) {
            return entry.getValue();
        }
        return LEVELS.get(0).stepDuration();
    }
}

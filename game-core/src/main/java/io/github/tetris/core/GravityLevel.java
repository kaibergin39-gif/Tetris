package io.github.tetris.core;

import java.time.Duration;

/**
 * Immutable pairing of a level number with its gravity step duration.
 *
 * @param level        the 1-based level index.
 * @param stepDuration duration between automatic piece drops at this level.
 */
public record GravityLevel(int level, Duration stepDuration) {
    public GravityLevel {
        if (level < 1) {
            throw new IllegalArgumentException("level must be positive");
        }
        if (stepDuration.isNegative() || stepDuration.isZero()) {
            throw new IllegalArgumentException("stepDuration must be positive");
        }
    }
}

package io.github.tetris.persistence;

import java.time.Instant;
import java.util.Objects;

/**
 * Immutable value object capturing a single high score submission.
 *
 * @param playerName name shown in the leaderboard (non-blank).
 * @param score      total points earned.
 * @param level      level reached when the run ended.
 * @param lines      total lines cleared.
 * @param achievedAt timestamp of the run in UTC.
 */
public record HighScoreEntry(
        String playerName,
        long score,
        int level,
        int lines,
        Instant achievedAt
) {

    public HighScoreEntry {
        Objects.requireNonNull(playerName, "playerName");
        if (playerName.isBlank()) {
            throw new IllegalArgumentException("playerName must not be blank");
        }
        if (score < 0) {
            throw new IllegalArgumentException("score must be non-negative");
        }
        if (level < 1) {
            throw new IllegalArgumentException("level must be at least 1");
        }
        if (lines < 0) {
            throw new IllegalArgumentException("lines must be non-negative");
        }
        Objects.requireNonNull(achievedAt, "achievedAt");
    }
}

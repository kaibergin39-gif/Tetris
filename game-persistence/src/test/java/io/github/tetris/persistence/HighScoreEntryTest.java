package io.github.tetris.persistence;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class HighScoreEntryTest {

    @Test
    void rejectsBlankPlayerName() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new HighScoreEntry("   ", 100L, 1, 0, Instant.now()));
    }

    @Test
    void rejectsNegativeScore() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new HighScoreEntry("AAA", -1L, 1, 0, Instant.now()));
    }

    @Test
    void rejectsLevelBelowOne() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new HighScoreEntry("AAA", 10L, 0, 0, Instant.now()));
    }

    @Test
    void rejectsNegativeLines() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new HighScoreEntry("AAA", 10L, 1, -1, Instant.now()));
    }

    @Test
    void rejectsNullTimestamp() {
        assertThatNullPointerException()
                .isThrownBy(() -> new HighScoreEntry("AAA", 10L, 1, 0, null));
    }
}

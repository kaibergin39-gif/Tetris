package io.github.tetris.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import io.github.tetris.core.GameRules;

/**
 * Executable examples lifted directly from the specification to ensure constants remain in sync.
 */
class SpecificationExamplesTest {

    @Test
    void scoringExampleMatchesSpecification() {
        int level = 3;
        int hardDropCells = 5;

        int lineClearScore = GameRules.TETRIS_LINE_BASE_SCORE * level;
        int hardDropBonus = GameRules.HARD_DROP_POINTS_PER_CELL * hardDropCells;

        assertThat(lineClearScore + hardDropBonus).isEqualTo(2_410);
    }

    @Test
    void lockDelayIsFiveHundredMilliseconds() {
        assertThat(GameRules.BASE_LOCK_DELAY).isEqualTo(Duration.ofMillis(500));
    }

    @Test
    void nextQueueContainsFivePieces() {
        assertThat(GameRules.NEXT_QUEUE_SIZE).isEqualTo(5);
    }
}

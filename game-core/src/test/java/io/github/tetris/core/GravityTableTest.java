package io.github.tetris.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

class GravityTableTest {

    @Test
    void publishesMonotonicallyDecreasingDurations() {
        List<GravityLevel> levels = GravityTable.levels();

        assertThat(levels)
                .hasSize(20)
                .isSortedAccordingTo((a, b) -> Integer.compare(a.level(), b.level()));

        Duration previous = levels.get(0).stepDuration();
        for (int index = 1; index < levels.size(); index++) {
            Duration current = levels.get(index).stepDuration();
            assertThat(previous).as("level %d", levels.get(index - 1).level())
                    .isGreaterThan(current);
            previous = current;
        }
    }

    @Test
    void alignsWithSpecificationExamples() {
        assertThat(GravityTable.gravityForLevel(1)).isEqualTo(Duration.ofMillis(800));
        assertThat(GravityTable.gravityForLevel(5)).isEqualTo(Duration.ofMillis(500));
        assertThat(GravityTable.gravityForLevel(10)).isEqualTo(Duration.ofMillis(300));
        assertThat(GravityTable.gravityForLevel(15)).isEqualTo(Duration.ofMillis(200));
        assertThat(GravityTable.gravityForLevel(20)).isEqualTo(Duration.ofMillis(100));
    }

    @Test
    void extendsHighestPublishedValueForUpperLevels() {
        assertThat(GravityTable.gravityForLevel(25)).isEqualTo(Duration.ofMillis(100));
    }

    @Test
    void rejectsZeroOrNegativeLevelRequests() {
        assertThatThrownBy(() -> GravityTable.gravityForLevel(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("level must be positive");
    }
}

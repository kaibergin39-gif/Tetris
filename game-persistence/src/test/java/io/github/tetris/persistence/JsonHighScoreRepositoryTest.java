package io.github.tetris.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JsonHighScoreRepositoryTest {

    @TempDir
    Path tempDir;

    @Test
    void roundTripsEntries() throws IOException {
        Path file = tempDir.resolve("scores.json");
        JsonHighScoreRepository repository = JsonHighScoreRepository.withDefaultMapper(file);
        List<HighScoreEntry> entries = List.of(
                new HighScoreEntry("ABC", 123_456L, 7, 42, Instant.parse("2024-08-18T10:15:30Z")),
                new HighScoreEntry("XYZ", 987_654L, 12, 80, Instant.parse("2024-08-20T09:00:00Z"))
        );

        repository.save(entries);
        List<HighScoreEntry> loaded = repository.load();

        assertThat(loaded).containsExactlyElementsOf(entries);
    }

    @Test
    void loadingMissingFileReturnsEmptyList() throws IOException {
        Path file = tempDir.resolve("missing.json");
        JsonHighScoreRepository repository = JsonHighScoreRepository.withDefaultMapper(file);

        assertThat(repository.load()).isEmpty();
    }

    @Test
    void rejectsNullEntriesOnSave() throws IOException {
        Path file = tempDir.resolve("null.json");
        JsonHighScoreRepository repository = JsonHighScoreRepository.withDefaultMapper(file);

        assertThatThrownBy(() -> repository.save(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("entries");
    }
}

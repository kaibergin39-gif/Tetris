package io.github.tetris.persistence;

import java.io.IOException;
import java.util.List;

/**
 * Port for reading and writing persisted high scores. Implementations may back
 * onto files or databases.
 */
public interface HighScoreRepository {

    /**
     * Loads all persisted high score entries.
     *
     * @return immutable list of entries sorted according to repository rules.
     * @throws IOException if the underlying storage cannot be accessed.
     */
    List<HighScoreEntry> load() throws IOException;

    /**
     * Persists the supplied entries, replacing any previously stored data.
     *
     * @param entries entries to persist (top N, already sorted by caller).
     * @throws IOException if the underlying storage cannot be accessed.
     */
    void save(List<HighScoreEntry> entries) throws IOException;
}

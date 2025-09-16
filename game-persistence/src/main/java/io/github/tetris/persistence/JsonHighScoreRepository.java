package io.github.tetris.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * File-based implementation that persists high scores as JSON with ISO-8601 timestamps.
 */
public final class JsonHighScoreRepository implements HighScoreRepository {

    private static final TypeReference<List<HighScoreEntry>> HIGH_SCORE_LIST_TYPE =
            new TypeReference<>() {
            };

    private final ObjectMapper mapper;
    private final Path file;

    public JsonHighScoreRepository(Path file, ObjectMapper mapper) {
        this.file = Objects.requireNonNull(file, "file");
        this.mapper = Objects.requireNonNull(mapper, "mapper").copy();
        this.mapper.findAndRegisterModules();
    }

    /**
     * Factory method providing a mapper with the Java time module registered.
     *
     * @param file path to the JSON document.
     * @return repository instance.
     */
    public static JsonHighScoreRepository withDefaultMapper(Path file) {
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        return new JsonHighScoreRepository(file, mapper);
    }

    @Override
    public List<HighScoreEntry> load() throws IOException {
        if (!Files.exists(file)) {
            return List.of();
        }
        List<HighScoreEntry> entries = mapper.readValue(file.toFile(), HIGH_SCORE_LIST_TYPE);
        return List.copyOf(entries);
    }

    @Override
    public void save(List<HighScoreEntry> entries) throws IOException {
        Objects.requireNonNull(entries, "entries");
        Path parent = file.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        writer.writeValue(file.toFile(), entries);
    }
}

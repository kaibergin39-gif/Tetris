package io.github.tetris.audio;

import java.util.Objects;

/**
 * Identifies an audio asset. The identifier maps to a resource or file known by
 * the persistence layer.
 *
 * @param id stable identifier for the sound asset.
 */
public record SoundCue(String id) {

    public SoundCue {
        Objects.requireNonNull(id, "id");
        if (id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
    }
}

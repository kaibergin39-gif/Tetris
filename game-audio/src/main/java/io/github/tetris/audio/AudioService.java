package io.github.tetris.audio;

/**
 * Abstraction for playing short sound effects and longer background tracks.
 * Implementations should be thread-safe because the game loop may publish events
 * off the JavaFX thread.
 */
public interface AudioService {

    /**
     * Plays a transient sound effect (rotate, hard drop, line clear, etc.).
     *
     * @param cue descriptor of the sound to play.
     */
    void playEffect(SoundCue cue);

    /**
     * Starts or replaces the looping background track.
     *
     * @param cue descriptor of the track to play.
     */
    void loopMusic(SoundCue cue);

    /** Stops any currently playing background music. */
    void stopMusic();

    /**
     * Adjusts the master volume. Implementations should clamp to the valid range.
     *
     * @param volume desired master volume in the range [0, 1].
     */
    void setMasterVolume(double volume);

    /** Releases any native resources held by the audio backend. */
    void shutdown();
}

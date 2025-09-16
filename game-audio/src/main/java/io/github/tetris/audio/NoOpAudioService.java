package io.github.tetris.audio;

import java.util.Objects;

/**
 * Baseline implementation used for tests or headless runs. All operations validate input
 * but perform no IO.
 */
public final class NoOpAudioService implements AudioService {

    private double volume = 1.0d;

    @Override
    public void playEffect(SoundCue cue) {
        Objects.requireNonNull(cue, "cue");
    }

    @Override
    public void loopMusic(SoundCue cue) {
        Objects.requireNonNull(cue, "cue");
    }

    @Override
    public void stopMusic() {
        // intentionally blank
    }

    @Override
    public void setMasterVolume(double volume) {
        if (Double.isNaN(volume) || Double.isInfinite(volume)) {
            throw new IllegalArgumentException("volume must be finite");
        }
        this.volume = Math.max(0.0d, Math.min(1.0d, volume));
    }

    @Override
    public void shutdown() {
        // nothing to release
    }

    /** Current master volume after clamping. */
    public double volume() {
        return volume;
    }
}

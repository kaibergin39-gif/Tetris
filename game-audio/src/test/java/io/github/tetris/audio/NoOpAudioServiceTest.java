package io.github.tetris.audio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NoOpAudioServiceTest {

    private NoOpAudioService service;

    @BeforeEach
    void setUp() {
        service = new NoOpAudioService();
    }

    @Test
    void enforcesNonNullCueForEffects() {
        assertThatNullPointerException().isThrownBy(() -> service.playEffect(null));
    }

    @Test
    void enforcesNonNullCueForMusic() {
        assertThatNullPointerException().isThrownBy(() -> service.loopMusic(null));
    }

    @Test
    void clampsVolumeToValidRange() {
        service.setMasterVolume(0.75d);
        assertThat(service.volume()).isEqualTo(0.75d);

        service.setMasterVolume(-1.0d);
        assertThat(service.volume()).isEqualTo(0.0d);

        service.setMasterVolume(2.0d);
        assertThat(service.volume()).isEqualTo(1.0d);
    }

    @Test
    void rejectsInvalidVolumeValues() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> service.setMasterVolume(Double.NaN));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> service.setMasterVolume(Double.POSITIVE_INFINITY));
    }

    @Test
    void acceptsValidSoundCue() {
        SoundCue cue = new SoundCue("rotate");
        service.playEffect(cue);
        service.loopMusic(cue);
    }
}

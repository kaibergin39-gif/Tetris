package io.github.tetris.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumSet;
import java.util.List;

import org.junit.jupiter.api.Test;

class TetrominoTypeTest {

    @Test
    void standardBagOrderContainsAllSevenPieces() {
        List<TetrominoType> order = TetrominoType.standardBagOrder();

        assertThat(order)
                .containsExactly(TetrominoType.I, TetrominoType.O, TetrominoType.T,
                        TetrominoType.S, TetrominoType.Z, TetrominoType.J, TetrominoType.L);
        assertThat(EnumSet.copyOf(order)).hasSize(7);
    }

    @Test
    void symbolMatchesTypeNameInitial() {
        assertThat(TetrominoType.standardBagOrder())
                .allSatisfy(type -> assertThat(type.symbol()).isEqualTo(type.name().charAt(0)));
    }
}

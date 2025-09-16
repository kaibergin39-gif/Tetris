package io.github.tetris.core;

import java.util.List;

/**
 * Enumerates the seven standard tetrominoes defined by the guideline ruleset.
 */
public enum TetrominoType {
    I('I'),
    O('O'),
    T('T'),
    S('S'),
    Z('Z'),
    J('J'),
    L('L');

    private static final List<TetrominoType> STANDARD_BAG_ORDER = List.of(values());

    private final char symbol;

    TetrominoType(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Symbol used when rendering or serialising the piece type.
     *
     * @return the canonical single-character symbol.
     */
    public char symbol() {
        return symbol;
    }

    /**
     * Immutable ordered view of all tetromino types in their natural (bag) order.
     *
     * @return an unmodifiable list containing every {@link TetrominoType}.
     */
    public static List<TetrominoType> standardBagOrder() {
        return List.copyOf(STANDARD_BAG_ORDER);
    }
}

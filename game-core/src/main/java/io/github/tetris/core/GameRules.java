package io.github.tetris.core;

import java.time.Duration;

/**
 * Immutable collection of rule constants derived directly from the product specification.
 */
public final class GameRules {
    private GameRules() {
    }

    /** Width of the visible playfield in columns. */
    public static final int COLUMNS = 10;

    /** Height of the visible playfield (excluding hidden spawn rows). */
    public static final int VISIBLE_ROWS = 20;

    /** Number of upcoming pieces that must be visible in the next queue. */
    public static final int NEXT_QUEUE_SIZE = 5;

    /** Base lock delay applied whenever a piece is grounded without further input. */
    public static final Duration BASE_LOCK_DELAY = Duration.ofMillis(500);

    /** Lines required to advance the level counter. */
    public static final int LINES_PER_LEVEL = 10;

    /** Score multiplier for a single line clear before applying the level multiplier. */
    public static final int SINGLE_LINE_BASE_SCORE = 100;

    /** Score multiplier for a double line clear before applying the level multiplier. */
    public static final int DOUBLE_LINE_BASE_SCORE = 300;

    /** Score multiplier for a triple line clear before applying the level multiplier. */
    public static final int TRIPLE_LINE_BASE_SCORE = 500;

    /** Score multiplier for a Tetris (four line) clear before applying the level multiplier. */
    public static final int TETRIS_LINE_BASE_SCORE = 800;

    /** Points earned per cell travelled while soft dropping. */
    public static final int SOFT_DROP_POINTS_PER_CELL = 1;

    /** Points earned per cell travelled while hard dropping. */
    public static final int HARD_DROP_POINTS_PER_CELL = 2;

    /** Additional combo points (per level) after the first consecutive line clear. */
    public static final int COMBO_CHAIN_INCREMENT = 50;
}

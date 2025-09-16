# Tetris – Specification (v0.1 "What & Why")

## 1. Product goals
- Provide a fast, fair, classic Tetris experience with smooth controls and predictable difficulty ramp.
- Support short session play (1–10 minutes) with meaningful score progression and replayability.
- Prioritize clarity, responsiveness, and accessibility over visual flair.

## 2. Users & success
- **Primary user:** casual to mid-core player on keyboard/controller.
- **Success signals:**
  - Players consistently reach Level 5+ within 3 sessions.
  - 80% retention of high-score submission flow.
  - <1% user reports of "unfair piece sequence" or "unreadable board".

## 3. Scope (MVP)
- **Mode:** Single-player Marathon.
- **Playfield:** 10 columns × 20 visible rows (+ hidden spawn buffer).
- **Pieces:** 7 tetrominoes (I, O, T, S, Z, J, L).
- **Systems:** next queue (show 5 upcoming), hold, scoring, levels/speeds, game over, pause, basic sound, local high score table.
- **Out of scope (v0.1):** multiplayer, skins, custom boards, online leaderboards, marathon variants.

## 4. Core mechanics
- **Spawn:** one piece at a time at the top center; legal spawn only (no immediate collision).
- **Randomizer:** 7-bag (shuffle of the 7 tetrominoes; no repeats until bag exhausted).
- **Rotation system:** SRS-compatible behavior with wall-kicks; 0/90/180/270 rotations.
- **Gravity & movement:**
  - Auto-fall ("gravity") increases with level.
  - Soft drop: accelerates descent; hard drop: instant lock at landing row.
  - Lock delay: 500ms base; resets on piece movement/rotation while touching surface; cap resets per movement to avoid infinite stalling.
  - Lateral movement supports DAS/ARR-like behavior (initial delay then auto-repeat).
- **Line clears:** detect 1–4 line clears per lock; lines above collapse downward.
- **Scoring (classic-style):**
  - Single: 100 × level
  - Double: 300 × level
  - Triple: 500 × level
  - Tetris (4): 800 × level
  - Soft drop: +1 per cell; Hard drop: +2 per cell
  - Combo chain: +50 × level per consecutive clear beyond the first
  - Optional (toggleable in v0.1): T-Spin recognition omitted.
- **Levels & speed:** start at Level 1; level increases every 10 lines; gravity table defines frames/step per level (see Appendix A).
- **Top-out:** occurs when a new piece cannot spawn legally.

## 5. Controls & UX
- **Inputs:** left, right, rotate CW/CCW, 180° rotate (optional), soft drop, hard drop, hold, pause.
- **UI states:** Title → Gameplay → Pause → Game Over → High-score entry → Title.
- **Readability:** high-contrast blocks; ghost piece for landing preview; distinct lock/clear animations ≤300ms; next queue (5) and hold panel always visible.

## 6. Audio/feedback
- Subtle move/rotate/drop sounds; distinct line-clear and level-up cues; optional background loop with volume slider and mute.

## 7. Persistence & fairness
- Local storage of top 10 scores with date, level, lines.
- Seeded 7-bag randomizer for reproducibility (debug/testing).
- No "garbage" or invisible pieces; no mid-game changes to gravity/rotation rules.

## 8. Accessibility
- Remappable controls; color-blind friendly palette; adjustable ARR/DAS; toggle ghost piece; vibration/rumble optional.

## 9. Performance/constraints
- Target ≥60 FPS; input latency ≤50ms end-to-end on a typical laptop.
- Deterministic rules: same seed ⇒ same sequence and outcomes given same inputs.

## 10. Level speed table (examples)
- Level 1: gravity step every 0.8s
- Level 5: 0.5s
- Level 10: 0.3s
- Level 15: 0.2s
- Level 20: 0.1s
- Exact timings can be tuned but must decrease monotonically.

## 11. Acceptance criteria (executable-style scenarios)
- **Randomizer fairness:** In any 70 consecutive pieces, each tetromino appears 8–12 times; no tetromino repeats more than twice consecutively.
- **Line-clear scoring:** Given Level 3, clearing a Tetris from a hard drop of 5 cells yields 800×3 + (5×2) = 2,400 + 10 = 2,410 points added.
- **Lock delay:** While a piece is surface-touching, any left/right or rotate input within 500ms resets the lock timer; if no input within 500ms, the piece locks.
- **Wall-kicks:** A T piece rotated against a right wall applies SRS kick tests; if any kick yields a legal position, the rotation succeeds.
- **Hold system:** First hold swaps to hold slot and spawns next piece; subsequent holds during the same piece lock cycle only swap with the held piece; hold is disabled immediately after a hard drop.
- **Top-out:** If a newly generated piece collides at spawn position, the game ends and the Game Over screen is displayed within 300ms.

## 12. Telemetry (optional, local)
- Session length, highest level, total lines, reason for game end (top-out/manual quit); no PII.

## 13. Non-goals (v0.1)
- Competitive multiplayer, guideline-exact finesse detection, piece "preview skins," online profiles.

---

### Appendix A: Gravity timings
Publish a complete table of gravity values per level alongside the implementation. The timings above are examples and must form a monotonically decreasing sequence.

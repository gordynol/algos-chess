package org.gordynol.algos.chess;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RemainingFiguresTest {
    RemainingFigures figures = new RemainingFigures();
    @Test
    public void initialSetIsEmpty() {
        assertThat(figures.isEmpty()).isTrue();
    }

    @Test
    public void filledSetIsNotEmpty() {
        figures.add(Figure.BISHOP, 1);
        assertThat(figures.isEmpty()).isFalse();
    }

    @Test
    public void isLifo() {
        figures.add(Figure.BISHOP, 1);
        figures.add(Figure.KING, 1);

        assertThat(figures.next()).isEqualTo(Figure.KING);
    }

    @Test
    public void pullsNextElement() {
        figures.add(Figure.BISHOP, 1);
        figures.add(Figure.KING, 1);

        assertThat(figures.remaining().next()).isEqualTo(Figure.BISHOP);
    }
}
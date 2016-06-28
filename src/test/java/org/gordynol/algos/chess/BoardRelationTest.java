package org.gordynol.algos.chess;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.gordynol.algos.chess.BoardRelation;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BoardRelationTest {
    @Test
    @Parameters({"1, 1, true",
            "10, 10, true",
            "10, -10, true",
            "3, 5, false"})
    public void isDiagonalWhenDistanceEqualsByModulo(int x, int y, boolean isDiagonal) {
        assertThat(new BoardRelation(x, y).isDiagonal()).isEqualTo(isDiagonal);
    }

    @Test
    @Parameters({"1, 1, 1",
    "1, 0, 1",
    "5, 5, 5",
    "2, 1, 2",
    "3, 1, 3"})
    public void unilateralDistanceIsInKingSteps(int x, int y, int distance) {
        assertThat(new BoardRelation(x, y).unilateralDistance()).isEqualTo(distance);
    }

    @Test
    @Parameters({"1, 1, false",
            "0, 10, true",
            "10, 0, true",
            "3, 5, false"})
    public void isSameAxeWhenXorYDistanceIsZero(int x, int y, boolean isSameAxis) {
        assertThat(new BoardRelation(x, y).isSameAxis()).isEqualTo(isSameAxis);
    }
}
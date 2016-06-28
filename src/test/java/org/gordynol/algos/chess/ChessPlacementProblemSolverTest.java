package org.gordynol.algos.chess;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.gordynol.algos.chess.Figure.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ChessPlacementProblemSolverTest {
    @Test
    public void zeroForEmptyBoard() {
        assertThat(new ChessPlacementProblemSolver(new ChessBoard(0, 0)).countOfUniquePlacements()).isEqualTo(0);
    }

    @Test
    @Parameters({"1, 1, 1",
            "3, 4, 12",
            "5, 5, 25"})
    public void singleFigureCanBePutAnywhere(int x, int y, int count) {
        assertThat(new ChessPlacementProblemSolver(new ChessBoard(x, y)).withFigure(QUEEN).countOfUniquePlacements()).isEqualTo(count);
    }

    @Test
    public void bishopsInARow() {
        assertThat(new ChessPlacementProblemSolver(new ChessBoard(3, 1)).withFigures(BISHOP, 3).countOfUniquePlacements()).isEqualTo(1);
    }

    @Test
    public void bishopsPermutationInARow() {
        assertThat(new ChessPlacementProblemSolver(new ChessBoard(3, 1)).withFigures(BISHOP, 2).countOfUniquePlacements()).isEqualTo(3);
    }

    @Test
    public void kingAndRookOnSmallBoard() {
        assertThat(new ChessPlacementProblemSolver(new ChessBoard(3, 3))
                .withFigure(ROOK)
                .withFigure(KING)
                .countOfUniquePlacements()).isEqualTo(20);
    }

    @Test
    public void acceptanceScenario1() {
        assertThat(new ChessPlacementProblemSolver(new ChessBoard(3, 3))
                .withFigure(ROOK)
                .withFigures(KING, 2)
                .countOfUniquePlacements()).isEqualTo(4);

    }

    @Test
    public void acceptanceScenario2() {
        assertThat(new ChessPlacementProblemSolver(new ChessBoard(4, 4))
                .withFigures(ROOK, 2)
                .withFigures(KNIGHT, 4)
                .countOfUniquePlacements()).isEqualTo(8);

    }

    @Test
    @Ignore
    public void performanceScenario() {
        new ChessPlacementProblemSolver(new ChessBoard(6, 9))
                .withFigures(KING, 2)
                .withFigure(QUEEN)
                .withFigure(BISHOP)
                .withFigure(ROOK)
                .withFigure(KNIGHT)
                .countOfUniquePlacements();

    }
}
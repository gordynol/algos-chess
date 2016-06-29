package org.gordynol.algos.chess;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

import static org.gordynol.algos.chess.Figure.KNIGHT;
import static org.gordynol.algos.chess.Figure.ROOK;

@Fork(5)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class ChessPlacementProblemSolverMicroBenchmark {
    @Benchmark
    public void chessBoardSizeN() {
        new ChessPlacementProblemSolver(new ChessBoard(2, 4))
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize2N() {
        new ChessPlacementProblemSolver(new ChessBoard(4, 4))
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize3N() {
        new ChessPlacementProblemSolver(new ChessBoard(6, 4))
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize4N() {
        new ChessPlacementProblemSolver(new ChessBoard(8, 4))
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void chessBoardSize5N() {
        new ChessPlacementProblemSolver(new ChessBoard(10, 4))
                .withFigures(ROOK, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures2() {
        new ChessPlacementProblemSolver(new ChessBoard(6, 4))
                .withFigures(KNIGHT, 2)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures3() {
        new ChessPlacementProblemSolver(new ChessBoard(6, 4))
                .withFigures(KNIGHT, 3)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures4() {
        new ChessPlacementProblemSolver(new ChessBoard(6, 4))
                .withFigures(KNIGHT, 4)
                .countOfUniquePlacements();

    }

    @Benchmark
    public void figures5() {
        new ChessPlacementProblemSolver(new ChessBoard(6, 4))
                .withFigures(KNIGHT, 5)
                .countOfUniquePlacements();

    }
}
